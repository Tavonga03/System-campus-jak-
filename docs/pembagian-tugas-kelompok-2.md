# Pembagian Tugas Kelompok 2

## Identitas Kelompok

Mata kuliah: Pemrograman Berorientasi Obyek  
Studi kasus: Kasus 2 - Platform Layanan Terpadu Kampus (Campus-Jek)  
Jenis aplikasi: Console-Based Application (CLI) Java  

## Anggota Kelompok

| No | Nama | NIM | Branch | Tanggung Jawab Utama |
| --- | --- | --- | --- | --- |
| 1 | Tavonga | 255314082 | `tavonga` | Analisis kebutuhan, identifikasi entitas, dan UML class diagram |
| 2 | Hepiviani | 255314047 | `hepiviani` | Class `Mahasiswa`, class riwayat harian, dan object passing profil mahasiswa |
| 3 | Novi | 255314083 | `novi` | Implementasi layanan transportasi dan pesan-antar makanan |
| 4 | Fito | 255314084 | `fito` | Integrasi sistem di `Main`, skenario CLI, rekap tagihan, dan struk akhir |
| 5 | Vany | 255314085 | `vany` | Implementasi layanan kurir cetak tugas, uji output, dan dokumentasi README |

## Ringkasan Kasus

Campus-Jek adalah platform layanan kampus yang menyediakan tiga jenis jasa:

1. Transportasi antar-jemput, dengan tarif berdasarkan jarak per kilometer.
2. Pesan-antar makanan, dengan biaya dari harga makanan ditambah ongkos kirim restoran.
3. Kurir cetak tugas, dengan biaya dari jumlah halaman dikali harga per lembar, ditambah biaya jilid.

Sistem harus menampung semua pesanan mahasiswa dalam satu riwayat harian. Saat tagihan harian diminta, sistem membaca seluruh pesanan, menghitung tarif sesuai aturan masing-masing layanan secara otomatis, lalu mencetak satu struk pembayaran akhir.

## Rancangan Pembagian Kerja

### Tavonga - Analisis dan UML

Branch: `tavonga`

Tugas:

- Menulis deskripsi kasus Campus-Jek.
- Mengidentifikasi kandidat class dari narasi kasus.
- Menentukan abstraksi utama, misalnya `Layanan`.
- Membuat rancangan relasi class dalam UML.
- Menyimpan hasil UML ke folder `docs/`.
- Menulis bagian awal laporan: deskripsi kasus, entitas utama, dan alasan pemilihan class.

Target commit:

- `docs: menambahkan analisis entitas kasus campus-jek`
- `docs: menambahkan rancangan uml campus-jek`

### Hepiviani - Mahasiswa dan Riwayat Harian

Branch: `hepiviani`

Tugas:

- Membuat class `Mahasiswa` dengan atribut private, constructor, getter, dan setter.
- Membuat class penyimpan riwayat harian dengan `List<Layanan>` sesuai UML.
- Menyediakan method untuk menambahkan objek layanan ke dalam riwayat.
- Memastikan relasi menggunakan object passing, misalnya riwayat menerima objek `Mahasiswa`.
- Menyediakan method untuk mencari pesanan berdasarkan kategori layanan.

Target commit:

- `feat: menambahkan class mahasiswa`
- `feat: menambahkan riwayat harian berbasis array`

### Novi - Layanan Transportasi dan Makanan

Branch: `novi`

Tugas:

- Membuat class turunan untuk layanan transportasi.
- Membuat class turunan untuk layanan pesan-antar makanan.
- Mengimplementasikan method perhitungan tarif dengan overriding dari class abstrak/interface layanan.
- Menambahkan format ringkasan layanan agar bisa dicetak dalam struk.
- Menyiapkan contoh data layanan untuk diuji di `Main`.

Target commit:

- `feat: menambahkan layanan transportasi`
- `feat: menambahkan layanan pesan antar makanan`

### Fito - Integrasi Main dan Struk

Branch: `fito`

Tugas:

- Menggabungkan class dari anggota lain ke dalam skenario `Main`.
- Membuat simulasi satu mahasiswa yang memesan beberapa layanan berbeda dalam satu hari.
- Menjalankan perulangan terhadap daftar layanan untuk menunjukkan polymorphism.
- Menghitung total tagihan tanpa pengecekan `if-else` berdasarkan jenis layanan.
- Mencetak struk akhir berisi identitas mahasiswa, daftar pesanan, subtotal tiap layanan, dan total pembayaran.

Target commit:

- `feat: mengintegrasikan skenario main campus-jek`
- `feat: menambahkan output struk pembayaran`

### Vany - Layanan Cetak dan Dokumentasi

Branch: `vany`

Tugas:

- Membuat class turunan untuk layanan kurir cetak tugas.
- Mengimplementasikan perhitungan tarif cetak berdasarkan jumlah halaman, harga per lembar, dan biaya jilid.
- Membantu pengujian output dari tiga jenis layanan.
- Menulis dokumentasi README bagian implementasi OOP, `List`, dan polymorphism.
- Menyiapkan tangkapan layar output program untuk laporan.

Target commit:

- `feat: menambahkan layanan kurir cetak tugas`
- `docs: menjelaskan implementasi oop dan polymorphism`

## Aturan Branch dan Pengumpulan

1. Setiap anggota membuat branch sesuai pembagian di atas dari branch `main`.
2. Setiap perubahan dibuat dalam commit kecil dan deskriptif.
3. Setiap anggota mengerjakan bagiannya di branch masing-masing: `tavonga`, `hepiviani`, `novi`, `fito`, dan `vany`.
4. Semua hasil akhir dikumpulkan dengan cara menggabungkan branch masing-masing ke branch `main`.
5. Branch dokumentasi dan class dasar sebaiknya digabung lebih awal agar anggota lain punya acuan.
6. Branch `fito` sebaiknya digabung terakhir karena berisi integrasi `Main` dan struk akhir.
7. Sebelum digabung ke `main`, pastikan program bisa dikompilasi dan dijalankan.

## Checklist Kesesuaian Tugas

| Kriteria | Penanggung Jawab Utama | Status |
| --- | --- | --- |
| Encapsulation pada semua class | Semua anggota | Belum dikerjakan |
| Abstract class/interface layanan | Tavonga, Fito | Belum dikerjakan |
| Inheritance layanan spesifik | Novi, Vany | Belum dikerjakan |
| Method overriding perhitungan tarif | Novi, Vany | Belum dikerjakan |
| Object passing mahasiswa dan riwayat | Hepiviani, Fito | Belum dikerjakan |
| `List<Layanan>` untuk riwayat layanan | Hepiviani | Belum dikerjakan |
| Looping polymorphism di `Main` | Fito | Belum dikerjakan |
| README dan laporan teknis | Tavonga, Vany | Belum dikerjakan |

## Usulan Struktur Class

Struktur ini hanya gambaran saja, dapat disesuaikan saat implementasi:

```text
src/
  Main.java
  Mahasiswa.java
  RiwayatHarian.java
  Layanan.java
  LayananTransportasi.java
  PesananMakanan.java
  LayananCetakTugas.java
  Struk.java
```

Catatan: `Layanan` dapat dibuat sebagai abstract class atau interface. Class `RiwayatHarian` menyimpan objek turunan `Layanan` dalam `List<Layanan>` agar satu penyimpanan dapat menampung berbagai jenis layanan sesuai UML.
