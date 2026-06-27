// import ArrayList dipakai supaya riwayat bisa menyimpan banyak layanan secara dinamis
import java.util.ArrayList;

// import List dipakai sesuai UML karena pesanan disimpan sebagai List<Layanan>
import java.util.List;

// class RiwayatHarian dipakai buat menyimpan semua pesanan mahasiswa dalam satu hari
public class RiwayatHarian {
    // mahasiswa disimpan supaya riwayat tahu siapa pemilik semua pesanan ini
    private Mahasiswa mahasiswa;

    // pesanan disimpan sebagai List supaya sesuai rancangan UML
    private List<Layanan> pesanan;

    // tanggal disimpan supaya riwayat jelas untuk hari apa
    private String tanggal;

    // constructor ini dipakai buat membuat riwayat harian baru
    // inputnya objek Mahasiswa dan tanggal riwayat
    // outputnya tidak ada karena constructor hanya menyiapkan objek
    // List dipakai karena UML menunjukkan RiwayatHarian menyimpan List<Layanan>
    public RiwayatHarian(Mahasiswa mahasiswa, String tanggal) {
        // mahasiswa disimpan supaya riwayat terhubung dengan pemiliknya
        this.mahasiswa = mahasiswa;

        // ArrayList dibuat supaya pesanan bisa ditambah satu per satu dari menu
        this.pesanan = new ArrayList<Layanan>();

        // tanggal disimpan supaya riwayat bisa ditampilkan di struk
        this.tanggal = tanggal;
    }

    // function ini dipakai buat mengambil mahasiswa pemilik riwayat
    // inputnya tidak ada karena mahasiswa sudah tersimpan di objek
    // outputnya objek Mahasiswa
    // getter dipakai supaya Struk bisa membaca profil mahasiswa
    public Mahasiswa getMahasiswa() {
        // return ini mengirim objek mahasiswa ke bagian yang membutuhkan
        return mahasiswa;
    }

    // function ini dipakai buat mengganti mahasiswa pemilik riwayat kalau diperlukan
    // inputnya objek Mahasiswa baru
    // outputnya tidak ada karena hanya mengganti atribut
    // setter dipakai supaya perubahan tetap lewat method
    public void setMahasiswa(Mahasiswa mahasiswa) {
        // this.mahasiswa dipakai supaya atribut mahasiswa menerima objek baru
        this.mahasiswa = mahasiswa;
    }

    // function ini dipakai buat mengambil daftar pesanan
    // inputnya tidak ada karena pesanan sudah tersimpan di objek
    // outputnya List berisi objek Layanan
    // getter dipakai supaya Struk bisa menelusuri semua layanan
    public List<Layanan> getPesanan() {
        // return ini mengirim daftar pesanan ke pemanggil function
        return pesanan;
    }

    // function ini dipakai buat mengambil tanggal riwayat
    // inputnya tidak ada karena tanggal sudah tersimpan di objek
    // outputnya String berisi tanggal riwayat
    // getter dipakai supaya tanggal bisa ditampilkan di riwayat dan struk
    public String getTanggal() {
        // return ini mengirim tanggal riwayat ke bagian yang membutuhkan
        return tanggal;
    }

    // function ini dipakai buat mengubah tanggal riwayat kalau diperlukan
    // inputnya tanggal baru dalam bentuk String
    // outputnya tidak ada karena hanya mengganti atribut
    // setter dipakai supaya perubahan tanggal tetap lewat method
    public void setTanggal(String tanggal) {
        // this.tanggal dipakai supaya atribut tanggal menerima nilai baru
        this.tanggal = tanggal;
    }

    // function ini dipakai buat mengetahui jumlah pesanan yang tersimpan
    // inputnya tidak ada karena jumlah bisa dihitung dari ukuran List
    // outputnya int berisi jumlah pesanan
    // size dipakai karena List sudah bisa menghitung jumlah datanya sendiri
    public int getJumlahLayanan() {
        // return ini mengirim ukuran list pesanan
        return pesanan.size();
    }

    // function ini dipakai buat menambahkan layanan ke riwayat harian
    // inputnya objek Layanan yang bisa berupa transportasi, makanan, atau cetak tugas
    // outputnya tidak ada karena UML meminta tambahPesanan(Layanan) bertipe void
    // object passing dipakai supaya riwayat menerima objek layanan lengkap
    public void tambahPesanan(Layanan layanan) {
        // add dipakai supaya layanan baru masuk ke bagian akhir list pesanan
        pesanan.add(layanan);
    }

