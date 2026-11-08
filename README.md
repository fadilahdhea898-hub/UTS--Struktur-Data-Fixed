# UTS Struktur Data - Aplikasi Manajemen Putusan Pidana Khusus PN Jember

## Deskripsi Proyek
Aplikasi ini dikembangkan untuk mengelola, menganalisis, menyaring, dan mengekspor 50 data putusan pidana khusus (Narkotika) Pengadilan Negeri Jember.

## Ketentuan & Struktur Data
- **Struktur Data Utama**: `SinglyLinkedList` (Dibuat secara mandiri tanpa library koleksi bawaan Java `java.util`).
- **Struktur Data Sekunder**: `Stack` (Digunakan untuk manajemen `AuditLog` secara LIFO).
- **Atribut Putusan**: 16 Atribut terverifikasi.
- **Provenance**: Documented provenance untuk 800 atribut ($50 \times 16$).

## Struktur Folder Project
```text
UTS-Struktur-Data/
├── pom.xml
├── README.md
├── docs/
│   ├── ADR.md
│   ├── TRACE.md
│   ├── AI_USAGE.md
│   ├── CONTRIBUTIONS.md
│   └── data-provenance.csv
├── data/
│   └── putusan-50.csv
└── src/
    ├── main/java/com/uts/strukturdata/
    │   ├── Node.java
    │   ├── SinglyLinkedList.java
    │   ├── Stack.java
    │   ├── Putusan.java
    │   ├── AuditLog.java
    │   ├── AuditManager.java
    │   ├── CSVService.java
    │   ├── PutusanService.java
    │   └── Main.java
    └── test/java/com/uts/strukturdata/
        └── PutusanTest.java
```

## Cara Menjalankan Aplikasi
1. Buka folder `UTS-Struktur-Data` di IntelliJ IDEA atau IDE Java pilihan Anda.
2. Pastikan Java SDK 11+ telah dikonfigurasi.
3. Jalankan `Main.java` untuk memulai CLI Interaktif.
4. Untuk menjalankan Unit Test:
   ```bash
   mvn test
   ```

## Skenario Pengujian yang Lolos
1. Pengujian pencarian non-destruktif (SinglyLinkedList tetap utuh).
2. Pengujian fungsi Stack Audit Log LIFO.
3. Pengujian Invariant & Penghapusan Elemen.
4. Pengujian Agregasi & Pengurutan Merge Sort pada Linked List.
