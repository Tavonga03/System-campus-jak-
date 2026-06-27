// import IOException dipakai karena proses baca tulis file bisa gagal
import java.io.IOException;

// import Files dipakai buat membuat folder, membaca file, dan menulis file
import java.nio.file.Files;

// import Path dipakai buat menyimpan lokasi folder dan file JSON
import java.nio.file.Path;

// import Paths dipakai buat membuat Path dari teks lokasi file
import java.nio.file.Paths;

// import ArrayList dipakai saat memecah daftar objek pesanan dari JSON
import java.util.ArrayList;

// import List dipakai supaya hasil pecahan pesanan bisa disimpan sebagai daftar
import java.util.List;

// import Matcher dipakai untuk mengambil nilai field JSON dengan regex sederhana
import java.util.regex.Matcher;

// import Pattern dipakai untuk membuat pola regex pencarian field JSON
import java.util.regex.Pattern;

// class ini dipakai buat menyimpan dan memuat data Campus-Jek ke file JSON
public class PenyimpananJson {
    // folder data dipakai supaya file JSON tidak bercampur dengan file source code
    private static final Path FOLDER_DATA = Paths.get("data");

    // function ini dipakai buat memuat data mahasiswa berdasarkan NIM
    // inputnya nim mahasiswa yang diketik user
    // outputnya DataMahasiswa kalau file ditemukan, atau null kalau belum ada data
    // JSON dipakai supaya data tetap tersimpan walaupun program ditutup
    public static DataMahasiswa muat(String nim) {
        // path file dibuat dari NIM supaya satu mahasiswa punya satu file JSON sendiri
        Path file = buatPathFile(nim);

        // kondisi ini dipakai kalau mahasiswa belum pernah disimpan sebelumnya
        if (!Files.exists(file)) {
            // return null memberi tanda bahwa data mahasiswa belum ada
            return null;
        }

        // try dipakai karena proses baca file bisa gagal
        try {
            // isi file dibaca menjadi String supaya bisa diproses sebagai JSON sederhana
            String json = Files.readString(file);

            // bagian mahasiswa dipotong supaya field profil lebih mudah diambil
            String bagianMahasiswa = ambilObject(json, "mahasiswa");

            // bagian riwayat dipotong supaya tanggal dan pesanan bisa diambil
            String bagianRiwayat = ambilObject(json, "riwayatHarian");

            // nama mahasiswa diambil dari field JSON
            String nama = ambilString(bagianMahasiswa, "nama");

            // nim mahasiswa diambil dari field JSON
            String nimMahasiswa = ambilString(bagianMahasiswa, "nim");

            // prodi mahasiswa diambil dari field JSON
            String prodi = ambilString(bagianMahasiswa, "prodi");

            // angkatan mahasiswa diambil dari field JSON
            String angkatan = ambilString(bagianMahasiswa, "angkatan");

            // objek mahasiswa dibuat ulang dari data JSON
            Mahasiswa mahasiswa = new Mahasiswa(nama, nimMahasiswa, prodi, angkatan);

            // tanggal riwayat diambil dari field JSON
            String tanggal = ambilString(bagianRiwayat, "tanggal");

            // objek riwayat dibuat ulang dari profil mahasiswa dan tanggal yang tersimpan
            RiwayatHarian riwayatHarian = new RiwayatHarian(mahasiswa, tanggal);

            // array pesanan dipotong supaya setiap objek layanan bisa dibaca ulang
            String bagianPesanan = ambilArray(bagianRiwayat, "pesanan");

            // daftar objek JSON layanan dipecah satu per satu
            List<String> daftarObjectPesanan = pecahObjectArray(bagianPesanan);

            // loop ini membuat ulang setiap layanan dari JSON
            for (String objectPesanan : daftarObjectPesanan) {
                // layanan dibuat berdasarkan kategori yang tersimpan
                Layanan layanan = buatLayananDariJson(objectPesanan);

                // kondisi ini dipakai supaya data rusak tidak langsung membuat program error
                if (layanan != null) {
                    // layanan yang berhasil dibuat dimasukkan lagi ke riwayat
                    riwayatHarian.tambahPesanan(layanan);
                }
            }

            // return ini mengirim profil dan riwayat yang sudah berhasil dimuat
            return new DataMahasiswa(mahasiswa, riwayatHarian);
        } catch (IOException error) {
            // pesan ini membantu user tahu kalau file gagal dibaca
            System.out.println("Gagal membaca data JSON: " + error.getMessage());

            // return null dipakai supaya program tetap bisa lanjut input data baru
            return null;
        }
    }

