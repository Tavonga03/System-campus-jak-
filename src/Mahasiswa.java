// class Mahasiswa dipakai buat menyimpan profil mahasiswa pemesan layanan
public class Mahasiswa {
    // nama disimpan supaya riwayat dan struk tahu siapa pemilik pesanan
    private String nama;

    // nim disimpan sebagai identitas utama mahasiswa di kampus
    private String nim;

    // prodi disimpan supaya profil mahasiswa lebih lengkap
    private String prodi;

    // angkatan disimpan karena ada di rancangan UML mahasiswa
    private String angkatan;

    // constructor ini dipakai buat mengisi profil mahasiswa saat objek dibuat
    // inputnya nama, nim, prodi, dan angkatan dari mahasiswa
    // outputnya tidak ada karena constructor hanya menyiapkan objek
    // constructor dipakai supaya objek mahasiswa langsung punya data lengkap
    public Mahasiswa(String nama, String nim, String prodi, String angkatan) {
        // this.nama dipakai supaya nilai parameter nama masuk ke atribut objek
        this.nama = nama;

        // this.nim dipakai supaya nim mahasiswa tersimpan di objek
        this.nim = nim;

        // this.prodi dipakai supaya program studi mahasiswa ikut tersimpan
        this.prodi = prodi;

        // this.angkatan dipakai supaya tahun angkatan mahasiswa ikut tersimpan
        this.angkatan = angkatan;
    }

    // function ini dipakai buat mengambil nama mahasiswa
    // inputnya tidak ada karena nama sudah tersimpan di objek
    // outputnya String berisi nama mahasiswa
    // getter dipakai supaya atribut private tetap bisa dibaca dari luar class
    public String getNama() {
        // return ini mengirim nama mahasiswa ke bagian yang membutuhkan
        return nama;
    }

    // function ini dipakai buat mengubah nama mahasiswa kalau ada koreksi
    // inputnya nama baru dalam bentuk String
    // outputnya tidak ada karena hanya mengganti isi atribut
    // setter dipakai supaya perubahan data tetap lewat method
    public void setNama(String nama) {
        // this.nama dipakai supaya atribut nama milik objek diganti nilai baru
        this.nama = nama;
    }

    // function ini dipakai buat mengambil nim mahasiswa
    // inputnya tidak ada karena nim sudah tersimpan di objek
    // outputnya String berisi nim mahasiswa
    // getter dipakai supaya nim bisa dibaca tanpa membuka atribut private
    public String getNim() {
        // return ini mengirim nim mahasiswa ke pemanggil function
        return nim;
    }

    // function ini dipakai buat mengubah nim mahasiswa kalau ada koreksi data
    // inputnya nim baru dalam bentuk String
    // outputnya tidak ada karena hanya update atribut
    // setter dipakai supaya data private tetap terkontrol
    public void setNim(String nim) {
        // this.nim dipakai supaya atribut nim menerima nilai baru
        this.nim = nim;
    }

    // function ini dipakai buat mengambil prodi mahasiswa
    // inputnya tidak ada karena prodi sudah tersimpan di objek
    // outputnya String berisi prodi mahasiswa
    // getter dipakai supaya class lain bisa membaca prodi
    public String getProdi() {
        // return ini mengirim prodi mahasiswa ke bagian yang membutuhkan
        return prodi;
    }

    // function ini dipakai buat mengubah prodi mahasiswa kalau diperlukan
    // inputnya prodi baru dalam bentuk String
    // outputnya tidak ada karena hanya mengganti atribut
    // setter dipakai supaya perubahan prodi tetap lewat method
    public void setProdi(String prodi) {
        // this.prodi dipakai supaya atribut prodi diganti nilai baru
        this.prodi = prodi;
    }

    // function ini dipakai buat mengambil angkatan mahasiswa
    // inputnya tidak ada karena angkatan sudah tersimpan di objek
    // outputnya String berisi angkatan mahasiswa
    // getter dipakai supaya angkatan bisa ditampilkan di riwayat atau struk
    public String getAngkatan() {
        // return ini mengirim angkatan mahasiswa ke pemanggil function
        return angkatan;
    }

    // function ini dipakai buat mengubah angkatan mahasiswa kalau datanya salah
    // inputnya angkatan baru dalam bentuk String
    // outputnya tidak ada karena hanya update atribut
    // setter dipakai supaya data tetap diubah lewat method
    public void setAngkatan(String angkatan) {
        // this.angkatan dipakai supaya atribut angkatan menerima nilai baru
        this.angkatan = angkatan;
    }

    // function ini dipakai buat membuat deskripsi lengkap mahasiswa
    // inputnya tidak ada karena semua data sudah ada di atribut objek
    // outputnya String yang berisi nama, nim, prodi, dan angkatan
    // format deskripsi dipakai supaya data mahasiswa tidak dirangkai berulang-ulang
    public String getDeskripsi() {
        // return ini mengirim profil mahasiswa dalam satu teks yang mudah dibaca
        return nama + " (" + nim + ") - " + prodi + " Angkatan " + angkatan;
    }
}
