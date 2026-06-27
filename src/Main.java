// import LocalDate dipakai supaya program bisa membuat tanggal riwayat secara otomatis
import java.time.LocalDate;

// import LocalDateTime dipakai supaya waktu pesanan dan tanggal cetak bisa dicatat
import java.time.LocalDateTime;

// import DateTimeFormatter dipakai supaya format tanggal dan waktu lebih rapi
import java.time.format.DateTimeFormatter;

// import List dipakai saat menampilkan pesanan berdasarkan kategori
import java.util.List;

// import Scanner dipakai supaya program bisa membaca input dari keyboard
import java.util.Scanner;

// class Main dipakai sebagai titik mulai program saat dijalankan
public class Main {
    // tarif ini dibuat tetap karena layanan transportasi Campus-Jek punya harga per km
    private static final float TARIF_TRANSPORTASI_PER_KM = 3000;

    // harga ini dibuat tetap karena layanan cetak tugas punya harga standar per lembar
    private static final float HARGA_CETAK_PER_LEMBAR = 500;

    // biaya ini dibuat tetap karena layanan cetak tugas punya biaya jilid standar
    private static final float BIAYA_JILID = 4000;

    // formatter tanggal dipakai untuk tanggal riwayat dan tanggal cetak
    private static final DateTimeFormatter FORMAT_TANGGAL = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    // formatter waktu dipakai supaya waktu layanan punya jam dan menit
    private static final DateTimeFormatter FORMAT_WAKTU = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    // konstanta ini dipakai supaya pilihan menu transportasi tidak ditulis angka mentah terus
    private static final int MENU_TRANSPORTASI = 1;

    // konstanta ini dipakai supaya pilihan menu makanan lebih mudah dibaca
    private static final int MENU_MAKANAN = 2;

    // konstanta ini dipakai supaya pilihan menu cetak tugas lebih jelas
    private static final int MENU_CETAK_TUGAS = 3;

    // konstanta ini dipakai untuk masuk ke menu riwayat pesanan
    private static final int MENU_LIHAT_RIWAYAT = 4;

    // konstanta ini dipakai sebagai pilihan keluar dari program
    private static final int MENU_KELUAR = 5;

    // konstanta ini dipakai untuk melihat semua pesanan dari submenu riwayat
    private static final int RIWAYAT_LIHAT_SEMUA = 1;

    // konstanta ini dipakai untuk melihat pesanan berdasarkan kategori dari submenu riwayat
    private static final int RIWAYAT_LIHAT_KATEGORI = 2;

    // konstanta ini dipakai untuk mencetak struk total harian dari submenu riwayat
    private static final int RIWAYAT_CETAK_STRUK_TOTAL = 3;

    // konstanta ini dipakai untuk kembali dari submenu riwayat ke menu utama
    private static final int RIWAYAT_KEMBALI = 4;

