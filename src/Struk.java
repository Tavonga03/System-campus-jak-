// class Struk dipakai buat menerbitkan struk pembayaran dari RiwayatHarian sesuai UML
public class Struk {
    // riwayat disimpan supaya struk bisa membaca mahasiswa, tanggal, dan daftar pesanan
    private RiwayatHarian riwayat;

    // tanggalCetak disimpan supaya struk punya informasi kapan struk diterbitkan
    private String tanggalCetak;

    // constructor ini dipakai buat membuat objek struk dari riwayat harian
    // inputnya RiwayatHarian dan tanggal cetak struk
    // outputnya tidak ada karena constructor hanya menyiapkan objek
    // class Struk dipisah supaya tugas cetak pembayaran tidak ditaruh di RiwayatHarian
    public Struk(RiwayatHarian riwayat, String tanggalCetak) {
        // riwayat disimpan supaya struk bisa mengambil data pesanan
        this.riwayat = riwayat;

        // tanggal cetak disimpan supaya struk sesuai rancangan UML
        this.tanggalCetak = tanggalCetak;
    }

    // function ini dipakai buat mengambil riwayat yang dipakai struk
    // inputnya tidak ada karena riwayat sudah tersimpan di objek
    // outputnya objek RiwayatHarian
    // getter dipakai supaya data private tetap bisa dibaca kalau diperlukan
    public RiwayatHarian getRiwayat() {
        // return ini mengirim riwayat ke pemanggil function
        return riwayat;
    }

    // function ini dipakai buat mengganti riwayat pada struk kalau diperlukan
    // inputnya objek RiwayatHarian baru
    // outputnya tidak ada karena hanya mengganti atribut
    // setter dipakai supaya perubahan tetap lewat method
    public void setRiwayat(RiwayatHarian riwayat) {
        // this.riwayat dipakai supaya atribut riwayat menerima objek baru
        this.riwayat = riwayat;
    }

    // function ini dipakai buat mengambil tanggal cetak struk
    // inputnya tidak ada karena tanggal cetak sudah tersimpan
    // outputnya String berisi tanggal cetak
    // getter dipakai supaya tanggal cetak bisa dibaca dari luar class
    public String getTanggalCetak() {
        // return ini mengirim tanggal cetak ke bagian yang membutuhkan
        return tanggalCetak;
    }

    // function ini dipakai buat mengubah tanggal cetak struk
    // inputnya tanggal cetak baru dalam bentuk String
    // outputnya tidak ada karena hanya mengganti atribut
    // setter dipakai supaya perubahan tanggal tetap lewat method
    public void setTanggalCetak(String tanggalCetak) {
        // this.tanggalCetak dipakai supaya atribut tanggal cetak menerima nilai baru
        this.tanggalCetak = tanggalCetak;
    }

    // function ini dipakai buat menerbitkan struk pembayaran akhir
    // inputnya tidak ada karena semua data diambil dari riwayat yang sudah tersimpan
    // outputnya tidak ada karena struk langsung dicetak ke layar
    // method terbitkan dipakai karena di UML Struk punya tanggung jawab mencetak struk
    public void terbitkan() {
        // garis ini dipakai supaya tampilan struk lebih rapi
        System.out.println("==============================================");

        // judul ini menunjukkan bahwa output adalah struk pembayaran Campus-Jek
        System.out.println("               STRUK CAMPUS-JEK               ");

        // garis ini menutup bagian judul struk
        System.out.println("==============================================");

        // profil mahasiswa dicetak supaya tagihan jelas milik siapa
        System.out.println("Mahasiswa     : " + riwayat.getMahasiswa().getDeskripsi());

        // tanggal riwayat dicetak supaya struk jelas mengambil pesanan hari apa
        System.out.println("Tanggal Order : " + riwayat.getTanggal());

        // tanggal cetak dicetak supaya sesuai atribut tanggalCetak di UML
        System.out.println("Tanggal Cetak : " + tanggalCetak);

        // jumlah layanan dicetak supaya pembaca tahu banyaknya pesanan
        System.out.println("Jumlah        : " + riwayat.getPesanan().size() + " layanan");

        // garis ini memisahkan bagian identitas dan daftar pesanan
        System.out.println("----------------------------------------------");

        // kondisi ini dipakai supaya struk tetap jelas kalau belum ada pesanan
        if (riwayat.getPesanan().isEmpty()) {
            // pesan ini memberi tahu user bahwa belum ada layanan di riwayat
            System.out.println("Belum ada layanan yang dipesan.");
        }

        // loop ini menelusuri semua pesanan dari riwayat
        for (int i = 0; i < riwayat.getPesanan().size(); i++) {
            // layanan diambil berdasarkan index supaya nomor urut bisa ditampilkan
            Layanan layanan = riwayat.getPesanan().get(i);

            // ringkasan layanan dicetak supaya struk menampilkan subtotal tiap layanan
            System.out.println((i + 1) + ". " + layanan.buatRingkasan());
        }

        // garis ini memisahkan daftar layanan dengan total pembayaran
        System.out.println("----------------------------------------------");

        // total tagihan dicetak dari hasil hitungTotal milik RiwayatHarian
        System.out.println("Total Tagihan : Rp" + String.format("%.0f", riwayat.hitungTotal()));

        // garis ini dipakai sebagai penutup struk
        System.out.println("==============================================");
    }

}
