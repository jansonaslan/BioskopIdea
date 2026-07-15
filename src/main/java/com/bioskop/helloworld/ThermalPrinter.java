package com.bioskop.helloworld;

import javax.print.Doc;
import javax.print.DocFlavor;
import javax.print.DocPrintJob;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.SimpleDoc;

import java.nio.charset.StandardCharsets;

public class ThermalPrinter {

    // Nama printer harus sama persis dengan yang muncul di Windows
    private static final String PRINTER_NAME = "POSPrinter POS58";

    public void printReceipt() {

        StringBuilder sb = new StringBuilder();

        sb.append("================================\n");
        sb.append("            CINEMAX\n");
        sb.append("================================\n");
        sb.append("No Transaksi : ").append(BookingData.nomorTransaksi).append("\n");
        sb.append("Film         : ").append(BookingData.judulFilm).append("\n");
        sb.append("Studio       : ").append(BookingData.studio).append("\n");
        sb.append("Tanggal      : ").append(BookingData.tanggal).append("\n");
        sb.append("Jam          : ").append(BookingData.jam).append("\n");
        sb.append("Kursi        : ").append(String.join(", ", BookingData.kursi)).append("\n");

        int subtotal = BookingData.hargaTiket * BookingData.kursi.size();
        int admin = 2500;
        int total = subtotal + admin;

        sb.append("--------------------------------\n");
        sb.append("Jumlah Tiket : ").append(BookingData.kursi.size()).append("\n");
        sb.append("Subtotal     : Rp").append(subtotal).append("\n");
        sb.append("Admin        : Rp").append(admin).append("\n");
        sb.append("--------------------------------\n");
        sb.append("TOTAL        : Rp").append(total).append("\n");
        sb.append("================================\n");
        sb.append("     TERIMA KASIH\n");
        sb.append("   SELAMAT MENONTON\n");
        sb.append("================================\n\n\n\n");

        print(sb.toString());
    }

    private void print(String text) {

        try {

            PrintService[] services =
                    PrintServiceLookup.lookupPrintServices(null, null);

            System.out.println("===== DAFTAR PRINTER =====");

            PrintService printer = null;

            for (PrintService service : services) {

                System.out.println(service.getName());

                if (service.getName().equalsIgnoreCase(PRINTER_NAME)) {

                    printer = service;

                    System.out.println("Printer ditemukan : "
                            + service.getName());

                    break;
                }
            }

            if (printer == null) {

                System.out.println("Printer tidak ditemukan!");

                return;
            }

            DocPrintJob job = printer.createPrintJob();

            byte[] bytes = text.getBytes(StandardCharsets.US_ASCII);

            DocFlavor flavor = DocFlavor.BYTE_ARRAY.AUTOSENSE;

            Doc doc = new SimpleDoc(bytes, flavor, null);

            job.print(doc, null);

            System.out.println("Struk berhasil dikirim ke printer.");

        } catch (Exception e) {

            e.printStackTrace();

        }

    }

}