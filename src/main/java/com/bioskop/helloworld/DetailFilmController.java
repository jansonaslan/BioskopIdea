package com.bioskop.helloworld;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class DetailFilmController {

    @FXML
    private ImageView posterImage;

    @FXML
    private Label judulLabel;

    @FXML
    private Label genreLabel;

    @FXML
    private Label durasiLabel;

    @FXML
    private Label tahunLabel;

    @FXML
    private Label sutradaraLabel;

    @FXML
    private Label hargaLabel;

    @FXML
    private TextArea sinopsisArea;

    @FXML
    private Button btnKembali;

    @FXML
    private Button btnPilihKursi;

    @FXML
    private ToggleGroup tanggalGroup;

    @FXML
    private ToggleGroup studioGroup;

    @FXML
    private ToggleGroup jamGroup;

    @FXML
    private ToggleButton tgl1;

    @FXML
    private ToggleButton tgl2;

    @FXML
    private ToggleButton tgl3;

    @FXML
    private ToggleButton tgl4;

    @FXML
    private ToggleButton tgl5;

    @FXML
    private ToggleButton tgl16;

    // ===========================
    // Menyimpan pilihan user
    // ===========================

    private String selectedDate;
    private String selectedStudio;
    private String selectedTime;

    private ToggleButton lastTanggal;
    private ToggleButton lastStudio;
    private ToggleButton lastJam;

    @FXML
    public void initialize() {

        btnPilihKursi.setDisable(true);

        LocalDate hariIni = LocalDate.now();

        DateTimeFormatter format =
                DateTimeFormatter.ofPattern("EEE\ndd MMM", new Locale("id","ID"));

        tgl1.setText(hariIni.format(format));
        tgl2.setText(hariIni.plusDays(1).format(format));
        tgl3.setText(hariIni.plusDays(2).format(format));
        tgl4.setText(hariIni.plusDays(3).format(format));

    }

    // =====================================================
    // MENAMPILKAN DATA FILM
    // =====================================================

    public void setFilm(String judul,
                        String genre,
                        String durasi,
                        String tahun,
                        String sutradara,
                        String harga,
                        String sinopsis,
                        Image poster) {

        judulLabel.setText(judul);
        genreLabel.setText("Genre : " + genre);
        durasiLabel.setText("Durasi : " + durasi);
        tahunLabel.setText("Tahun : " + tahun);
        sutradaraLabel.setText("Sutradara : " + sutradara);
        hargaLabel.setText("Harga : " + harga);
        sinopsisArea.setText(sinopsis);

        posterImage.setImage(poster);

        // ===========================
        // SIMPAN KE BOOKING DATA
        // ===========================

        BookingData.judulFilm = judul;
        BookingData.posterFilm = poster;

        try {

            String angka = harga
                    .replace("Rp", "")
                    .replace(".", "")
                    .replace(",", "")
                    .replace(" ", "");

            BookingData.hargaTiket = Integer.parseInt(angka);

        } catch (Exception e) {

            BookingData.hargaTiket = 35000;

        }

    }
    // =====================================================
    // PILIH TANGGAL
    // =====================================================

    @FXML
    private void pilihTanggal(ActionEvent event) {

        ToggleButton button = (ToggleButton) event.getSource();

        if (button == lastTanggal) {

            button.setSelected(false);
            tanggalGroup.selectToggle(null);

            lastTanggal = null;
            selectedDate = null;

        } else {

            lastTanggal = button;
            selectedDate = button.getText();

        }

        cekPilihan();

    }

    // =====================================================
    // PILIH STUDIO
    // =====================================================

    @FXML
    private void pilihStudio(ActionEvent event) {

        ToggleButton button = (ToggleButton) event.getSource();

        if (button == lastStudio) {

            button.setSelected(false);
            studioGroup.selectToggle(null);

            lastStudio = null;
            selectedStudio = null;

        } else {

            lastStudio = button;
            selectedStudio = button.getText();

        }

        cekPilihan();

    }

    // =====================================================
    // PILIH JAM
    // =====================================================

    @FXML
    private void pilihJam(ActionEvent event) {

        ToggleButton button = (ToggleButton) event.getSource();

        if (button == lastJam) {

            button.setSelected(false);
            jamGroup.selectToggle(null);

            lastJam = null;
            selectedTime = null;

        } else {

            lastJam = button;
            selectedTime = button.getText();

        }

        cekPilihan();

    }

    // =====================================================
    // CEK APAKAH SEMUA PILIHAN SUDAH LENGKAP
    // =====================================================

    private void cekPilihan() {

        boolean lengkap =
                selectedDate != null &&
                        selectedStudio != null &&
                        selectedTime != null;

        btnPilihKursi.setDisable(!lengkap);

    }

    // =====================================================
    // RESET STYLE (Opsional)
    // =====================================================

    private void resetTanggal() {

        // Dapat ditambahkan jika ingin mengubah style tombol tanggal

    }

    private void resetStudio() {

        // Dapat ditambahkan jika ingin mengubah style tombol studio

    }

    private void resetJam() {

        // Dapat ditambahkan jika ingin mengubah style tombol jam

    }
    // =====================================================
    // PILIH KURSI
    // =====================================================

    @FXML
    private void pilihKursi() {

        // Simpan data yang dipilih user
        BookingData.studio = selectedStudio;
        BookingData.tanggal = selectedDate;
        BookingData.jam = selectedTime;

        // Pindah ke halaman pilih kursi
        DashboardController.getInstance().loadPage("Seat.fxml");

    }

    // =====================================================
    // KEMBALI
    // =====================================================

    @FXML
    private void kembali() {

        // Bersihkan data booking jika user batal
        BookingData.reset();

        // Kembali ke dashboard
        DashboardController.getInstance().loadPage("DashboardHome.fxml");

    }

}