    // function main ini dipakai buat menjalankan program Campus-Jek dari menu CLI
    // inputnya args dari terminal, tapi di program ini belum dipakai
    // outputnya tidak mengembalikan nilai karena hasil program langsung tampil di layar
    // pendekatan menu dipakai supaya user bisa membuat riwayat dan struk sesuai UML
    public static void main(String[] args) {
        // scanner dibuat supaya program bisa membaca input dari keyboard
        Scanner scanner = new Scanner(System.in);

        // header ditampilkan di awal supaya user tahu program yang sedang dibuka
        tampilkanHeader();

        // tanggal hari ini dibuat otomatis untuk atribut tanggal di RiwayatHarian
        String tanggalRiwayat = LocalDate.now().format(FORMAT_TANGGAL);

        // data mahasiswa dimuat atau dibuat berdasarkan NIM
        DataMahasiswa dataMahasiswa = inputAtauMuatMahasiswa(scanner, tanggalRiwayat);

        // riwayat harian diambil dari data mahasiswa supaya pesanan lama bisa dilanjutkan
        RiwayatHarian riwayatHarian = dataMahasiswa.getRiwayatHarian();

        // variabel ini dipakai buat mengontrol apakah menu masih berjalan
        boolean programBerjalan = true;

        // loop ini dipakai supaya mahasiswa bisa memesan banyak layanan dalam satu riwayat
        while (programBerjalan) {
            // menu ditampilkan setiap putaran supaya user bisa memilih aksi berikutnya
            tampilkanMenu(riwayatHarian);

            // pilihan dibaca sebagai angka supaya bisa diproses oleh switch
            int pilihan = inputAngkaBulatPositif(scanner, "Pilih menu: ");

            // switch dipakai karena menu punya beberapa cabang yang jelas
            switch (pilihan) {
                // case ini jalan kalau user memilih layanan transportasi
                case MENU_TRANSPORTASI:
                    // function tambah transportasi dipanggil supaya input transportasi terpisah
                    tambahTransportasi(scanner, riwayatHarian);

                    // break dipakai supaya program tidak lanjut ke case berikutnya
                    break;

                // case ini jalan kalau user memilih layanan makanan
                case MENU_MAKANAN:
                    // function tambah makanan dipanggil supaya input makanan terpisah
                    tambahPesananMakanan(scanner, riwayatHarian);

                    // break dipakai supaya switch berhenti setelah makanan diproses
                    break;

                // case ini jalan kalau user memilih layanan cetak tugas
                case MENU_CETAK_TUGAS:
                    // function tambah cetak tugas dipanggil supaya input cetak lebih rapi
                    tambahCetakTugas(scanner, riwayatHarian);

                    // break dipakai supaya program tidak menjalankan case lain
                    break;

                // case ini jalan kalau user ingin membuka menu riwayat pesanan
                case MENU_LIHAT_RIWAYAT:
                    // submenu riwayat dipanggil supaya pilihan lihat dan cetak struk terkumpul di satu tempat
                    tampilkanMenuRiwayat(scanner, riwayatHarian);

                    // break dipakai supaya menu utama kembali setelah submenu riwayat selesai
                    break;

                // case ini jalan kalau user ingin keluar dari program
                case MENU_KELUAR:
                    // nilai false membuat while berhenti
                    programBerjalan = false;

                    // pesan ini ditampilkan supaya program terasa selesai
                    System.out.println("Program selesai. Terima kasih sudah memakai Campus-Jek.");

                    // break dipakai supaya switch selesai setelah user memilih keluar
                    break;

                // default jalan kalau pilihan menu tidak tersedia
                default:
                    // pesan ini membantu user tahu batas pilihan menu
                    System.out.println("Pilihan tidak tersedia. Coba pilih angka 1 sampai 5.");
            }
        }

        // scanner ditutup karena input dari keyboard sudah selesai dipakai
        scanner.close();
    }

    // function ini dipakai buat menampilkan judul program di awal
    // inputnya tidak ada karena teks header sudah tetap
    // outputnya tidak ada karena hasilnya langsung dicetak ke layar
    // header dipisah supaya main lebih mudah dibaca
    private static void tampilkanHeader() {
        // garis ini dipakai supaya tampilan awal program lebih rapi
        System.out.println("==============================================");

        // teks ini menjelaskan nama aplikasi yang sedang dijalankan
        System.out.println("                  CAMPUS-JEK                  ");

        // garis ini dipakai sebagai penutup bagian header
        System.out.println("==============================================");
    }

