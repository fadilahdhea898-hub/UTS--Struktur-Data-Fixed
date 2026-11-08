package com.uts.strukturdata;

import java.io.File;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        AuditManager auditManager = new AuditManager();
        PutusanService service = new PutusanService(auditManager);

        // Auto-resolve CSV file path regardless of working directory in IntelliJ
        File csvFile = new File("data/putusan-50.csv");
        if (!csvFile.exists()) {
            csvFile = new File("UTS-Struktur-Data/data/putusan-50.csv");
        }
        if (!csvFile.exists()) {
            // Check parent directory fallback
            csvFile = new File("../data/putusan-50.csv");
        }

        String csvPath = csvFile.getAbsolutePath();
        System.out.println("[INFO] Memuat dataset dari path: " + csvPath);
        service.loadDataFromCSV(csvPath);

        Scanner sc = new Scanner(System.in);
        boolean running = true;

        System.out.println("===============================================================");
        System.out.println("  APLIKASI MANAJEMEN PUTUSAN PIDANA KHUSUS - PN JEMBER");
        System.out.println("  Tugas UTS Struktur Data - Murni Linked List & Stack");
        System.out.println("===============================================================");

        while (running) {
            System.out.println("\n--- MENU UTAMA ---");
            System.out.println("1. Tampilkan Semua Putusan");
            System.out.println("2. Cari Putusan (by Nomor Perkara)");
            System.out.println("3. Filter Putusan (Minimal Vonis)");
            System.out.println("4. Urutkan Putusan berdasarkan Vonis");
            System.out.println("5. Tampilkan Statistik Agregasi Data");
            System.out.println("6. Tambah Data Putusan Baru");
            System.out.println("7. Hapus Data Putusan");
            System.out.println("8. Lihat Riwayat Audit System (Stack LIFO)");
            System.out.println("9. Ekspor Data Final ke CSV");
            System.out.println("0. Keluar");
            System.out.print("Pilih menu [0-9]: ");

            String input = sc.nextLine();
            switch (input) {
                case "1":
                    service.tampilkanSemua();
                    break;
                case "2":
                    System.out.print("Masukkan Nomor Perkara (contoh: 101/Pid.Sus/2026/PN Jmr): ");
                    String noCari = sc.nextLine();
                    Putusan pFound = service.cariByNomorPerkara(noCari);
                    if (pFound != null) {
                        System.out.println("\n[DITEMUKAN] " + pFound.toString());
                        System.out.println("  Barang Bukti : " + pFound.getBarangBukti());
                        System.out.println("  Hakim        : " + pFound.getNamaHakim());
                        System.out.println("  Jaksa        : " + pFound.getNamaJaksa());
                    } else {
                        System.out.println("[TIDAK DITEMUKAN] Perkara nomor " + noCari + " tidak ada.");
                    }
                    break;
                case "3":
                    System.out.print("Masukkan Minimal Vonis (Bulan): ");
                    try {
                        int minV = Integer.parseInt(sc.nextLine());
                        SinglyLinkedList<Putusan> filtered = service.filterByVonisMin(minV);
                        System.out.println("\nHasil Filter (Vonis >= " + minV + " bulan):");
                        Node<Putusan> curr = filtered.getHead();
                        while (curr != null) {
                            System.out.println(" - " + curr.getData().toString());
                            curr = curr.getNext();
                        }
                    } catch (Exception e) {
                        System.out.println("Input tidak valid!");
                    }
                    break;
                case "4":
                    System.out.print("Urutkan: 1. Ascending (Terkecil) | 2. Descending (Terbesar): ");
                    String optSort = sc.nextLine();
                    boolean asc = optSort.equals("1");
                    service.urutkanByVonis(asc);
                    System.out.println("Data berhasil diurutkan!");
                    service.tampilkanSemua();
                    break;
                case "5":
                    service.cetakStatistik();
                    break;
                case "6":
                    System.out.print("Nomor Perkara: "); String no = sc.nextLine();
                    System.out.print("Nama Terdakwa: "); String nm = sc.nextLine();
                    System.out.print("Vonis (Bulan): "); int v = Integer.parseInt(sc.nextLine());
                    System.out.print("Tuntutan (Bulan): "); int t = Integer.parseInt(sc.nextLine());
                    System.out.print("Denda (Rp): "); long d = Long.parseLong(sc.nextLine());
                    System.out.print("Pasal: "); String pas = sc.nextLine();
                    Putusan pBaru = new Putusan(no, nm, "Narkotika", pas, t, v, d, 3, "Sabu-sabu", "Ditahan", "PN", "Hakim M", "Jaksa N", "2026-03-01", "Inkracht", "Baru");
                    service.tambahPutusan(pBaru);
                    System.out.println("Putusan baru berhasil ditambahkan!");
                    break;
                case "7":
                    System.out.print("Masukkan Nomor Perkara yang akan dihapus: ");
                    String noHapus = sc.nextLine();
                    boolean h = service.hapusPutusan(noHapus);
                    if (h) System.out.println("Perkara " + noHapus + " berhasil dihapus.");
                    else System.out.println("Perkara tidak ditemukan.");
                    break;
                case "8":
                    auditManager.cetakAuditHistory();
                    break;
                case "9":
                    File exportFile = new File(csvFile.getParentFile(), "putusan-50-export.csv");
                    CSVService.exportToCSV(service.getListPutusan(), exportFile.getAbsolutePath());
                    break;
                case "0":
                    running = false;
                    System.out.println("Terima kasih. Program selesai.");
                    break;
                default:
                    System.out.println("Pilihan tidak valid!");
            }
        }
    }
}
