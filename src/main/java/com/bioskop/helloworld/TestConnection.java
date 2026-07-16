package com.bioskop.helloworld;

import java.sql.Connection;

public class TestConnection {
    public static void main(String[] args) {
        Connection conn = Koneksi.getConnection();

        if (conn != null) {
            System.out.println("Berhasil terhubung ke database.");
        } else {
            System.out.println("Gagal terhubung ke database.");
        }
    }
}