    // function ini dipakai buat memuat data mahasiswa lama atau membuat data baru
    // inputnya Scanner dan tanggal riwayat hari ini
    // outputnya DataMahasiswa berisi profil dan riwayat harian
    // pengecekan NIM dipakai supaya mahasiswa yang sama bisa melihat history sebelumnya
    private static DataMahasiswa inputAtauMuatMahasiswa(Scanner scanner, String tanggalRiwayat) {
        // teks ini memberi tahu user bahwa program akan mengecek data berdasarkan NIM
        System.out.println("\nMasukkan NIM Mahasiswa");

        // nim dibaca lebih dulu karena dipakai sebagai nama file JSON
        String nim = inputTeks(scanner, "NIM      : ");

        // program mencoba memuat data mahasiswa dari file JSON
        DataMahasiswa dataTersimpan = PenyimpananJson.muat(nim);

        // kondisi ini jalan kalau file JSON mahasiswa sudah ada
        if (dataTersimpan != null) {
            // pesan ini memberi tahu bahwa data lama berhasil ditemukan
            System.out.println("Data mahasiswa ditemukan: " + dataTersimpan.getMahasiswa().getDeskripsi());

            // pesan ini memberi tahu jumlah pesanan yang ikut dimuat
            System.out.println("Riwayat yang dimuat: " + dataTersimpan.getRiwayatHarian().getJumlahLayanan()
                    + " layanan.");

            // return ini memakai data lama supaya history sebelumnya tetap terlihat
            return dataTersimpan;
        }

        // pesan ini muncul kalau NIM belum pernah disimpan
        System.out.println("Data belum ada, silakan isi profil mahasiswa.");

        // nama dibaca sebagai teks supaya bisa berisi lebih dari satu kata
        String nama = inputTeks(scanner, "Nama     : ");

        // prodi dibaca sebagai teks supaya profil mahasiswa lengkap
        String prodi = inputTeks(scanner, "Prodi    : ");

        // angkatan dibaca sebagai teks supaya bisa mengikuti format kampus
        String angkatan = inputTeks(scanner, "Angkatan : ");

        // objek mahasiswa dibuat dari input profil baru
        Mahasiswa mahasiswa = new Mahasiswa(nama, nim, prodi, angkatan);

        // riwayat baru dibuat untuk mahasiswa yang belum punya file JSON
        RiwayatHarian riwayatHarian = new RiwayatHarian(mahasiswa, tanggalRiwayat);

        // data baru langsung disimpan supaya file JSON mahasiswa tersedia
        simpanRiwayatKeJson(riwayatHarian);

        // return ini mengirim data mahasiswa baru ke Main
        return new DataMahasiswa(mahasiswa, riwayatHarian);
    }

    // function ini dipakai buat menampilkan daftar menu utama
    // inputnya RiwayatHarian supaya jumlah pesanan dan tanggal bisa ditampilkan
    // outputnya tidak ada karena menu langsung dicetak ke layar
    // menu dipisah supaya pilihan program gampang dicari dan diubah
    private static void tampilkanMenu(RiwayatHarian riwayatHarian) {
        // baris kosong ini dipakai supaya tampilan menu tidak terlalu menempel
        System.out.println();

        // teks ini menunjukkan tanggal riwayat yang sedang aktif
        System.out.println("Tanggal riwayat : " + riwayatHarian.getTanggal());

        // teks ini menunjukkan jumlah layanan yang tersimpan di riwayat mahasiswa
        System.out.println("Riwayat pesanan : " + riwayatHarian.getJumlahLayanan() + " layanan");

        // menu ini dipakai untuk memilih layanan transportasi yang sudah tersedia
        System.out.println("1. Pesan transportasi antar-jemput");

        // menu ini dipakai untuk memilih layanan makanan yang sudah tersedia
        System.out.println("2. Pesan-antar makanan");

        // menu ini dipakai untuk memilih layanan cetak tugas yang sudah tersedia
        System.out.println("3. Kurir cetak tugas");

        // menu ini dipakai untuk masuk ke submenu riwayat pesanan
        System.out.println("4. Lihat riwayat pesanan");

        // menu ini dipakai untuk keluar dari program
        System.out.println("5. Keluar");
    }