    // function ini dipakai buat menyimpan riwayat harian ke file JSON
    // inputnya objek RiwayatHarian yang berisi mahasiswa dan daftar pesanan
    // outputnya boolean true kalau berhasil, false kalau gagal
    // penyimpanan dilakukan setiap ada pesanan baru supaya data tidak hilang
    public static boolean simpan(RiwayatHarian riwayatHarian) {
        // try dipakai karena proses membuat folder atau menulis file bisa gagal
        try {
            // folder data dibuat kalau belum ada
            Files.createDirectories(FOLDER_DATA);

            // path file dibuat dari NIM mahasiswa
            Path file = buatPathFile(riwayatHarian.getMahasiswa().getNim());

            // JSON dibuat dari isi riwayat harian
            String json = ubahKeJson(riwayatHarian);

            // JSON ditulis ke file supaya bisa dimuat lagi nanti
            Files.writeString(file, json);

            // return true memberi tanda bahwa penyimpanan berhasil
            return true;
        } catch (IOException error) {
            // pesan ini muncul kalau file gagal disimpan
            System.out.println("Gagal menyimpan data JSON: " + error.getMessage());

            // return false memberi tanda bahwa penyimpanan gagal
            return false;
        }
    }

    // function ini dipakai buat membuat path file JSON dari NIM
    // inputnya nim mahasiswa
    // outputnya Path menuju file data mahasiswa
    // NIM dibersihkan supaya aman dipakai sebagai nama file
    private static Path buatPathFile(String nim) {
        // nama file dibuat dari NIM dengan karakter aneh diganti garis bawah
        String namaFile = nim.replaceAll("[^A-Za-z0-9_-]", "_") + ".json";

        // return ini mengirim path lengkap ke folder data
        return FOLDER_DATA.resolve(namaFile);
    }

    // function ini dipakai buat mengubah RiwayatHarian menjadi teks JSON
    // inputnya objek RiwayatHarian
    // outputnya String berisi JSON yang rapi
    // StringBuilder dipakai supaya penyusunan teks panjang lebih mudah dibaca
    private static String ubahKeJson(RiwayatHarian riwayatHarian) {
        // mahasiswa diambil dulu supaya field profil lebih singkat ditulis
        Mahasiswa mahasiswa = riwayatHarian.getMahasiswa();

        // builder dipakai buat menyusun JSON baris demi baris
        StringBuilder json = new StringBuilder();

        // baris pembuka object utama JSON
        json.append("{\n");

        // bagian profil mahasiswa dibuka
        json.append("  \"mahasiswa\": {\n");

        // nama mahasiswa ditulis sebagai string JSON
        json.append("    \"nama\": \"").append(escape(mahasiswa.getNama())).append("\",\n");

        // nim mahasiswa ditulis sebagai string JSON
        json.append("    \"nim\": \"").append(escape(mahasiswa.getNim())).append("\",\n");

        // prodi mahasiswa ditulis sebagai string JSON
        json.append("    \"prodi\": \"").append(escape(mahasiswa.getProdi())).append("\",\n");

        // angkatan mahasiswa ditulis sebagai string JSON
        json.append("    \"angkatan\": \"").append(escape(mahasiswa.getAngkatan())).append("\"\n");

        // bagian profil mahasiswa ditutup
        json.append("  },\n");

        // bagian riwayat harian dibuka
        json.append("  \"riwayatHarian\": {\n");

        // tanggal riwayat ditulis sebagai string JSON
        json.append("    \"tanggal\": \"").append(escape(riwayatHarian.getTanggal())).append("\",\n");

        // array pesanan dibuka
        json.append("    \"pesanan\": [\n");

        // loop ini menulis semua pesanan ke JSON
        for (int i = 0; i < riwayatHarian.getPesanan().size(); i++) {
            // layanan diambil berdasarkan index supaya koma antar item bisa diatur
            Layanan layanan = riwayatHarian.getPesanan().get(i);

            // object layanan ditulis sesuai kategori aslinya
            json.append(ubahLayananKeJson(layanan));

            // koma ditambahkan kalau layanan ini bukan item terakhir
            if (i < riwayatHarian.getPesanan().size() - 1) {
                // koma ini memisahkan object layanan di array JSON
                json.append(",");
            }

            // pindah baris supaya JSON lebih mudah dibaca
            json.append("\n");
        }

        // array pesanan ditutup
        json.append("    ]\n");

        // bagian riwayat harian ditutup
        json.append("  }\n");

        // object utama JSON ditutup
        json.append("}\n");

        // return ini mengirim JSON lengkap
        return json.toString();
    }

