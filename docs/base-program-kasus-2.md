# Base Program Kasus 2 - Campus-Jek

Dokumen ini menjelaskan struktur program Java untuk studi kasus 2. Versi ini sudah disesuaikan dengan UML di folder `uml/`, jadi program punya `Mahasiswa`, `RiwayatHarian`, `Layanan`, tiga turunan layanan, dan `Struk`.

## Struktur File

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
  DataMahasiswa.java
  PenyimpananJson.java
```

## Peran Setiap Class

| Class | Peran |
| --- | --- |
| `Main` | Menjalankan menu CLI Campus-Jek |
| `Mahasiswa` | Menyimpan profil mahasiswa: nama, nim, prodi, dan angkatan |
| `RiwayatHarian` | Menyimpan semua pesanan mahasiswa dalam `List<Layanan>` |
| `Layanan` | Abstract class untuk semua jenis layanan |
| `LayananTransportasi` | Menghitung tarif transportasi berdasarkan jarak dan tarif per km |
| `PesananMakanan` | Menghitung tarif makanan dari harga makanan ditambah ongkos kirim |
| `LayananCetakTugas` | Menghitung tarif cetak dari halaman, harga per lembar, dan biaya jilid |
| `Struk` | Menerbitkan struk pembayaran dari riwayat harian |
| `DataMahasiswa` | Membungkus objek `Mahasiswa` dan `RiwayatHarian` saat data dimuat |
| `PenyimpananJson` | Menyimpan dan memuat data mahasiswa ke file JSON |

## Konsep OOP yang Ditunjukkan

### Encapsulation

Setiap atribut dibuat `private`, lalu diakses melalui getter dan setter.

Contoh:

```java
private String nama;

public String getNama() {
    return nama;
}
```

### Abstraction

Class `Layanan` dibuat abstract karena semua layanan punya data umum, tetapi cara menghitung tarifnya berbeda.

```java
public abstract float hitungTarif();
public abstract String getDeskripsi();
public abstract String getKategori();
```

### Inheritance

Class layanan spesifik mewarisi class `Layanan`.

```java
public class LayananTransportasi extends Layanan
```

### Polymorphism

`RiwayatHarian` menyimpan semua jenis layanan dalam `List<Layanan>`.

```java
private List<Layanan> pesanan;
```

Saat menghitung total, sistem cukup memanggil:

```java
total += layanan.hitungTarif();
```

Java akan menjalankan versi `hitungTarif()` sesuai objek aslinya, misalnya transportasi, makanan, atau cetak tugas.

### Object Passing

Class `RiwayatHarian` menerima objek `Layanan`, bukan hanya teks atau angka.

```java
public void tambahPesanan(Layanan layanan) {
    pesanan.add(layanan);
}
```

Class `Struk` juga menerima objek `RiwayatHarian`, supaya struk bisa membaca mahasiswa, tanggal, daftar pesanan, dan total tagihan.

## Penyimpanan JSON

Program tidak memakai database. Data mahasiswa dan riwayat disimpan ke file JSON di folder `data/`.

Contoh:

```text
data/255314084.json
```

Saat user memasukkan NIM yang sama, program akan mencoba membaca file JSON tersebut. Kalau file ditemukan, profil dan riwayat lama akan dimuat lagi.

## Cara Compile dan Run

Compile:

```bash
javac -d out src/*.java
```

Run:

```bash
java -cp out Main
```
