// class PesananMakanan dipakai buat layanan pesan-antar makanan sesuai UML
public class PesananMakanan extends Layanan {
    // namaItem disimpan supaya makanan yang dipesan bisa ditampilkan
    private String namaItem;

    // hargaMakanan disimpan sebagai biaya utama dari pesanan makanan
    private float hargaMakanan;

    // ongkosKirim disimpan sebagai biaya tambahan dari restoran
    private float ongkosKirim;

    // constructor ini dipakai buat membuat pesanan makanan
    // inputnya id, waktu, nama item, harga makanan, dan ongkos kirim
    // outputnya tidak ada karena constructor hanya mengisi data objek
    // super dipakai supaya id, nama layanan, dan waktu tetap diatur class Layanan
    public PesananMakanan(String id, String waktu, String namaItem, float hargaMakanan, float ongkosKirim) {
        // super mengisi data umum layanan makanan
        super(id, "Pesan-Antar Makanan", waktu);

        // nama item disimpan supaya struk tahu makanan apa yang dipesan
        this.namaItem = namaItem;

        // harga makanan disimpan karena masuk ke rumus total makanan
        this.hargaMakanan = hargaMakanan;

        // ongkos kirim disimpan karena harus ditambahkan ke harga makanan
        this.ongkosKirim = ongkosKirim;
    }

    // function ini dipakai buat mengambil nama item makanan
    // inputnya tidak ada karena nama item sudah tersimpan
    // outputnya String berisi nama makanan
    // getter dipakai supaya atribut private bisa dibaca dari luar class
    public String getNamaItem() {
        // return ini mengirim nama item makanan ke pemanggil function
        return namaItem;
    }

    // function ini dipakai buat mengubah nama item makanan
    // inputnya nama item baru dalam bentuk String
    // outputnya tidak ada karena hanya update atribut
    // setter dipakai supaya perubahan data tetap lewat method
    public void setNamaItem(String namaItem) {
        // this.namaItem dipakai supaya atribut nama item menerima nilai baru
        this.namaItem = namaItem;
    }

    // function ini dipakai buat mengambil harga makanan
    // inputnya tidak ada karena harga sudah tersimpan di objek
    // outputnya float berisi harga makanan
    // getter dipakai supaya harga bisa dicek dari luar class
    public float getHargaMakanan() {
        // return ini mengirim harga makanan ke bagian yang membutuhkan
        return hargaMakanan;
    }

    // function ini dipakai buat mengubah harga makanan
    // inputnya harga baru dalam bentuk float
    // outputnya tidak ada karena hanya update atribut
    // setter dipakai supaya harga bisa diperbaiki kalau ada perubahan
    public void setHargaMakanan(float hargaMakanan) {
        // this.hargaMakanan dipakai supaya atribut harga menerima nilai baru
        this.hargaMakanan = hargaMakanan;
    }

    // function ini dipakai buat mengambil ongkos kirim
    // inputnya tidak ada karena ongkir sudah tersimpan di objek
    // outputnya float berisi ongkos kirim
    // getter dipakai supaya ongkir bisa dibaca oleh bagian lain
    public float getOngkosKirim() {
        // return ini mengirim ongkos kirim ke pemanggil function
        return ongkosKirim;
    }

    // function ini dipakai buat mengubah ongkos kirim
    // inputnya ongkos kirim baru dalam bentuk float
    // outputnya tidak ada karena hanya mengganti atribut
    // setter dipakai supaya perubahan ongkir tetap lewat method
    public void setOngkosKirim(float ongkosKirim) {
        // this.ongkosKirim dipakai supaya atribut ongkir menerima nilai baru
        this.ongkosKirim = ongkosKirim;
    }

    // function ini dipakai buat menghitung tarif pesanan makanan
    // inputnya tidak ada karena harga dan ongkir sudah ada di objek
    // outputnya float berisi total biaya makanan
    // penjumlahan dipakai karena total makanan adalah harga makanan ditambah ongkos kirim
    @Override
    public float hitungTarif() {
        // return ini mengirim hasil harga makanan ditambah ongkos kirim
        return hargaMakanan + ongkosKirim;
    }

    // function ini dipakai buat membuat deskripsi pesanan makanan
    // inputnya tidak ada karena detail diambil dari atribut objek
    // outputnya String berisi nama item, harga makanan, dan ongkos kirim
    // deskripsi dipakai supaya riwayat dan struk menjelaskan asal tarifnya
    @Override
    public String getDeskripsi() {
        // return ini menggabungkan detail makanan dalam satu kalimat
        return namaItem + ", makanan Rp" + String.format("%.0f", hargaMakanan)
                + " + ongkir Rp" + String.format("%.0f", ongkosKirim);
    }

    // function ini dipakai buat memberi kategori layanan makanan
    // inputnya tidak ada karena kategori pesanan makanan sudah tetap
    // outputnya String kategori PesananMakanan
    // kategori dipakai saat riwayat difilter berdasarkan tipe layanan
    @Override
    public String getKategori() {
        // return ini mengirim nama kategori sesuai UML
        return "PesananMakanan";
    }
}