    // function ini dipakai buat mengubah satu layanan menjadi object JSON
    // inputnya objek Layanan yang bisa berbeda subclass
    // outputnya String JSON untuk satu layanan
    // instanceof dipakai saat menyimpan karena tiap subclass punya atribut khusus
    private static String ubahLayananKeJson(Layanan layanan) {
        // builder dipakai buat menyusun JSON satu layanan
        StringBuilder json = new StringBuilder();

        // object layanan dibuka dengan indentasi supaya rapi
        json.append("      {\n");

        // kategori ditulis supaya nanti bisa tahu subclass yang harus dibuat saat load
        json.append("        \"kategori\": \"").append(escape(layanan.getKategori())).append("\",\n");

        // id layanan ditulis supaya nomor transaksi tetap sama saat dimuat
        json.append("        \"id\": \"").append(escape(layanan.getId())).append("\",\n");

        // nama layanan ditulis supaya JSON tetap mudah dibaca
        json.append("        \"nama\": \"").append(escape(layanan.getNama())).append("\",\n");

        // waktu layanan ditulis supaya riwayat menyimpan waktu pesanan
        json.append("        \"waktu\": \"").append(escape(layanan.getWaktu())).append("\"");

        // kondisi ini menulis field khusus untuk transportasi
        if (layanan instanceof LayananTransportasi) {
            // layanan dicasting supaya getter khusus transportasi bisa dipakai
            LayananTransportasi transportasi = (LayananTransportasi) layanan;

            // tujuan transportasi ditulis ke JSON
            json.append(",\n        \"tujuan\": \"").append(escape(transportasi.getTujuan())).append("\",\n");

            // jarak transportasi ditulis ke JSON sebagai angka
            json.append("        \"jarak\": ").append(transportasi.getJarak()).append(",\n");

            // tarif per km ditulis ke JSON sebagai angka
            json.append("        \"tarifPerKm\": ").append(transportasi.getTarifPerKm());
        }

        // kondisi ini menulis field khusus untuk pesanan makanan
        if (layanan instanceof PesananMakanan) {
            // layanan dicasting supaya getter khusus makanan bisa dipakai
            PesananMakanan makanan = (PesananMakanan) layanan;

            // nama makanan ditulis ke JSON
            json.append(",\n        \"namaItem\": \"").append(escape(makanan.getNamaItem())).append("\",\n");

            // harga makanan ditulis ke JSON sebagai angka
            json.append("        \"hargaMakanan\": ").append(makanan.getHargaMakanan()).append(",\n");

            // ongkos kirim ditulis ke JSON sebagai angka
            json.append("        \"ongkosKirim\": ").append(makanan.getOngkosKirim());
        }

        // kondisi ini menulis field khusus untuk cetak tugas
        if (layanan instanceof LayananCetakTugas) {
            // layanan dicasting supaya getter khusus cetak tugas bisa dipakai
            LayananCetakTugas cetak = (LayananCetakTugas) layanan;

            // nama dokumen ditulis ke JSON
            json.append(",\n        \"namaDokumen\": \"").append(escape(cetak.getNamaDokumen())).append("\",\n");

            // jumlah halaman ditulis ke JSON sebagai angka
            json.append("        \"jumlahHalaman\": ").append(cetak.getJumlahHalaman()).append(",\n");

            // harga per lembar ditulis ke JSON sebagai angka
            json.append("        \"hargaPerLembar\": ").append(cetak.getHargaPerLembar()).append(",\n");

            // biaya jilid ditulis ke JSON sebagai angka
            json.append("        \"biayaJilid\": ").append(cetak.getBiayaJilid());
        }

        // object layanan ditutup
        json.append("\n      }");

        // return ini mengirim JSON untuk satu layanan
        return json.toString();
    }

