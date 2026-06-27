# Panduan Git Branch, Commit, Push, dan Pull Request

Dokumen ini digunakan oleh Kelompok 2 untuk mengerjakan tugas melalui branch masing-masing, lalu mengumpulkan hasil akhir ke branch `main`.

## Daftar Branch Anggota

| Nama | Branch |
| --- | --- |
| Tavonga | `tavonga` |
| Hepiviani | `hepiviani` |
| Novi | `novi` |
| Fito | `fito` |
| Vany | `vany` |

## 1. Ambil Update Terbaru dari Main

Sebelum mulai kerja, pastikan posisi lokal sudah mengikuti branch `main` terbaru.

```bash
git checkout main
git pull origin main
```

## 2. Membuat Branch Sendiri

Gunakan nama branch sesuai nama masing-masing.

Contoh untuk saya Fito:

```bash
git checkout -b fito
```

Contoh untuk kalian:

```bash
git checkout -b tavonga
git checkout -b hepiviani
git checkout -b novi
git checkout -b vany
```

Catatan: perintah `git checkout -b nama-branch` hanya dipakai saat branch belum pernah dibuat.

## 3. Pindah ke Branch Sendiri

Jika branch sudah pernah dibuat, cukup pindah dengan perintah:

```bash
git checkout fito
```

Ganti `fito` dengan nama branch masing-masing.

## 4. Cek Status Perubahan

Setelah mengubah file, cek file yang berubah:

```bash
git status
```

## 5. Menambahkan File ke Commit

Tambahkan file yang ingin dimasukkan ke commit.

Untuk menambahkan semua perubahan:

```bash
git add .
```

Untuk menambahkan file tertentu:

```bash
git add nama-file.java
```

Contoh:

```bash
git add src/Main.java
git add docs/pembagian-tugas-kelompok-2.md
```

## 6. Membuat Commit

Gunakan pesan commit yang jelas dan singkat.

```bash
git commit -m "feat: menambahkan class mahasiswa"
```

Contoh pesan commit:

```bash
git commit -m "docs: menambahkan analisis entitas campus-jek"
git commit -m "feat: menambahkan layanan transportasi"
git commit -m "feat: menambahkan riwayat harian berbasis array"
git commit -m "fix: memperbaiki perhitungan total tagihan"
```

## 7. Push Branch ke GitHub

Untuk push pertama kali:

```bash
git push -u origin fito
```

Ganti `fito` dengan nama branch masing-masing.

Contoh:

```bash
git push -u origin tavonga
git push -u origin hepiviani
git push -u origin novi
git push -u origin vany
```

Untuk push berikutnya dari branch yang sama, cukup gunakan:

```bash
git push
```

## 8. Membuat Pull Request ke Main

Setelah branch sudah di-push ke GitHub:

1. Buka repository GitHub kelompok.
2. Klik tab **Pull requests**.
3. Klik tombol **New pull request**.
4. Pastikan target penggabungan:
   - base: `main`
   - compare: branch sendiri, misalnya `fito`
5. Klik **Create pull request**.
6. Isi judul pull request dengan jelas.
7. Tulis ringkasan perubahan.
8. Klik **Create pull request**.

Contoh judul pull request:

```text
Menambahkan integrasi Main dan struk pembayaran
```

Contoh isi deskripsi pull request:

```text
Perubahan:
- Menambahkan skenario utama Campus-Jek di Main
- Menambahkan perhitungan total tagihan
- Menambahkan output struk pembayaran
```

## 9. Merge Pull Request ke Main

Sebelum merge:

1. Pastikan tidak ada konflik.
2. Pastikan program bisa dikompilasi.
3. Pastikan perubahan sudah sesuai pembagian tugas.

Jika sudah aman:

1. Klik **Merge pull request**.
2. Klik **Confirm merge**.
3. Setelah merge, branch boleh tetap disimpan sebagai riwayat kerja.

## 10. Mengambil Update Main Setelah Merge

Setelah ada branch teman yang sudah digabung ke `main`, semua anggota sebaiknya mengambil update terbaru.

```bash
git checkout main
git pull origin main
git checkout nama-branch-sendiri
git merge main
```

Contoh untuk Fito:

```bash
git checkout main
git pull origin main
git checkout fito
git merge main
```

## 11. Alur Singkat Harian

Gunakan urutan ini saat mengerjakan tugas:

```bash
git checkout main
git pull origin main
git checkout nama-branch-sendiri
git merge main
git status
git add .
git commit -m "pesan commit"
git push
```

## Catatan Penting

- Jangan mengerjakan langsung di branch `main`.
- Jangan menggunakan pesan commit yang terlalu umum seperti `update`, `fix`, atau `coba`.
- Gunakan branch sesuai nama masing-masing.
- Semua hasil akhir tetap harus masuk ke branch `main`.
- Jika ada konflik saat merge, diskusikan dengan anggota yang mengerjakan file terkait sebelum mengubah kode.
