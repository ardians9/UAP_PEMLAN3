# UAP_PEMLAN3
UAP Pemlan semester 3

Rental Console Application

Deskripsi

Rental Console Application adalah aplikasi berbasis Java Swing untuk mengelola penyewaan konsol permainan. Pengguna dapat memilih konsol, jenis, durasi penyewaan, serta menghitung total harga dengan opsi diskon untuk anggota.

Fitur Utama

Formulir Informasi Penyewaan

Input nama pelanggan.

Pilihan konsol: Nintendo, PlayStation, Xbox.

Pilihan jenis konsol tergantung pada konsol yang dipilih.

Input durasi penyewaan (dalam hari).

Opsi keanggotaan untuk diskon 10%.

Gambar Konsol

Menampilkan gambar konsol sesuai dengan jenis konsol yang dipilih.

Tabel Penyewaan

Menampilkan daftar penyewaan dengan kolom: Nama, Konsol, Jenis, Hari, Keanggotaan, dan Harga.

Opsi untuk menambah, memperbarui, dan menghapus penyewaan.

Hitung Total Harga

Menghitung harga berdasarkan jenis konsol, durasi penyewaan, dan keanggotaan.

Antarmuka yang Interaktif

Perubahan warna latar belakang formulir berdasarkan konsol yang dipilih.

Validasi input untuk memastikan data yang dimasukkan benar.

Struktur Program

Kelas Utama

RentalConsole:

Memulai aplikasi dengan menampilkan antarmuka pengguna.

Kelas GUI

RentalConsoleFrame:

Mengatur elemen-elemen GUI seperti formulir, tabel, dan panel gambar.

Mengatur event listener untuk interaksi pengguna.

Fungsi Utama

updateTypeBox: Mengisi dropdown jenis konsol berdasarkan konsol yang dipilih.

updateConsoleImage: Menampilkan gambar konsol berdasarkan jenis konsol.

updateBackground: Mengubah warna latar belakang formulir sesuai konsol.

addRental: Menambah data penyewaan ke tabel.

updateRental: Memperbarui data penyewaan yang dipilih.

deleteRental: Menghapus data penyewaan yang dipilih.

calculatePrice: Menghitung total harga penyewaan.

clearFields: Mengosongkan semua input.

Cara Penggunaan

Jalankan program menggunakan IDE seperti IntelliJ IDEA atau Eclipse.

Pilih nama, konsol, jenis, durasi, dan keanggotaan di formulir penyewaan.

Klik tombol:

Add: Untuk menambahkan data penyewaan ke tabel.

Update: Untuk memperbarui data yang dipilih di tabel.

Delete: Untuk menghapus data yang dipilih di tabel.

Calculate: Untuk menghitung harga total sebelum menambah data ke tabel.

Lihat tabel di bagian bawah untuk daftar penyewaan.

Struktur File Gambar

Simpan file gambar konsol di dalam folder src/images/. Berikut nama file yang diharapkan:

nintendo_3ds.png

nintendo_switch.png

ps4.png

ps5.png

xbox_one.png

xbox_series_x.png

Validasi Input

Nama pelanggan tidak boleh kosong.

Durasi penyewaan harus berupa angka.

Semua input wajib diisi sebelum menambahkan data ke tabel.

Teknologi

Bahasa Pemrograman: Java

GUI Framework: Java Swing

Build Tools: JDK 8 atau lebih baru

Pengembang

Nama: [Masukkan nama Anda]

Email: [Masukkan email Anda]

GitHub: [Masukkan tautan GitHub Anda]