    // function ini dipakai buat menambah pesanan transportasi ke riwayat
    // inputnya Scanner dan RiwayatHarian yang akan menerima pesanan
    // outputnya tidak ada karena layanan langsung masuk ke riwayat
    // function khusus dipakai supaya input transportasi tidak bercampur dengan layanan lain
    private static void tambahTransportasi(Scanner scanner, RiwayatHarian riwayatHarian) {
        // teks ini menjadi penanda bahwa user sedang mengisi layanan transportasi
        System.out.println("\nPesan Transportasi Antar-Jemput");

        // id pesanan dibuat otomatis supaya user tidak perlu mengetik kode transaksi
        String id = buatIdPesanan(riwayatHarian.getJumlahLayanan() + 1);

        // waktu dibuat otomatis saat layanan ditambahkan ke riwayat
        String waktu = LocalDateTime.now().format(FORMAT_WAKTU);

        // tujuan dibaca dari input supaya perjalanan bisa disesuaikan
        String tujuan = inputTeks(scanner, "Tujuan          : ");

        // jarak dibaca sebagai angka karena dipakai untuk menghitung tarif
        float jarak = inputAngkaPositif(scanner, "Jarak kilometer : ");

        // objek transportasi dibuat dari input user dan tarif standar Campus-Jek
        Layanan layanan = new LayananTransportasi(id, waktu, tujuan, jarak, TARIF_TRANSPORTASI_PER_KM);

        // layanan dimasukkan ke riwayat memakai method sesuai UML
        riwayatHarian.tambahPesanan(layanan);

        // pesan ini memberi tahu user bahwa layanan berhasil masuk riwayat
        System.out.println("Pesanan berhasil masuk riwayat dengan id " + layanan.getId() + ".");

        // riwayat disimpan ke JSON supaya pesanan tetap ada saat program dibuka lagi
        simpanRiwayatKeJson(riwayatHarian);
    }

    // function ini dipakai buat menambah pesanan makanan ke riwayat
    // inputnya Scanner dan RiwayatHarian yang akan menerima pesanan
    // outputnya tidak ada karena layanan langsung disimpan ke riwayat
    // function khusus dipakai supaya input makanan punya alur sendiri
    private static void tambahPesananMakanan(Scanner scanner, RiwayatHarian riwayatHarian) {
        // teks ini menjadi penanda bahwa user sedang mengisi pesanan makanan
        System.out.println("\nPesan-Antar Makanan");

        // id pesanan dibuat otomatis dari urutan layanan
        String id = buatIdPesanan(riwayatHarian.getJumlahLayanan() + 1);

        // waktu dibuat otomatis saat pesanan makanan dibuat
        String waktu = LocalDateTime.now().format(FORMAT_WAKTU);

        // nama item dibaca supaya struk tahu makanan yang dipesan
        String namaItem = inputTeks(scanner, "Nama makanan      : ");

        // harga makanan dibaca karena tiap makanan bisa punya harga berbeda
        float hargaMakanan = inputAngkaPositif(scanner, "Harga makanan Rp  : ");

        // ongkos kirim dibaca karena tiap restoran bisa punya ongkir berbeda
        float ongkosKirim = inputAngkaPositif(scanner, "Ongkos kirim Rp   : ");

        // objek pesanan makanan dibuat sesuai class di UML
        Layanan layanan = new PesananMakanan(id, waktu, namaItem, hargaMakanan, ongkosKirim);

        // layanan dimasukkan ke riwayat memakai method tambahPesanan
        riwayatHarian.tambahPesanan(layanan);

        // pesan ini memberi tahu user bahwa pesanan berhasil tersimpan
        System.out.println("Pesanan berhasil masuk riwayat dengan id " + layanan.getId() + ".");

        // riwayat disimpan ke JSON supaya pesanan tetap ada saat program dibuka lagi
        simpanRiwayatKeJson(riwayatHarian);
    }

    // function ini dipakai buat menambah layanan cetak tugas ke riwayat
    // inputnya Scanner dan RiwayatHarian yang akan menerima layanan
    // outputnya tidak ada karena layanan langsung disimpan ke riwayat
    // function khusus dipakai supaya input cetak tugas tidak bercampur dengan menu lain
    private static void tambahCetakTugas(Scanner scanner, RiwayatHarian riwayatHarian) {
        // teks ini menjadi penanda bahwa user sedang mengisi layanan cetak tugas
        System.out.println("\nKurir Cetak Tugas");

        // id pesanan dibuat otomatis supaya format kode tetap konsisten
        String id = buatIdPesanan(riwayatHarian.getJumlahLayanan() + 1);

        // waktu dibuat otomatis saat layanan cetak ditambahkan
        String waktu = LocalDateTime.now().format(FORMAT_WAKTU);

        // nama dokumen dibaca supaya struk tahu file atau tugas yang dicetak
        String namaDokumen = inputTeks(scanner, "Nama dokumen   : ");

        // jumlah halaman dibaca sebagai angka karena dipakai dalam rumus biaya cetak
        int jumlahHalaman = inputAngkaBulatPositif(scanner, "Jumlah halaman : ");

        // objek cetak tugas dibuat dengan harga standar per lembar dan biaya jilid
        Layanan layanan = new LayananCetakTugas(id, waktu, namaDokumen, jumlahHalaman,
                HARGA_CETAK_PER_LEMBAR, BIAYA_JILID);

        // layanan dimasukkan ke riwayat memakai method sesuai UML
        riwayatHarian.tambahPesanan(layanan);

        // pesan ini memberi tahu user bahwa layanan berhasil tersimpan
        System.out.println("Pesanan berhasil masuk riwayat dengan id " + layanan.getId() + ".");

        // riwayat disimpan ke JSON supaya pesanan tetap ada saat program dibuka lagi
        simpanRiwayatKeJson(riwayatHarian);
    }

