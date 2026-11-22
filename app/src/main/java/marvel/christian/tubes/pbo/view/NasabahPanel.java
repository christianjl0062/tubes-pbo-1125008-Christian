package marvel.christian.tubes.pbo.view;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class NasabahPanel extends JPanel {
    private JLabel lblInfoUser;
    private JLabel lblSaldo;
    private JLabel lblStatusNasabah;
    private JTextField txtJumlahTukar;
    private JButton btnTukarUang;
    private JButton btnLogout;
    private JTextArea txtRiwayat;

    public NasabahPanel() {
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        JPanel topPanel = new JPanel(new GridLayout(2, 1, 5, 5));
        lblInfoUser = new JLabel("Dashboard Nasabah");
        lblSaldo = new JLabel("Saldo Anda: Rp 0");
        topPanel.add(lblInfoUser);
        topPanel.add(lblSaldo);
        add(topPanel, BorderLayout.NORTH);

        JPanel centerPanel = new JPanel(new GridLayout(1, 2, 10, 10));

        JPanel formTukarPanel = new JPanel(new GridLayout(4, 1, 8, 8));
        formTukarPanel.setBorder(BorderFactory.createTitledBorder("Tukar Saldo ke Uang"));
        txtJumlahTukar = new JTextField();
        txtJumlahTukar.setBorder(BorderFactory.createTitledBorder("Nominal Penukaran (Rp)"));
        btnTukarUang = new JButton("Tukar Ke Uang Tunai");
        lblStatusNasabah = new JLabel(" ");
        btnLogout = new JButton("Logout");

        formTukarPanel.add(txtJumlahTukar);
        formTukarPanel.add(btnTukarUang);
        formTukarPanel.add(lblStatusNasabah);
        formTukarPanel.add(btnLogout);

        txtRiwayat = new JTextArea();
        txtRiwayat.setEditable(false);
        JScrollPane scrollRiwayat = new JScrollPane(txtRiwayat);
        scrollRiwayat.setBorder(BorderFactory.createTitledBorder("Riwayat Transaksi"));

        centerPanel.add(formTukarPanel);
        centerPanel.add(scrollRiwayat);
        add(centerPanel, BorderLayout.CENTER);
    }


    public void setInfoUser(String info) {
        lblInfoUser.setText(info);
    }

    public void setSaldoText(double saldo) {
        lblSaldo.setText("Saldo Anda: Rp " + saldo);
    }

    public String getJumlahTukarInput() {
        return txtJumlahTukar.getText();
    }

    public void clearJumlahTukar() {
        txtJumlahTukar.setText("");
    }

    public void setStatusNasabah(String pesan) {
        lblStatusNasabah.setText(pesan);
    }

    public JButton getBtnTukarUang() {
        return btnTukarUang;
    }

    public JButton getBtnLogout() {
        return btnLogout;
    }

    public void setRiwayatText(String teks) {
        txtRiwayat.setText(teks);
    }
}
