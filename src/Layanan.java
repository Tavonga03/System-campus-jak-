// abstract class Layanan dipakai sebagai bentuk umum dari semua jenis layanan Campus-Jek
public abstract class Layanan {
    // id disimpan supaya setiap pesanan punya kode unik
    private String id;

    // nama disimpan supaya layanan punya nama yang bisa tampil di riwayat dan struk
    private String nama;

    // waktu disimpan supaya riwayat tahu kapan layanan dipesan
    private String waktu;

    // constructor ini dipakai buat mengisi data umum yang dimiliki semua layanan
    // inputnya id, nama layanan, dan waktu pemesanan
    // outputnya tidak ada karena constructor hanya menyiapkan objek
    // abstract parent dipakai supaya data umum tidak ditulis ulang di setiap subclass
    protected Layanan(String id, String nama, String waktu) {
        // this.id dipakai supaya id dari parameter masuk ke atribut objek
        this.id = id;

        // this.nama dipakai supaya nama layanan tersimpan di objek
        this.nama = nama;

        // this.waktu dipakai supaya waktu pemesanan ikut tersimpan
        this.waktu = waktu;
    }

    // function ini dipakai buat mengambil id layanan
    // inputnya tidak ada karena id sudah tersimpan di objek
    // outputnya String berisi id layanan
    // getter dipakai supaya atribut private bisa dibaca class lain
    public String getId() {
        // return ini mengirim id layanan ke bagian yang membutuhkan
        return id;
    }

    // function ini dipakai buat mengubah id layanan kalau diperlukan
    // inputnya id baru dalam bentuk String
    // outputnya tidak ada karena hanya mengganti atribut
    // setter dipakai supaya perubahan tetap lewat method
    public void setId(String id) {
        // this.id dipakai supaya atribut id menerima nilai baru
        this.id = id;
    }

    // function ini dipakai buat mengambil nama layanan
    // inputnya tidak ada karena nama sudah tersimpan di objek
    // outputnya String berisi nama layanan
    // getter dipakai supaya nama layanan bisa ditampilkan di struk
    public String getNama() {
        // return ini mengirim nama layanan ke pemanggil function
        return nama;
    }

    // function ini dipakai buat mengubah nama layanan kalau diperlukan
    // inputnya nama baru dalam bentuk String
    // outputnya tidak ada karena hanya update atribut
    // setter dipakai supaya data private tetap terkontrol
    public void setNama(String nama) {
        // this.nama dipakai supaya atribut nama menerima nilai baru
        this.nama = nama;
    }

    // function ini dipakai buat mengambil waktu pemesanan
    // inputnya tidak ada karena waktu sudah tersimpan di objek
    // outputnya String berisi waktu pemesanan
    // getter dipakai supaya riwayat bisa menampilkan kapan layanan dibuat
    public String getWaktu() {
        // return ini mengirim waktu layanan ke bagian yang membutuhkan
        return waktu;
    }

    // function ini dipakai buat mengubah waktu pemesanan kalau diperlukan
    // inputnya waktu baru dalam bentuk String
    // outputnya tidak ada karena hanya mengganti atribut
    // setter dipakai supaya perubahan waktu tetap lewat method
    public void setWaktu(String waktu) {
        // this.waktu dipakai supaya atribut waktu menerima nilai baru
        this.waktu = waktu;
    }

    // function abstract ini dipakai supaya setiap subclass wajib menghitung tarifnya sendiri
    // inputnya tidak ada karena data biaya ada di masing-masing layanan
    // outputnya float berisi tarif layanan
    // abstract dipakai karena rumus transportasi, makanan, dan cetak tugas berbeda
    public abstract float hitungTarif();

    // function abstract ini dipakai supaya setiap layanan punya deskripsi sendiri
    // inputnya tidak ada karena deskripsi diambil dari atribut tiap subclass
    // outputnya String berisi penjelasan layanan
    // abstract dipakai supaya detail tiap layanan bisa menyesuaikan kategorinya
    public abstract String getDeskripsi();

    // function abstract ini dipakai buat mengetahui kategori layanan
    // inputnya tidak ada karena kategori ditentukan oleh subclass
    // outputnya String seperti Transportasi, PesananMakanan, atau CetakTugas
    // kategori dipakai oleh RiwayatHarian saat mencari pesanan berdasarkan tipe
    public abstract String getKategori();

    // function ini dipakai buat membuat ringkasan satu baris layanan
    // inputnya tidak ada karena semua data sudah ada di objek
    // outputnya String berisi id, nama, waktu, deskripsi, dan tarif
    // ringkasan dipakai supaya riwayat dan struk punya format yang konsisten
    public String buatRingkasan() {
        // return ini menggabungkan data umum dan detail dari subclass
        return id + " - " + nama + " | " + waktu + " | " + getDeskripsi()
                + " | Tarif: Rp" + String.format("%.0f", hitungTarif());
    }
}