    // function ini dipakai buat menampilkan submenu riwayat pesanan
    // inputnya Scanner dan RiwayatHarian yang sedang aktif
    // outputnya tidak ada karena submenu langsung menampilkan hasil ke layar
    // submenu dipakai supaya menu utama tetap sederhana dan riwayat punya pilihan sendiri
    private static void tampilkanMenuRiwayat(Scanner scanner, RiwayatHarian riwayatHarian) {
        // variabel ini dipakai buat menentukan apakah submenu masih berjalan
        boolean menuRiwayatBerjalan = true;

        // loop ini membuat user bisa melihat riwayat berkali-kali sebelum kembali ke menu utama
        while (menuRiwayatBerjalan) {
            // baris kosong ini dipakai supaya submenu tidak terlalu menempel dengan output sebelumnya
            System.out.println();

            // judul ini memberi tanda bahwa user sedang berada di menu riwayat
            System.out.println("Menu Riwayat Pesanan");

            // pilihan ini dipakai untuk melihat semua pesanan yang tersimpan
            System.out.println("1. Lihat semua pesanan");

            // pilihan ini dipakai untuk melihat pesanan berdasarkan kategori layanan
            System.out.println("2. Lihat berdasarkan kategori");

            // pilihan ini dipakai untuk mencetak satu struk pembayaran akhir dari semua pesanan
            System.out.println("3. Cetak struk total hari ini");

            // pilihan ini dipakai untuk kembali ke menu utama
            System.out.println("4. Kembali ke menu utama");

            // input pilihan submenu dibaca sebagai angka bulat
            int pilihan = inputAngkaBulatPositif(scanner, "Pilih menu riwayat: ");

            // switch dipakai karena submenu punya beberapa pilihan yang jelas
            switch (pilihan) {
                // case ini jalan kalau user ingin melihat semua pesanan
                case RIWAYAT_LIHAT_SEMUA:
                    // semua riwayat dicetak supaya user bisa melihat id pesanan yang tersedia
                    riwayatHarian.cetakRiwayatPesanan();

                    // break dipakai supaya submenu kembali setelah daftar ditampilkan
                    break;

                // case ini jalan kalau user ingin melihat pesanan berdasarkan kategori
                case RIWAYAT_LIHAT_KATEGORI:
                    // function filter dipanggil supaya proses kategori tidak memenuhi submenu
                    tampilkanPesananByType(scanner, riwayatHarian);

                    // break dipakai supaya submenu kembali setelah filter ditampilkan
                    break;

                // case ini jalan kalau user ingin mencetak struk total harian
                case RIWAYAT_CETAK_STRUK_TOTAL:
                    // function cetak struk total dipanggil sesuai alur kasus
                    cetakStrukTotalHarian(riwayatHarian);

                    // break dipakai supaya submenu kembali setelah struk dicetak
                    break;

                // case ini jalan kalau user ingin keluar dari submenu riwayat
                case RIWAYAT_KEMBALI:
                    // false membuat loop submenu berhenti
                    menuRiwayatBerjalan = false;

                    // break dipakai supaya switch selesai setelah user memilih kembali
                    break;

                // default jalan kalau pilihan submenu tidak tersedia
                default:
                    // pesan ini memberi tahu batas angka submenu yang benar
                    System.out.println("Pilihan tidak tersedia. Coba pilih angka 1 sampai 4.");
            }
        }
    }

