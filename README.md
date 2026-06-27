[![Review Assignment Due Date](https://classroom.github.com/assets/deadline-readme-button-22041afd0340ce965d47ae6ef1cefeee28c7c493a6346c4f15d667ab976d596c.svg)](https://classroom.github.com/a/y0V6Mx5M)

# PBO Kelompok 2

Studi kasus yang dipilih: **Kasus 2 - Platform Layanan Terpadu Kampus (Campus-Jek)**.

Dokumen pembagian tugas dapat dilihat di [docs/pembagian-tugas-kelompok-2.md](docs/pembagian-tugas-kelompok-2.md).

Panduan penggunaan branch, commit, push, dan pull request dapat dilihat di [docs/panduan-git-branch-pr.md](docs/panduan-git-branch-pr.md).

Penjelasan base program Kasus 2 dapat dilihat di [docs/base-program-kasus-2.md](docs/base-program-kasus-2.md).

Paket belajar dan tugas praktis setiap anggota dapat dilihat di [docs/paket-belajar-anggota.md](docs/paket-belajar-anggota.md).

## Fitur Program

- Input data mahasiswa dari keyboard.
- Memilih layanan transportasi, makanan, dan cetak tugas yang tersedia lewat menu.
- Melihat riwayat pesanan mahasiswa lewat submenu riwayat.
- Melihat semua riwayat atau riwayat berdasarkan kategori layanan.
- Menghitung tarif otomatis sesuai jenis layanan.
- Menyimpan semua pesanan dalam `RiwayatHarian` berbasis `List<Layanan>`.
- Menyimpan profil dan riwayat mahasiswa ke file JSON di folder `data/`.
- Memuat ulang riwayat lama ketika NIM yang sama dimasukkan lagi.
- Mencetak satu struk pembayaran akhir berisi semua pesanan dan total tagihan hari itu.

## Cara Menjalankan Program

Compile:

```bash
javac -d out src/*.java
```

Run:

```bash
java -cp out Main
```

Setelah program berjalan, isi data mahasiswa lalu pilih menu layanan yang ingin ditambahkan.