    // function ini dipakai buat membuat objek layanan dari JSON satu pesanan
    // inputnya object JSON layanan dalam bentuk String
    // outputnya objek Layanan sesuai kategori, atau null kalau kategori tidak dikenal
    // kategori dipakai supaya program tahu subclass mana yang harus dibuat
    private static Layanan buatLayananDariJson(String objectPesanan) {
        // kategori layanan diambil dari JSON
        String kategori = ambilString(objectPesanan, "kategori");

        // id layanan diambil dari JSON
        String id = ambilString(objectPesanan, "id");

        // waktu layanan diambil dari JSON
        String waktu = ambilString(objectPesanan, "waktu");

        // kondisi ini membuat ulang layanan transportasi
        if (kategori.equalsIgnoreCase("Transportasi")) {
            // tujuan diambil dari JSON transportasi
            String tujuan = ambilString(objectPesanan, "tujuan");

            // jarak diambil dari JSON transportasi
            float jarak = ambilFloat(objectPesanan, "jarak");

            // tarif per km diambil dari JSON transportasi
            float tarifPerKm = ambilFloat(objectPesanan, "tarifPerKm");

            // return ini mengirim objek transportasi yang sudah dibuat ulang
            return new LayananTransportasi(id, waktu, tujuan, jarak, tarifPerKm);
        }

        // kondisi ini membuat ulang pesanan makanan
        if (kategori.equalsIgnoreCase("PesananMakanan")) {
            // nama item diambil dari JSON makanan
            String namaItem = ambilString(objectPesanan, "namaItem");

            // harga makanan diambil dari JSON makanan
            float hargaMakanan = ambilFloat(objectPesanan, "hargaMakanan");

            // ongkos kirim diambil dari JSON makanan
            float ongkosKirim = ambilFloat(objectPesanan, "ongkosKirim");

            // return ini mengirim objek pesanan makanan yang sudah dibuat ulang
            return new PesananMakanan(id, waktu, namaItem, hargaMakanan, ongkosKirim);
        }

        // kondisi ini membuat ulang layanan cetak tugas
        if (kategori.equalsIgnoreCase("CetakTugas")) {
            // nama dokumen diambil dari JSON cetak tugas
            String namaDokumen = ambilString(objectPesanan, "namaDokumen");

            // jumlah halaman diambil dari JSON cetak tugas
            int jumlahHalaman = ambilInt(objectPesanan, "jumlahHalaman");

            // harga per lembar diambil dari JSON cetak tugas
            float hargaPerLembar = ambilFloat(objectPesanan, "hargaPerLembar");

            // biaya jilid diambil dari JSON cetak tugas
            float biayaJilid = ambilFloat(objectPesanan, "biayaJilid");

            // return ini mengirim objek cetak tugas yang sudah dibuat ulang
            return new LayananCetakTugas(id, waktu, namaDokumen, jumlahHalaman, hargaPerLembar, biayaJilid);
        }

        // return null dipakai kalau kategori di JSON tidak dikenal
        return null;
    }

    // function ini dipakai buat mengambil object JSON berdasarkan nama field
    // inputnya teks JSON dan nama field object
    // outputnya isi object lengkap termasuk kurung kurawal
    // pencarian manual dipakai karena kita tidak memakai library JSON tambahan
    private static String ambilObject(String json, String field) {
        // posisi nama field dicari di teks JSON
        int posisiField = json.indexOf("\"" + field + "\"");

        // kondisi ini dipakai kalau field tidak ditemukan
        if (posisiField < 0) {
            // return object kosong supaya pemanggil tidak langsung error
            return "{}";
        }

        // posisi kurung pembuka object dicari setelah nama field
        int mulai = json.indexOf("{", posisiField);

        // posisi akhir object dicari dengan menghitung pasangan kurung
        int selesai = cariPenutup(json, mulai, '{', '}');

        // return ini mengambil potongan object dari JSON
        return json.substring(mulai, selesai + 1);
    }

