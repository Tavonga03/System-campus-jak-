// class Transportasi di UML diwakili oleh LayananTransportasi di kode ini
public class LayananTransportasi extends Layanan {
    // tujuan disimpan supaya layanan tahu lokasi akhir perjalanan
    private String tujuan;

    // jarak disimpan sebagai dasar perhitungan tarif per kilometer
    private float jarak;

    // tarifPerKm disimpan supaya tarif transportasi bisa dihitung dari jarak
    private float tarifPerKm;

    // constructor ini dipakai buat membuat pesanan transportasi
    // inputnya id, waktu, tujuan, jarak, dan tarif per kilometer
    // outputnya tidak ada karena constructor hanya mengisi data objek
    // super dipakai supaya data umum Layanan tetap diatur oleh class induk
    public LayananTransportasi(String id, String waktu, String tujuan, float jarak, float tarifPerKm) {
        // super mengisi id, nama layanan, dan waktu sesuai data umum di class Layanan
        super(id, "Transportasi Antar-Jemput", waktu);

        // tujuan disimpan supaya deskripsi layanan bisa menjelaskan arah perjalanan
        this.tujuan = tujuan;

        // jarak disimpan karena dipakai dalam rumus jarak dikali tarif per km
        this.jarak = jarak;

        // tarif per km disimpan supaya tarif bisa dihitung otomatis
        this.tarifPerKm = tarifPerKm;
    }

    // function ini dipakai buat mengambil tujuan perjalanan
    // inputnya tidak ada karena tujuan sudah tersimpan di objek
    // outputnya String berisi tujuan perjalanan
    // getter dipakai supaya atribut private bisa dibaca dari luar class
    public String getTujuan() {
        // return ini mengirim tujuan perjalanan ke pemanggil function
        return tujuan;
    }

    // function ini dipakai buat mengubah tujuan perjalanan kalau ada koreksi
    // inputnya tujuan baru dalam bentuk String
    // outputnya tidak ada karena hanya update atribut
    // setter dipakai supaya data private tetap diubah lewat method
    public void setTujuan(String tujuan) {
        // this.tujuan dipakai supaya atribut tujuan menerima nilai baru
        this.tujuan = tujuan;
    }

    // function ini dipakai buat mengambil jarak perjalanan
    // inputnya tidak ada karena jarak sudah tersimpan di objek
    // outputnya float berisi jarak perjalanan
    // getter dipakai supaya jarak bisa dibaca untuk kebutuhan lain
    public float getJarak() {
        // return ini mengirim jarak perjalanan ke pemanggil function
        return jarak;
    }

    // function ini dipakai buat mengubah jarak perjalanan
    // inputnya jarak baru dalam bentuk float
    // outputnya tidak ada karena hanya mengganti atribut
    // setter dipakai supaya jarak bisa diperbaiki kalau data berubah
    public void setJarak(float jarak) {
        // this.jarak dipakai supaya atribut jarak menerima nilai baru
        this.jarak = jarak;
    }

    // function ini dipakai buat mengambil tarif per kilometer
    // inputnya tidak ada karena tarif sudah tersimpan di objek
    // outputnya float berisi tarif per kilometer
    // getter dipakai supaya tarif bisa dicek dari luar class
    public float getTarifPerKm() {
        // return ini mengirim tarif per km ke pemanggil function
        return tarifPerKm;
    }

    // function ini dipakai buat mengubah tarif per kilometer
    // inputnya tarif baru dalam bentuk float
    // outputnya tidak ada karena hanya update atribut
    // setter dipakai supaya tarif bisa disesuaikan tanpa akses langsung
    public void setTarifPerKm(float tarifPerKm) {
        // this.tarifPerKm dipakai supaya atribut tarif menerima nilai baru
        this.tarifPerKm = tarifPerKm;
    }

    // function ini dipakai buat menghitung tarif transportasi
    // inputnya tidak ada karena jarak dan tarif per km sudah ada di objek
    // outputnya float berisi total tarif transportasi
    // perkalian dipakai karena tarif transportasi dihitung berdasarkan jarak
    @Override
    public float hitungTarif() {
        // return ini mengirim hasil jarak dikali tarif per kilometer
        return jarak * tarifPerKm;
    }

    // function ini dipakai buat membuat deskripsi transportasi
    // inputnya tidak ada karena detail diambil dari atribut objek
    // outputnya String berisi tujuan, jarak, dan tarif per km
    // deskripsi dipakai supaya riwayat dan struk mudah dibaca
    @Override
    public String getDeskripsi() {
        // return ini menggabungkan detail perjalanan dan rumus tarifnya
        return "Tujuan " + tujuan + ", " + jarak + " km x Rp" + String.format("%.0f", tarifPerKm);
    }

    // function ini dipakai buat memberi kategori layanan
    // inputnya tidak ada karena kategori transportasi sudah tetap
    // outputnya String kategori Transportasi
    // kategori dipakai untuk filter riwayat berdasarkan tipe layanan
    @Override
    public String getKategori() {
        // return ini mengirim nama kategori sesuai UML
        return "Transportasi";
    }
}
