# Paket Belajar Anggota - Campus-Jek

Teman-teman, aku susun catatan ini biar kerja kelompok kita lebih jelas. Aku tahu tidak semua dari kita sudah terbiasa ngoding, jadi pembagiannya aku buat pelan-pelan: tiap orang pegang bagian kecil, baca kodenya, coba pahami alurnya, lalu bisa jelasin bagian masing-masing waktu ditanya.

Yang penting bukan langsung jago, tapi kita sama-sama ngerti project yang kita kumpulkan. Kalau ada bagian yang bingung, langsung tanya di grup. Nanti kita bahas bareng supaya tidak ada yang cuma ikut nama tanpa paham apa yang dikerjakan.

Programnya sekarang sudah dibuat dynamic dan disesuaikan dengan UML. Nanti saat program dijalankan, user input NIM, program akan cek file JSON di folder `data/`, lalu profil dan riwayat lama bisa dimuat lagi kalau NIM tersebut sudah pernah dipakai.

## Cara Kerja Kita

1. Tiap anggota fokus dulu ke file yang sesuai bagiannya.
2. Baca komentar di kode sebelum mengubah isi program.
3. Kalau mau ubah contoh data, ubah sedikit dulu supaya gampang dicek.
4. Setelah mengubah kode, wajib compile dan run program.
5. Kalau error, kirim screenshot error dan sebutkan file mana yang diubah.
6. File JSON di folder `data/` hanya untuk penyimpanan lokal saat program dicoba.

Perintah yang kita pakai buat ngecek program:

```bash
javac -d out src/*.java
java -cp out Main
```

## Tavonga - Analisis dan UML

Bagian yang perlu dibaca:

- `docs/base-program-kasus-2.md`
- `docs/pembagian-tugas-kelompok-2.md`
- `src/Layanan.java`
- `src/Mahasiswa.java`
- `src/RiwayatHarian.java`

Yang dikerjakan:

- Buat UML dari class yang sudah ada di folder `src`.
- Jelaskan hubungan `Layanan` dengan `LayananTransportasi`, `PesananMakanan`, dan `LayananCetakTugas`.
- Tulis alasan kenapa `Layanan` dibuat sebagai abstract class.
- Tulis contoh encapsulation dari atribut yang dibuat `private`.

Bahan buat latihan jelasin:

- Kenapa `Layanan` tidak langsung dibuat objeknya di `Main`?
- Apa hubungan `LayananTransportasi` dengan `Layanan`?
- Kenapa atribut di class dibuat `private`?

Yang perlu dikumpulkan:

- File UML atau gambar UML di folder `docs/`.
- Penjelasan singkat 1-2 paragraf tentang relasi class.

## Hepiviani - Mahasiswa dan Riwayat Harian

Bagian yang perlu dibaca:

- `src/Mahasiswa.java`
- `src/RiwayatHarian.java`

Yang dikerjakan:

- Pahami fungsi constructor, getter, dan setter di `Mahasiswa`.
- Jelaskan kenapa `Mahasiswa` punya method `getDeskripsi()`.
- Jelaskan kenapa `RiwayatHarian` menyimpan data pakai `List<Layanan>`.
- Jelaskan fungsi `getPesananByType(String)` untuk melihat riwayat berdasarkan kategori.
- Coba tambah beberapa pesanan lewat menu, lalu cek apakah semuanya masuk ke struk.

Bahan buat latihan jelasin:

- Method `tambahPesanan(Layanan)` dipakai untuk apa?
- Kenapa `hitungTotal()` cukup memanggil `hitungTarif()` dari setiap layanan?
- Kenapa `getPesananByType(String)` butuh kategori dari setiap layanan?

Yang perlu dikumpulkan:

- Catatan singkat tentang `Mahasiswa` dan `RiwayatHarian`.
- Screenshot hasil program setelah berhasil dijalankan.

## Novi - Layanan Transportasi dan Makanan