    // function ini dipakai buat menampilkan pesanan berdasarkan kategori layanan
    // inputnya Scanner dan RiwayatHarian yang berisi daftar pesanan
    // outputnya tidak ada karena hasil filter langsung dicetak ke layar
    // fitur ini memakai getPesananByType(String) yang ada di UML
    private static void tampilkanPesananByType(Scanner scanner, RiwayatHarian riwayatHarian) {
        // daftar kategori dicetak supaya user tahu nilai yang bisa dipilih
        System.out.println("\nKategori: Transportasi, PesananMakanan, CetakTugas");

        // kategori dibaca dari user supaya filter bisa fleksibel
        String kategori = inputTeks(scanner, "Masukkan kategori: ");

        // method getPesananByType dipakai untuk mengambil pesanan yang kategorinya cocok
        List<Layanan> hasil = riwayatHarian.getPesananByType(kategori);

        // judul ini memberi tahu kategori yang sedang ditampilkan
        System.out.println("\nRiwayat kategori " + kategori);

        // garis ini dipakai supaya tampilan filter lebih rapi
        System.out.println("----------------------------------------------");

        // kondisi ini dipakai kalau tidak ada pesanan dengan kategori tersebut
        if (hasil.isEmpty()) {
            // pesan ini memberi tahu user bahwa filter tidak menemukan data
            System.out.println("Tidak ada pesanan untuk kategori ini.");
        }

        // loop ini mencetak semua layanan hasil filter
        for (int i = 0; i < hasil.size(); i++) {
            // layanan diambil dari hasil filter berdasarkan index
            Layanan layanan = hasil.get(i);

            // ringkasan layanan dicetak supaya hasil filter mudah dicek
            System.out.println((i + 1) + ". " + layanan.buatRingkasan());
        }
    }

    // function ini dipakai buat mencetak struk pembayaran akhir untuk semua pesanan hari ini
    // inputnya RiwayatHarian yang menyimpan semua pesanan mahasiswa
    // outputnya tidak ada karena struk langsung dicetak ke layar
    // alur ini dipakai supaya sesuai kasus: satu riwayat harian menghasilkan satu struk akhir
    private static void cetakStrukTotalHarian(RiwayatHarian riwayatHarian) {
        // kondisi ini dipakai supaya struk kosong tidak dicetak sebagai tagihan akhir
        if (riwayatHarian.getPesanan().isEmpty()) {
            // pesan ini memberi tahu bahwa belum ada pesanan yang bisa dihitung
            System.out.println("Belum ada pesanan yang bisa dicetak struk totalnya.");

            // return dipakai supaya proses berhenti ketika riwayat kosong
            return;
        }

        // tanggal cetak dibuat saat mahasiswa meminta tagihan total
        String tanggalCetak = LocalDateTime.now().format(FORMAT_WAKTU);

        // objek struk dibuat dari riwayat dan tanggal cetak sesuai UML
        Struk struk = new Struk(riwayatHarian, tanggalCetak);

        // struk diterbitkan untuk semua pesanan dalam riwayat harian
        struk.terbitkan();
    }

    // function ini dipakai buat menyimpan riwayat ke JSON dan memberi pesan ke user
    // inputnya RiwayatHarian yang akan disimpan
    // outputnya tidak ada karena hasilnya hanya pesan berhasil atau gagal
    // helper ini dipakai supaya proses simpan tidak ditulis ulang di banyak tempat
    private static void simpanRiwayatKeJson(RiwayatHarian riwayatHarian) {
        // hasil penyimpanan dipakai untuk menentukan pesan ke user
        boolean berhasil = PenyimpananJson.simpan(riwayatHarian);

        // kondisi ini jalan kalau file JSON berhasil ditulis
        if (berhasil) {
            // pesan ini memberi tahu lokasi umum penyimpanan data
            System.out.println("Data riwayat tersimpan ke folder data.");
        }
    }

