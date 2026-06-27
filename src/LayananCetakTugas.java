// class CetakTugas di UML diwakili oleh LayananCetakTugas di kode ini
public class LayananCetakTugas extends Layanan {
    // namaDokumen disimpan supaya struk tahu tugas apa yang dicetak
    private String namaDokumen;

    // jumlahHalaman disimpan sebagai dasar perhitungan biaya cetak
    private int jumlahHalaman;

    // hargaPerLembar disimpan supaya biaya cetak bisa dihitung dari jumlah halaman
    private float hargaPerLembar;

    // biayaJilid disimpan sebagai biaya tambahan setelah biaya halaman
    private float biayaJilid;

    // constructor ini dipakai buat membuat layanan cetak tugas
    // inputnya id, waktu, nama dokumen, jumlah halaman, harga per lembar, dan biaya jilid
    // outputnya tidak ada karena constructor hanya menyiapkan objek
    // super dipakai supaya data umum layanan tetap diatur oleh class Layanan
    public LayananCetakTugas(String id, String waktu, String namaDokumen, int jumlahHalaman,
            float hargaPerLembar, float biayaJilid) {
        // super mengisi id, nama layanan, dan waktu di class Layanan
        super(id, "Kurir Cetak Tugas", waktu);

        // nama dokumen disimpan supaya pesanan cetak bisa dikenali
        this.namaDokumen = namaDokumen;

        // jumlah halaman disimpan karena dipakai dalam rumus biaya cetak
        this.jumlahHalaman = jumlahHalaman;

        // harga per lembar disimpan supaya biaya halaman bisa dihitung
        this.hargaPerLembar = hargaPerLembar;

        // biaya jilid disimpan karena ditambahkan ke total cetak
        this.biayaJilid = biayaJilid;
    }

    // function ini dipakai buat mengambil nama dokumen
    // inputnya tidak ada karena dokumen sudah tersimpan
    // outputnya String berisi nama dokumen
    // getter dipakai supaya atribut private bisa dibaca class lain
    public String getNamaDokumen() {
        // return ini mengirim nama dokumen ke pemanggil function
        return namaDokumen;
    }

    // function ini dipakai buat mengubah nama dokumen kalau ada koreksi
    // inputnya nama dokumen baru dalam bentuk String
    // outputnya tidak ada karena hanya mengganti atribut
    // setter dipakai supaya perubahan tetap lewat method
    public void setNamaDokumen(String namaDokumen) {
        // this.namaDokumen dipakai supaya atribut dokumen menerima nilai baru
        this.namaDokumen = namaDokumen;
    }

    // function ini dipakai buat mengambil jumlah halaman
    // inputnya tidak ada karena jumlah halaman sudah tersimpan
    // outputnya int berisi jumlah halaman
    // getter dipakai supaya jumlah halaman bisa dibaca untuk kebutuhan lain
    public int getJumlahHalaman() {
        // return ini mengirim jumlah halaman ke pemanggil function
        return jumlahHalaman;
    }

    // function ini dipakai buat mengubah jumlah halaman
    // inputnya jumlah halaman baru dalam bentuk int
    // outputnya tidak ada karena hanya update atribut
    // setter dipakai supaya jumlah halaman bisa diperbaiki kalau salah input
    public void setJumlahHalaman(int jumlahHalaman) {
        // this.jumlahHalaman dipakai supaya atribut halaman menerima nilai baru
        this.jumlahHalaman = jumlahHalaman;
    }

    // function ini dipakai buat mengambil harga per lembar
    // inputnya tidak ada karena harga sudah tersimpan di objek
    // outputnya float berisi harga per lembar
    // getter dipakai supaya harga bisa dicek dari luar class
    public float getHargaPerLembar() {
        // return ini mengirim harga per lembar ke bagian yang membutuhkan
        return hargaPerLembar;
    }

    // function ini dipakai buat mengubah harga per lembar
    // inputnya harga baru dalam bentuk float
    // outputnya tidak ada karena hanya mengganti atribut
    // setter dipakai supaya harga cetak bisa disesuaikan
    public void setHargaPerLembar(float hargaPerLembar) {
        // this.hargaPerLembar dipakai supaya atribut harga menerima nilai baru
        this.hargaPerLembar = hargaPerLembar;
    }

    // function ini dipakai buat mengambil biaya jilid
    // inputnya tidak ada karena biaya jilid sudah tersimpan
    // outputnya float berisi biaya jilid
    // getter dipakai supaya biaya tambahan bisa dibaca dari luar class
    public float getBiayaJilid() {
        // return ini mengirim biaya jilid ke pemanggil function
        return biayaJilid;
    }

    // function ini dipakai buat mengubah biaya jilid
    // inputnya biaya jilid baru dalam bentuk float
    // outputnya tidak ada karena hanya update atribut
    // setter dipakai supaya biaya jilid bisa diperbarui
    public void setBiayaJilid(float biayaJilid) {
        // this.biayaJilid dipakai supaya atribut biaya jilid menerima nilai baru
        this.biayaJilid = biayaJilid;
    }

    // function ini dipakai buat menghitung tarif cetak tugas
    // inputnya tidak ada karena jumlah halaman, harga, dan jilid sudah ada di objek
    // outputnya float berisi total biaya cetak tugas
    // rumus ini dipakai karena biaya cetak adalah halaman dikali harga lalu ditambah jilid
    @Override
    public float hitungTarif() {
        // return ini mengirim hasil biaya halaman ditambah biaya jilid
        return (jumlahHalaman * hargaPerLembar) + biayaJilid;
    }

    // function ini dipakai buat membuat deskripsi layanan cetak tugas
    // inputnya tidak ada karena detail diambil dari atribut objek
    // outputnya String berisi dokumen, halaman, harga per lembar, dan biaya jilid
    // deskripsi dipakai supaya struk menjelaskan asal perhitungan tarif
    @Override
    public String getDeskripsi() {
        // return ini menggabungkan detail cetak tugas dalam satu teks
        return namaDokumen + ", " + jumlahHalaman + " halaman x Rp"
                + String.format("%.0f", hargaPerLembar) + " + jilid Rp"
                + String.format("%.0f", biayaJilid);
    }

    // function ini dipakai buat memberi kategori layanan cetak tugas
    // inputnya tidak ada karena kategori cetak tugas sudah tetap
    // outputnya String kategori CetakTugas
    // kategori dipakai saat riwayat ingin memfilter pesanan berdasarkan tipe
    @Override
    public String getKategori() {
        // return ini mengirim nama kategori sesuai UML
        return "CetakTugas";
    }
}