Bagian yang perlu dibaca:

- `src/Layanan.java`
- `src/LayananTransportasi.java`
- `src/PesananMakanan.java`
- `src/Main.java`

Yang dikerjakan:

- Pahami rumus tarif transportasi.
- Pahami rumus tarif makanan.
- Coba ubah contoh data transportasi atau makanan di `Main`.
- Jalankan program dan cek apakah total tagihan ikut berubah.

Contoh bagian yang boleh dicoba:

```java
// angka jarak boleh diganti buat melihat perubahan tarif transportasi
6,

// harga makanan boleh diganti buat melihat perubahan tarif makanan
18000,
```

Bahan buat latihan jelasin:

- Kenapa `hitungTarif()` di transportasi pakai perkalian?
- Kenapa `hitungTarif()` di makanan pakai penjumlahan?
- Apa maksud `@Override`?

Yang perlu dikumpulkan:

- Penjelasan singkat rumus transportasi dan makanan.
- Screenshot output sebelum dan sesudah contoh data diubah.

## Fito - Integrasi Main dan Struk

Bagian yang perlu dibaca:

- `src/Main.java`
- `src/RiwayatHarian.java`
- Semua file layanan di folder `src/`

Yang aku kerjakan:

- Menggabungkan semua class supaya bisa jalan dari `Main`.
- Membuat menu input supaya program bisa dipakai secara dynamic.
- Membuat submenu riwayat supaya pesanan bisa dicek sebelum struk dicetak.
- Membuat objek `Struk` supaya riwayat harian bisa menghasilkan satu struk pembayaran akhir.
- Menyimpan data mahasiswa dan riwayat ke JSON lewat `PenyimpananJson`.
- Mengecek total tagihan akhir.
- Merapikan format struk.
- Memastikan program bisa compile dan run sebelum dikumpulkan.

Bahan buat latihan jelasin:

- Kenapa di `Main` tipe variabelnya bisa ditulis `Layanan` padahal objeknya `LayananTransportasi`?
- Bagian mana yang menunjukkan polymorphism?
- Kenapa cetak struk dibuat di class `Struk`, bukan semuanya di `Main`?

Yang perlu aku siapkan:

- Program final yang bisa compile dan run.
- Screenshot riwayat pesanan dan struk total harian.
- Screenshot saat NIM lama berhasil memuat riwayat dari JSON.

## Vany - Layanan Cetak Tugas dan Dokumentasi

Bagian yang perlu dibaca:

- `src/Layanan.java`
- `src/LayananCetakTugas.java`
- `README.md`
- `docs/base-program-kasus-2.md`

Yang dikerjakan:

- Pahami rumus biaya cetak tugas.
- Coba ubah contoh data cetak tugas di `Main`, misalnya jumlah halaman atau biaya jilid.
- Bantu rapikan README bagian cara menjalankan program.
- Siapkan screenshot output program.

Bahan buat latihan jelasin:

- Kenapa biaya cetak dihitung dari halaman dikali harga per lembar?
- Kenapa biaya jilid ditambahkan setelah biaya halaman?
- Apa fungsi `getDeskripsi()`?

Yang perlu dikumpulkan:

- Penjelasan singkat layanan cetak tugas.
- README yang sudah rapi.
- Screenshot hasil program.

## Checklist Sebelum Dikumpulkan

- Program berhasil di-compile dengan `javac -d out src/*.java`.
- Program berhasil dijalankan dengan `java -cp out Main`.
- Setiap anggota bisa menjelaskan file atau bagian yang dipegang.
- Struk dicetak saat mahasiswa ingin melihat tagihan total hari itu.
- Struk menampilkan identitas mahasiswa, tanggal order, tanggal cetak, semua layanan, dan total tagihan harian.
- Konsep OOP yang bisa kita tunjukkan: encapsulation, abstraction, inheritance, polymorphism, object passing, dan relasi antar-class sesuai UML.