    // function ini dipakai buat menghitung total tarif dari semua pesanan
    // inputnya tidak ada karena daftar pesanan sudah ada di objek
    // outputnya float berisi total tagihan
    // loop dipakai supaya semua layanan dihitung satu per satu secara polymorphism
    public float hitungTotal() {
        // total dibuat 0 dulu sebelum semua tarif dijumlahkan
        float total = 0;

        // loop for-each dipakai karena pesanan berbentuk List
        for (Layanan layanan : pesanan) {
            // hitungTarif akan mengikuti jenis layanan aslinya
            total += layanan.hitungTarif();
        }

        // return ini mengirim total akhir ke bagian yang membutuhkan
        return total;
    }

    // function ini dipakai buat mencari pesanan berdasarkan kategori layanan
    // inputnya kategori dalam bentuk String, misalnya Transportasi atau CetakTugas
    // outputnya List<Layanan> yang isinya hanya pesanan dengan kategori yang cocok
    // filter manual dipakai supaya riwayat bisa menampilkan pesanan per tipe sesuai UML
    public List<Layanan> getPesananByType(String kategori) {
        // hasil dibuat sebagai List kosong untuk menampung pesanan yang cocok
        List<Layanan> hasil = new ArrayList<Layanan>();

        // loop ini memeriksa semua pesanan satu per satu
        for (Layanan layanan : pesanan) {
            // equalsIgnoreCase dipakai supaya huruf besar kecil tidak bikin filter gagal
            if (layanan.getKategori().equalsIgnoreCase(kategori)) {
                // layanan yang kategorinya cocok dimasukkan ke list hasil
                hasil.add(layanan);
            }
        }

        // return ini mengirim daftar pesanan yang sudah difilter
        return hasil;
    }

    // function ini dipakai buat mencari satu pesanan dari id pesanan
    // inputnya id pesanan dalam bentuk String, misalnya TRX-001
    // outputnya objek Layanan kalau ditemukan, atau null kalau tidak ada
    // pencarian id dipakai supaya struk bisa dicetak dari pesanan yang dipilih user
    public Layanan getPesananById(String id) {
        // loop ini memeriksa semua pesanan satu per satu
        for (Layanan layanan : pesanan) {
            // equalsIgnoreCase dipakai supaya id tetap cocok walaupun hurufnya beda besar kecil
            if (layanan.getId().equalsIgnoreCase(id)) {
                // return ini langsung mengirim layanan yang id-nya cocok
                return layanan;
            }
        }

        // return null dipakai sebagai tanda bahwa id pesanan tidak ditemukan
        return null;
    }

    // function ini dipakai buat menampilkan daftar pesanan milik mahasiswa
    // inputnya tidak ada karena data pesanan sudah tersimpan di riwayat
    // outputnya tidak ada karena riwayat langsung dicetak ke layar
    // riwayat dipisah dari struk supaya user bisa cek pesanan sebelum total diterbitkan
    public void cetakRiwayatPesanan() {
        // garis ini dipakai supaya tampilan riwayat lebih rapi
        System.out.println("==============================================");

        // judul ini menunjukkan bahwa bagian ini adalah riwayat pesanan
        System.out.println("              RIWAYAT PESANAN                 ");

        // garis ini menutup bagian judul riwayat
        System.out.println("==============================================");

        // profil mahasiswa dicetak supaya riwayat jelas milik siapa
        System.out.println("Mahasiswa : " + mahasiswa.getDeskripsi());

        // tanggal riwayat dicetak supaya jelas riwayat ini untuk hari apa
        System.out.println("Tanggal   : " + tanggal);

        // jumlah layanan dicetak supaya user tahu banyaknya pesanan
        System.out.println("Jumlah    : " + pesanan.size() + " layanan");

        // garis ini memisahkan profil dan daftar pesanan
        System.out.println("----------------------------------------------");

        // kondisi ini dipakai supaya tampilan tetap jelas kalau belum ada pesanan
        if (pesanan.isEmpty()) {
            // pesan ini memberi tahu user bahwa riwayat masih kosong
            System.out.println("Belum ada layanan yang dipesan.");
        }

        // loop ini mencetak setiap pesanan yang ada di List
        for (int i = 0; i < pesanan.size(); i++) {
            // layanan diambil berdasarkan index supaya nomor urut bisa dibuat
            Layanan layanan = pesanan.get(i);

            // ringkasan layanan dicetak dengan nomor urut supaya mudah dicek
            System.out.println((i + 1) + ". " + layanan.buatRingkasan());
        }

        // garis ini dipakai sebagai penutup tampilan riwayat
        System.out.println("==============================================");
    }
}
