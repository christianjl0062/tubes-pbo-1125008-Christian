package marvel.christian.tubes.pbo.view;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class PetugasPanel extends JPanel {
    private JLabel lblInfoUser;
    private JLabel lblStatusPetugas;
    private JComboBox<String> comboNasabah;
    private JComboBox<String> comboJenisSampah;
    private JTextField txtBeratKg;
    private JButton btnSetorSampah;
    private JButton btnLogout;
    private JButton btnHapusAkun;
    private JTextArea txtInfoDaurUlang;
    private JTextArea txtRiwayat;

    public PetugasPanel() {
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        lblInfoUser = new JLabel("Dashboard Petugas");
        add(lblInfoUser, BorderLayout.NORTH);
        JPanel centerPanel = new JPanel(new GridLayout(1, 2, 10, 10));
        JPanel formSetorPanel = new JPanel(new GridLayout(6, 1, 8, 8));
        formSetorPanel.setBorder(BorderFactory.createTitledBorder("Input Setoran Sampah"));

        comboNasabah = new JComboBox<>();
        comboNasabah.setBorder(BorderFactory.createTitledBorder("Pilih Nasabah Tujuan"));

        String[] pilihanSampah = {"Botol Plastik", "Kardus", "Sisa Makanan"};
        comboJenisSampah = new JComboBox<>(pilihanSampah);
        txtBeratKg = new JTextField();
        txtBeratKg.setBorder(BorderFactory.createTitledBorder("Berat Sampah (Kg)"));
        btnSetorSampah = new JButton("Proses Setoran");
        lblStatusPetugas = new JLabel(" ");

        JPanel panelActionAkun = new JPanel(new GridLayout(1, 2, 8, 0));
        btnLogout = new JButton("Logout");
        btnHapusAkun = new JButton("Hapus Akun");
        panelActionAkun.add(btnLogout);
        panelActionAkun.add(btnHapusAkun);

        formSetorPanel.add(comboNasabah);
        formSetorPanel.add(comboJenisSampah);
        formSetorPanel.add(txtBeratKg);
        formSetorPanel.add(btnSetorSampah);
        formSetorPanel.add(lblStatusPetugas);
        formSetorPanel.add(panelActionAkun);

        JPanel rightPanel = new JPanel(new GridLayout(2, 1, 8, 8));
        txtInfoDaurUlang = new JTextArea();
        txtInfoDaurUlang.setEditable(false);
        txtInfoDaurUlang.setLineWrap(true);
        txtInfoDaurUlang.setWrapStyleWord(true);
        JScrollPane scrollInfo = new JScrollPane(txtInfoDaurUlang);
        scrollInfo.setBorder(BorderFactory.createTitledBorder("Info & Instruksi Daur Ulang"));

        txtRiwayat = new JTextArea();
        txtRiwayat.setEditable(false);
        JScrollPane scrollRiwayat = new JScrollPane(txtRiwayat);
        scrollRiwayat.setBorder(BorderFactory.createTitledBorder("Riwayat Setoran"));

        rightPanel.add(scrollInfo);
        rightPanel.add(scrollRiwayat);

        centerPanel.add(formSetorPanel);
        centerPanel.add(rightPanel);
        add(centerPanel, BorderLayout.CENTER);
    }

    public void setInfoUser(String info) {
        lblInfoUser.setText(info);
    }

    public void setDaftarNasabah(List<String> listUsername) {
        comboNasabah.removeAllItems();
        for (int i = 0; i < listUsername.size(); i++) {
            comboNasabah.addItem(listUsername.get(i));
        }
    }

    public String getSelectedNasabah() {
        if (comboNasabah.getSelectedItem() != null) {
            return (String) comboNasabah.getSelectedItem();
        }
        return "";
    }

    public String getSelectedJenisSampah() {
        return (String) comboJenisSampah.getSelectedItem();
    }

    public String getBeratKgInput() {
        return txtBeratKg.getText();
    }

    public void clearBeratKg() {
        txtBeratKg.setText("");
    }

    public void setStatusPetugas(String pesan) {
        lblStatusPetugas.setText(pesan);
    }

    public void setInfoDaurUlang(String teks) {
        txtInfoDaurUlang.setText(teks);
    }

    public JButton getBtnSetorSampah() {
        return btnSetorSampah;
    }

    public JButton getBtnLogout() {
        return btnLogout;
    }

    public JButton getBtnHapusAkun() {
        return btnHapusAkun;
    }

    public void setRiwayatText(String teks) {
        txtRiwayat.setText(teks);
    }
}