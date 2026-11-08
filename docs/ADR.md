# Architectural Decision Record (ADR)

## ADR-001: Pemilihan Singly Linked List sebagai Struktur Data Utama
- **Status**: Accepted
- **Konteks**: Aplikasi memerlukan struktur data dinamis untuk menampung data putusan pengadilan yang dapat ditambah dan dihapus secara responsif tanpa batas alokasi array tetap.
- **Keputusan**: Mengimplementasikan `SinglyLinkedList` generik murni buatan sendiri.

## ADR-002: Pemilihan Stack untuk Audit Log
- **Status**: Accepted
- **Konteks**: Setiap aktivitas pengguna (tambah, hapus, cari, filter, urutkan) harus dicatat dan dapat dilihat dengan urutan aksi terbaru terlebih dahulu.
- **Keputusan**: Menggunakan struktur data `Stack` (LIFO) generik.
