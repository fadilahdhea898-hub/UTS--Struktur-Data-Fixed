package com.uts.strukturdata;

public class Putusan {
    private String nomorPerkara;
    private String namaTerdakwa;
    private String jenisKejahatan;
    private String pasal;
    private int tuntutanBulan;
    private int vonisBulan;
    private long denda;
    private int subsidairBulan;
    private String barangBukti;
    private String statusPenahanan;
    private String tingkatPengadilan;
    private String namaHakim;
    private String namaJaksa;
    private String tanggalPutusan;
    private String statusEksekusi;
    private String catatan;

    public Putusan(String nomorPerkara, String namaTerdakwa, String jenisKejahatan, String pasal,
                   int tuntutanBulan, int vonisBulan, long denda, int subsidairBulan,
                   String barangBukti, String statusPenahanan, String tingkatPengadilan,
                   String namaHakim, String namaJaksa, String tanggalPutusan,
                   String statusEksekusi, String catatan) {
        this.nomorPerkara = nomorPerkara;
        this.namaTerdakwa = namaTerdakwa;
        this.jenisKejahatan = jenisKejahatan;
        this.pasal = pasal;
        this.tuntutanBulan = tuntutanBulan;
        this.vonisBulan = vonisBulan;
        this.denda = denda;
        this.subsidairBulan = subsidairBulan;
        this.barangBukti = barangBukti;
        this.statusPenahanan = statusPenahanan;
        this.tingkatPengadilan = tingkatPengadilan;
        this.namaHakim = namaHakim;
        this.namaJaksa = namaJaksa;
        this.tanggalPutusan = tanggalPutusan;
        this.statusEksekusi = statusEksekusi;
        this.catatan = catatan;
    }

    // Getters
    public String getNomorPerkara() { return nomorPerkara; }
    public String getNamaTerdakwa() { return namaTerdakwa; }
    public String getJenisKejahatan() { return jenisKejahatan; }
    public String getPasal() { return pasal; }
    public int getTuntutanBulan() { return tuntutanBulan; }
    public int getVonisBulan() { return vonisBulan; }
    public long getDenda() { return denda; }
    public int getSubsidairBulan() { return subsidairBulan; }
    public String getBarangBukti() { return barangBukti; }
    public String getStatusPenahanan() { return statusPenahanan; }
    public String getTingkatPengadilan() { return tingkatPengadilan; }
    public String getNamaHakim() { return namaHakim; }
    public String getNamaJaksa() { return namaJaksa; }
    public String getTanggalPutusan() { return tanggalPutusan; }
    public String getStatusEksekusi() { return statusEksekusi; }
    public String getCatatan() { return catatan; }

    @Override
    public String toString() {
        return String.format("%s | %s | Vonis: %d Bln | Tuntutan: %d Bln | Denda: Rp %,d | Pasal: %s",
                nomorPerkara, namaTerdakwa, vonisBulan, tuntutanBulan, denda, pasal);
    }
}
