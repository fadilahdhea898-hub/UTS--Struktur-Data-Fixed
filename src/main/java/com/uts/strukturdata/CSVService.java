package com.uts.strukturdata;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

// Layanan pembacaan dan penulisan dataset putusan format CSV (UTF-8)
public class CSVService {

    public static SinglyLinkedList<Putusan> loadPutusanFromCSV(String filePath) {
        SinglyLinkedList<Putusan> list = new SinglyLinkedList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            boolean firstLine = true;
            while ((line = br.readLine()) != null) {
                if (firstLine) {
                    firstLine = false;
                    continue; // Skip header
                }
                String[] cols = parseCSVLine(line);
                if (cols.length >= 16) {
                    String nomorPerkara = cols[0].trim();
                    String namaTerdakwa = cols[1].trim();
                    String jenisKejahatan = cols[2].trim();
                    String pasal = cols[3].trim();
                    int tuntutanBulan = Integer.parseInt(cols[4].trim());
                    int vonisBulan = Integer.parseInt(cols[5].trim());
                    long denda = Long.parseLong(cols[6].trim());
                    int subsidairBulan = Integer.parseInt(cols[7].trim());
                    String barangBukti = cols[8].trim();
                    String statusPenahanan = cols[9].trim();
                    String tingkatPengadilan = cols[10].trim();
                    String namaHakim = cols[11].trim();
                    String namaJaksa = cols[12].trim();
                    String tanggalPutusan = cols[13].trim();
                    String statusEksekusi = cols[14].trim();
                    String catatan = cols[15].trim();

                    Putusan p = new Putusan(nomorPerkara, namaTerdakwa, jenisKejahatan, pasal,
                            tuntutanBulan, vonisBulan, denda, subsidairBulan, barangBukti,
                            statusPenahanan, tingkatPengadilan, namaHakim, namaJaksa,
                            tanggalPutusan, statusEksekusi, catatan);
                    list.add(p);
                }
            }
        } catch (IOException e) {
            System.err.println("Gagal membaca file CSV: " + e.getMessage());
        }
        return list;
    }

    public static void exportToCSV(SinglyLinkedList<Putusan> list, String filePath) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(filePath))) {
            pw.println("nomor_perkara,nama_terdakwa,jenis_kejahatan,pasal,tuntutan_bulan,vonis_bulan,denda,subsidair_bulan,barang_bukti,status_penahanan,tingkat_pengadilan,nama_hakim,nama_jaksa,tanggal_putusan,status_eksekusi,catatan");
            Node<Putusan> curr = list.getHead();
            while (curr != null) {
                Putusan p = curr.getData();
                pw.printf("\"%s\",\"%s\",\"%s\",\"%s\",%d,%d,%d,%d,\"%s\",\"%s\",\"%s\",\"%s\",\"%s\",\"%s\",\"%s\",\"%s\"\n",
                        p.getNomorPerkara(), p.getNamaTerdakwa(), p.getJenisKejahatan(), p.getPasal(),
                        p.getTuntutanBulan(), p.getVonisBulan(), p.getDenda(), p.getSubsidairBulan(),
                        p.getBarangBukti(), p.getStatusPenahanan(), p.getTingkatPengadilan(), p.getNamaHakim(),
                        p.getNamaJaksa(), p.getTanggalPutusan(), p.getStatusEksekusi(), p.getCatatan());
                curr = curr.getNext();
            }
            System.out.println("Ekspor CSV berhasil disimpan ke: " + filePath);
        } catch (IOException e) {
            System.err.println("Gagal mengekspor CSV: " + e.getMessage());
        }
    }

    private static String[] parseCSVLine(String line) {
        // Basic CSV Parser for double quotes
        java.util.List<String> result = new java.util.ArrayList<>();
        boolean inQuotes = false;
        StringBuilder sb = new StringBuilder();
        for (char c : line.toCharArray()) {
            if (c == '"') {
                inQuotes = !inQuotes;
            } else if (c == ',' && !inQuotes) {
                result.add(sb.toString());
                sb.setLength(0);
            } else {
                sb.append(c);
            }
        }
        result.add(sb.toString());
        return result.toArray(new String[0]);
    }
}
