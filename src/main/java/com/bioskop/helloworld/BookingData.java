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

    public static Image posterFilm;

    // ==========================
    // DATA TIKET
    // ==========================

    public static int hargaTiket = 35000;

    public static List<String> kursi = new ArrayList<>();

    // ==========================
    // DATA PEMBAYARAN
    // ==========================

    public static int biayaAdmin = 2500;

    public static String metodePembayaran = "";

    public static String nomorTransaksi = "";

    // ==========================
    // RESET
    // ==========================

    public static void reset() {

        judulFilm = "";
        studio = "";
        tanggal = "";
        jam = "";

        posterFilm = null;

        hargaTiket = 35000;

        kursi.clear();

        biayaAdmin = 2500;

        metodePembayaran = "";

        nomorTransaksi = "";
    }

}