    // function ini dipakai buat mengambil array JSON berdasarkan nama field
    // inputnya teks JSON dan nama field array
    // outputnya isi array lengkap termasuk kurung siku
    // pencarian manual cukup aman karena file dibaca dari format yang kita tulis sendiri
    private static String ambilArray(String json, String field) {
        // posisi nama field dicari di teks JSON
        int posisiField = json.indexOf("\"" + field + "\"");

        // kondisi ini dipakai kalau field tidak ditemukan
        if (posisiField < 0) {
            // return array kosong supaya program tetap bisa lanjut
            return "[]";
        }

        // posisi kurung pembuka array dicari setelah nama field
        int mulai = json.indexOf("[", posisiField);

        // posisi akhir array dicari dengan menghitung pasangan kurung siku
        int selesai = cariPenutup(json, mulai, '[', ']');

        // return ini mengambil potongan array dari JSON
        return json.substring(mulai, selesai + 1);
    }

    // function ini dipakai buat mencari penutup pasangan kurung
    // inputnya teks, posisi pembuka, karakter pembuka, dan karakter penutup
    // outputnya index karakter penutup yang cocok
    // perhitungan level dipakai supaya object atau array bersarang tetap terbaca benar
    private static int cariPenutup(String teks, int mulai, char pembuka, char penutup) {
        // level dipakai untuk menghitung kedalaman kurung
        int level = 0;

        // loop berjalan dari posisi pembuka sampai akhir teks
        for (int i = mulai; i < teks.length(); i++) {
            // karakter saat ini diambil supaya bisa dibandingkan
            char karakter = teks.charAt(i);

            // kondisi ini menaikkan level saat menemukan pembuka
            if (karakter == pembuka) {
                // level dinaikkan karena masuk ke object atau array baru
                level++;
            }

            // kondisi ini menurunkan level saat menemukan penutup
            if (karakter == penutup) {
                // level diturunkan karena keluar dari object atau array
                level--;

                // kondisi ini berarti penutup yang dicari sudah ditemukan
                if (level == 0) {
                    // return ini mengirim posisi penutup yang cocok
                    return i;
                }
            }
        }

        // return posisi terakhir dipakai sebagai fallback kalau JSON rusak
        return teks.length() - 1;
    }

    // function ini dipakai buat memecah array pesanan menjadi beberapa object JSON
    // inputnya teks array JSON
    // outputnya List<String> berisi object pesanan satu per satu
    // pemecahan manual dipakai karena pesanan disimpan sebagai object flat
    private static List<String> pecahObjectArray(String arrayJson) {
        // hasil dibuat kosong dulu untuk menampung object yang ditemukan
        List<String> hasil = new ArrayList<String>();

        // index dipakai untuk berjalan di dalam teks array
        int index = 0;

        // loop berjalan sampai seluruh teks array selesai dicek
        while (index < arrayJson.length()) {
            // posisi object pembuka dicari dari index saat ini
            int mulai = arrayJson.indexOf("{", index);

            // kondisi ini berarti tidak ada object lagi di array
            if (mulai < 0) {
                // break dipakai supaya loop berhenti
                break;
            }

            // posisi object penutup dicari dengan pasangan kurung
            int selesai = cariPenutup(arrayJson, mulai, '{', '}');

            // object yang ditemukan dimasukkan ke list hasil
            hasil.add(arrayJson.substring(mulai, selesai + 1));

            // index dipindah ke setelah object yang baru selesai
            index = selesai + 1;
        }

        // return ini mengirim daftar object pesanan
        return hasil;
    }

