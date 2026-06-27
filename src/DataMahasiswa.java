// class ini dipakai buat membawa data mahasiswa dan riwayat hariannya sekaligus
public class DataMahasiswa {
    // mahasiswa disimpan supaya profil bisa dipakai di Main
    private Mahasiswa mahasiswa;

    // riwayatHarian disimpan supaya pesanan lama bisa langsung dilanjutkan
    private RiwayatHarian riwayatHarian;

    // constructor ini dipakai buat menggabungkan mahasiswa dan riwayat hariannya
    // inputnya objek Mahasiswa dan objek RiwayatHarian
    // outputnya tidak ada karena constructor hanya menyiapkan objek
    // pembungkus ini dipakai karena Java tidak bisa return dua objek langsung tanpa class bantu
    public DataMahasiswa(Mahasiswa mahasiswa, RiwayatHarian riwayatHarian) {
        // mahasiswa disimpan supaya bisa diambil lagi oleh Main
        this.mahasiswa = mahasiswa;

        // riwayat disimpan supaya bisa diambil lagi oleh Main
        this.riwayatHarian = riwayatHarian;
    }

    // function ini dipakai buat mengambil objek mahasiswa
    // inputnya tidak ada karena mahasiswa sudah tersimpan di objek ini
    // outputnya objek Mahasiswa
    // getter dipakai supaya atribut private tetap bisa dibaca dari luar class
    public Mahasiswa getMahasiswa() {
        // return ini mengirim objek mahasiswa ke pemanggil function
        return mahasiswa;
    }

    // function ini dipakai buat mengambil objek riwayat harian
    // inputnya tidak ada karena riwayat sudah tersimpan di objek ini
    // outputnya objek RiwayatHarian
    // getter dipakai supaya Main bisa lanjut memakai riwayat yang sudah dimuat
    public RiwayatHarian getRiwayatHarian() {
        // return ini mengirim riwayat harian ke pemanggil function
        return riwayatHarian;
    }
}
