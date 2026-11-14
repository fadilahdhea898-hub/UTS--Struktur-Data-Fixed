package com.uts.strukturdata;

import java.util.Comparator;

public class PutusanService {
    private SinglyLinkedList<Putusan> listPutusan;
    private AuditManager auditManager;

    public PutusanService(AuditManager auditManager) {
        this.listPutusan = new SinglyLinkedList<>();
        this.auditManager = auditManager;
    }

    public void loadDataFromCSV(String filePath) {
        this.listPutusan = CSVService.loadPutusanFromCSV(filePath);
        auditManager.log("IMPORT_CSV", "Memuat " + listPutusan.getSize() + " data putusan dari " + filePath);
    }

    public SinglyLinkedList<Putusan> getListPutusan() {
        return listPutusan;
    }

    public void tambahPutusan(Putusan p) {
        listPutusan.add(p);
        auditManager.log("TAMBAH_PUTUSAN", "Menambahkan perkara: " + p.getNomorPerkara());
    }

    public boolean hapusPutusan(String nomorPerkara) {
        boolean removed = listPutusan.removeIf(p -> p.getNomorPerkara().equalsIgnoreCase(nomorPerkara));
        if (removed) {
            auditManager.log("HAPUS_PUTUSAN", "Menghapus perkara: " + nomorPerkara);
        }
        return removed;
    }

    public Putusan cariByNomorPerkara(String nomorPerkara) {
        Node<Putusan> curr = listPutusan.getHead();
        while (curr != null) {
            if (curr.getData().getNomorPerkara().equalsIgnoreCase(nomorPerkara)) {
                auditManager.log("CARI_PERKARA", "Mencari perkara: " + nomorPerkara + " (Ditemukan)");
                return curr.getData();
            }
            curr = curr.getNext();
        }
        auditManager.log("CARI_PERKARA", "Mencari perkara: " + nomorPerkara + " (Tidak Ditemukan)");
        return null;
    }

    public SinglyLinkedList<Putusan> filterByVonisMin(int minVonisBulan) {
        SinglyLinkedList<Putusan> result = new SinglyLinkedList<>();
        Node<Putusan> curr = listPutusan.getHead();
        while (curr != null) {
            if (curr.getData().getVonisBulan() >= minVonisBulan) {
                result.add(curr.getData());
            }
            curr = curr.getNext();
        }
        auditManager.log("FILTER_VONIS", "Filter vonis >= " + minVonisBulan + " bulan (Hasil: " + result.getSize() + " data)");
        return result;
    }
    // Pengurutan daftar putusan berdasarkan durasi vonis (ASC/DESC)
    public void urutkanByVonis(boolean ascending) {
        Comparator<Putusan> comp = Comparator.comparingInt(Putusan::getVonisBulan);
        if (!ascending) comp = comp.reversed();
        listPutusan.sort(comp);
        auditManager.log("PENGURUTAN", "Mengurutkan data berdasarkan Vonis (" + (ascending ? "ASC" : "DESC") + ")");
    }

    // Perhitungan statistik agregasi durasi vonis (jumlah, rata-rata, min, max)
    public void cetakStatistik() {
        if (listPutusan.isEmpty()) {
            System.out.println("Data kosong.");
            return;
        }
        int total = listPutusan.getSize();
        long sumVonis = 0;
        long sumTuntutan = 0;
        long sumDenda = 0;
        int maxVonis = Integer.MIN_VALUE;
        int minVonis = Integer.MAX_VALUE;

        Node<Putusan> curr = listPutusan.getHead();
        while (curr != null) {
            Putusan p = curr.getData();
            sumVonis += p.getVonisBulan();
            sumTuntutan += p.getTuntutanBulan();
            sumDenda += p.getDenda();
            if (p.getVonisBulan() > maxVonis) maxVonis = p.getVonisBulan();
            if (p.getVonisBulan() < minVonis) minVonis = p.getVonisBulan();
            curr = curr.getNext();
        }

        System.out.println("\n=== AGREGASI & STATISTIK PUTUSAN PN JEMBER ===");
        System.out.println("Total Putusan Perkara : " + total);
        System.out.println("Rata-rata Vonis       : " + String.format("%.2f", (double) sumVonis / total) + " Bulan");
        System.out.println("Rata-rata Tuntutan    : " + String.format("%.2f", (double) sumTuntutan / total) + " Bulan");
        System.out.println("Vonis Tertinggi       : " + maxVonis + " Bulan (" + (maxVonis/12) + " Tahun)");
        System.out.println("Vonis Terendah        : " + minVonis + " Bulan");
        System.out.println("Total Denda Seluruhnya: Rp " + String.format("%,d", sumDenda));
        auditManager.log("STATISTIK", "Menampilkan statistik agregasi 50 putusan");
    }

    public void tampilkanSemua() {
        System.out.println("\n=== DAFTAR PUTUSAN PIDANA KHUSUS PN JEMBER ===");
        Node<Putusan> curr = listPutusan.getHead();
        int idx = 1;
        while (curr != null) {
            System.out.printf("%2d. %s\n", idx++, curr.getData().toString());
            curr = curr.getNext();
        }
    }
}