    // function ini dipakai buat membaca input teks yang tidak boleh kosong
    // inputnya Scanner dan teks prompt yang mau ditampilkan
    // outputnya String yang sudah dipastikan tidak kosong
    // loop dipakai supaya user bisa mengulang kalau tidak sengaja menekan Enter kosong
    private static String inputTeks(Scanner scanner, String prompt) {
        // loop tanpa batas dipakai karena function akan berhenti sendiri saat input valid
        while (true) {
            // prompt dicetak tanpa baris baru supaya input user berada di sebelahnya
            System.out.print(prompt);

            // input dibaca lalu trim supaya spasi awal dan akhir tidak dihitung sebagai isi
            String input = scanner.nextLine().trim();

            // kondisi ini memastikan teks benar-benar diisi
            if (!input.isEmpty()) {
                // return ini mengirim teks valid ke bagian yang memanggil function
                return input;
            }

            // pesan ini muncul kalau user memasukkan teks kosong
            System.out.println("Input tidak boleh kosong.");
        }
    }

    // function ini dipakai buat membaca angka positif dari keyboard
    // inputnya Scanner dan prompt yang mau ditampilkan ke user
    // outputnya float yang sudah dipastikan lebih dari 0
    // validasi manual dipakai supaya program tidak berhenti saat user salah input
    private static float inputAngkaPositif(Scanner scanner, String prompt) {
        // loop ini terus berjalan sampai user memasukkan angka yang benar
        while (true) {
            // prompt dicetak supaya user tahu data apa yang harus dimasukkan
            System.out.print(prompt);

            // input dibaca sebagai teks dulu supaya bisa dicek sebelum diubah ke angka
            String input = scanner.nextLine().trim();

            // try dipakai karena parseFloat bisa error kalau input bukan angka
            try {
                // teks input diubah menjadi float supaya bisa dipakai dalam perhitungan
                float angka = Float.parseFloat(input);

                // kondisi ini memastikan angka lebih dari 0
                if (angka > 0) {
                    // return ini mengirim angka valid ke bagian yang membutuhkan
                    return angka;
                }

                // pesan ini muncul kalau angka 0 atau negatif
                System.out.println("Angka harus lebih dari 0.");
            } catch (NumberFormatException error) {
                // pesan ini muncul kalau input tidak bisa diubah menjadi angka
                System.out.println("Input harus berupa angka.");
            }
        }
    }

    // function ini dipakai buat membaca angka bulat positif dari keyboard
    // inputnya Scanner dan prompt yang mau ditampilkan ke user
    // outputnya int yang sudah dipastikan lebih dari 0
    // angka bulat dipakai untuk menu dan jumlah halaman karena nilainya tidak perlu koma
    private static int inputAngkaBulatPositif(Scanner scanner, String prompt) {
        // loop ini terus berjalan sampai user memasukkan angka bulat yang benar
        while (true) {
            // prompt dicetak supaya user tahu data apa yang harus dimasukkan
            System.out.print(prompt);

            // input dibaca sebagai teks dulu supaya bisa divalidasi sebelum jadi angka
            String input = scanner.nextLine().trim();

            // try dipakai karena parseInt bisa error kalau input bukan angka bulat
            try {
                // teks input diubah menjadi int supaya cocok untuk pilihan menu dan halaman
                int angka = Integer.parseInt(input);

                // kondisi ini memastikan angka lebih dari 0
                if (angka > 0) {
                    // return ini mengirim angka bulat yang sudah valid
                    return angka;
                }

                // pesan ini muncul kalau angka 0 atau negatif
                System.out.println("Angka harus lebih dari 0.");
            } catch (NumberFormatException error) {
                // pesan ini muncul kalau input bukan angka bulat
                System.out.println("Input harus berupa angka bulat.");
            }
        }
    }

    // function ini dipakai buat membuat id pesanan otomatis
    // inputnya nomor urut pesanan dalam bentuk int
    // outputnya String seperti TRX-001, TRX-002, dan seterusnya
    // format otomatis dipakai supaya id pesanan rapi dan tidak perlu diketik manual
    private static String buatIdPesanan(int nomorPesanan) {
        // return ini membuat kode dengan tiga digit supaya tampilannya konsisten
        return String.format("TRX-%03d", nomorPesanan);
    }
}