    // function ini dipakai buat mengambil nilai string dari field JSON
    // inputnya object JSON dan nama field
    // outputnya nilai String yang sudah dibuka escape sederhana
    // regex dipakai karena field yang dibaca berbentuk key-value sederhana
    private static String ambilString(String json, String field) {
        // pattern dibuat untuk mencari "field": "nilai"
        Pattern pattern = Pattern.compile("\"" + field + "\"\\s*:\\s*\"((?:\\\\.|[^\"])*)\"");

        // matcher dipakai untuk menjalankan pattern ke teks JSON
        Matcher matcher = pattern.matcher(json);

        // kondisi ini dipakai kalau field ditemukan
        if (matcher.find()) {
            // return ini mengirim nilai string yang sudah di-unescape
            return unescape(matcher.group(1));
        }

        // return kosong dipakai kalau field tidak ditemukan
        return "";
    }

    // function ini dipakai buat mengambil nilai float dari field JSON
    // inputnya object JSON dan nama field
    // outputnya float dari field tersebut
    // angka diparse manual karena JSON yang dipakai tidak memakai library
    private static float ambilFloat(String json, String field) {
        // nilai angka diambil sebagai string dulu
        String nilai = ambilAngka(json, field);

        // kondisi ini dipakai kalau field angka tidak ditemukan
        if (nilai.isEmpty()) {
            // return 0 supaya program tidak error saat data kurang lengkap
            return 0;
        }

        // return ini mengubah teks angka menjadi float
        return Float.parseFloat(nilai);
    }

    // function ini dipakai buat mengambil nilai int dari field JSON
    // inputnya object JSON dan nama field
    // outputnya int dari field tersebut
    // int dipakai untuk jumlah halaman yang tidak butuh koma
    private static int ambilInt(String json, String field) {
        // nilai angka diambil sebagai string dulu
        String nilai = ambilAngka(json, field);

        // kondisi ini dipakai kalau field angka tidak ditemukan
        if (nilai.isEmpty()) {
            // return 0 supaya program tidak error saat data kurang lengkap
            return 0;
        }

        // return ini mengubah teks angka menjadi int
        return Integer.parseInt(nilai);
    }

    // function ini dipakai buat mengambil angka dalam bentuk teks dari JSON
    // inputnya object JSON dan nama field
    // outputnya String angka
    // regex ini mendukung angka bulat dan desimal
    private static String ambilAngka(String json, String field) {
        // pattern dibuat untuk mencari "field": angka
        Pattern pattern = Pattern.compile("\"" + field + "\"\\s*:\\s*(-?\\d+(?:\\.\\d+)?)");

        // matcher dipakai untuk menjalankan pattern ke object JSON
        Matcher matcher = pattern.matcher(json);

        // kondisi ini dipakai kalau angka ditemukan
        if (matcher.find()) {
            // return ini mengirim teks angka yang ditemukan
            return matcher.group(1);
        }

        // return kosong dipakai kalau field angka tidak ditemukan
        return "";
    }

    // function ini dipakai buat mengamankan string sebelum ditulis ke JSON
    // inputnya teks biasa dari user atau objek
    // outputnya teks yang aman untuk string JSON
    // escape dipakai supaya tanda kutip dan backslash tidak merusak file JSON
    private static String escape(String teks) {
        // backslash diganti dulu supaya tidak bentrok dengan escape lain
        String hasil = teks.replace("\\", "\\\\");

        // tanda kutip diganti supaya tetap valid di JSON
        hasil = hasil.replace("\"", "\\\"");

        // return ini mengirim teks yang sudah aman
        return hasil;
    }

    // function ini dipakai buat membuka escape sederhana dari string JSON
    // inputnya teks dari JSON
    // outputnya teks asli yang bisa dipakai program
    // unescape dipakai supaya data yang dimuat sama seperti sebelum disimpan
    private static String unescape(String teks) {
        // tanda kutip yang di-escape dikembalikan menjadi tanda kutip biasa
        String hasil = teks.replace("\\\"", "\"");

        // backslash yang di-escape dikembalikan menjadi satu backslash
        hasil = hasil.replace("\\\\", "\\");

        // return ini mengirim teks yang sudah dibuka escape-nya
        return hasil;
    }
}
