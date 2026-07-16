package com.bioskop.helloworld;

import javafx.scene.image.Image;
import java.util.ArrayList;
import java.util.List;

public class BookingData {

    // ==========================
    // DATA FILM
    // ==========================

    public static String judulFilm = "";
    public static String studio = "";
    public static String tanggal = "";
    public static String jam = "";

    public static Image posterFilm = null;

    // ==========================
    // DATA TIKET
    // ==========================

    // Harga 1 tiket (diambil dari film yang dipilih)
    public static int hargaTiket = 0;

    // Daftar kursi yang dipilih
    public static List<String> kursi = new ArrayList<>();

    // Jumlah tiket
    public static int jumlahTiket = 0;

    // Harga sebelum biaya admin
    public static int subtotal = 0;

    // ==========================
    // DATA PEMBAYARAN
    // ==========================

    // Biaya admin
    public static int biayaAdmin = 2500;

    // Total akhir pembayaran
    public static int totalBayar = 0;

    // Metode pembayaran
    public static String metodePembayaran = "";

    // Nomor transaksi
    public static String nomorTransaksi = "";

    // ==========================
    // RESET DATA
    // ==========================

    public static void reset() {

        // Film
        judulFilm = "";
        studio = "";
        tanggal = "";
        jam = "";
        posterFilm = null;

        // Tiket
        hargaTiket = 0;
        kursi.clear();
        jumlahTiket = 0;
        subtotal = 0;

        // Pembayaran
        biayaAdmin = 2500;
        totalBayar = 0;
        metodePembayaran = "";
        nomorTransaksi = "";
    }
}