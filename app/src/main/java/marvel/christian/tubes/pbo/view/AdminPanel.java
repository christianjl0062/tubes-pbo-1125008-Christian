package marvel.christian.tubes.pbo.view;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class AdminPanel extends JPanel {
    private JLabel lblInfoUser;
    private JLabel lblStatusAdmin;
    private JComboBox<String> cmbStrategiHarga;
    private JTextField txtBonusPersen;
    private JButton btnSimpanStrategi;
    private JButton btnLogout;
    private JButton btnHapusAkun;
    private JTextArea txtRiwayat;

    public AdminPanel() {
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        lblInfoUser = new JLabel("Dashboard Admin");
        add(lblInfoUser, BorderLayout.NORTH);

        JPanel centerPanel = new JPanel(new GridLayout(1, 2, 10, 10));

        JPanel pengaturanPanel = new JPanel(new GridLayout(5, 1, 8, 8));
        pengaturanPanel.setBorder(BorderFactory.createTitledBorder("Pengaturan Strategi Harga"));

        String[] pilihanStrategi = {"Harga Normal", "Harga Bonus"};
        cmbStrategiHarga = new JComboBox<>(pilihanStrategi);
        txtBonusPersen = new JTextField("10");
        txtBonusPersen.setBorder(BorderFactory.createTitledBorder("Persentase Bonus (%)"));
        txtBonusPersen.setEnabled(false);

        btnSimpanStrategi = new JButton("Terapkan Strategi");
        lblStatusAdmin = new JLabel("Strategi aktif: Harga Normal");
        
        JPanel panelAksiAkun = new JPanel(new GridLayout(1, 2, 8, 0));
        btnLogout = new JButton("Logout");
        btnHapusAkun = new JButton("Hapus Akun");
        panelAksiAkun.add(btnLogout);
        panelAksiAkun.add(btnHapusAkun);

        pengaturanPanel.add(cmbStrategiHarga);
        pengaturanPanel.add(txtBonusPersen);
        pengaturanPanel.add(btnSimpanStrategi);
        pengaturanPanel.add(lblStatusAdmin);
        pengaturanPanel.add(panelAksiAkun);

        txtRiwayat = new JTextArea();
        txtRiwayat.setEditable(false);
        JScrollPane scrollRiwayat = new JScrollPane(txtRiwayat);
        scrollRiwayat.setBorder(BorderFactory.createTitledBorder("Semua Riwayat Transaksi"));

        centerPanel.add(pengaturanPanel);
        centerPanel.add(scrollRiwayat);
        add(centerPanel, BorderLayout.CENTER);
    }

    public void setInfoUser(String info) {
        lblInfoUser.setText(info);
    }

    public String getSelectedStrategi() {
        return (String) cmbStrategiHarga.getSelectedItem();
    }

    public String getBonusPersenInput() {
        return txtBonusPersen.getText();
    }

    public void setStatusAdmin(String pesan) {
        lblStatusAdmin.setText(pesan);
    }

    public JButton getBtnSimpanStrategi() {
        return btnSimpanStrategi;
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

    public JComboBox<String> getCmbStrategiHarga() {
        return cmbStrategiHarga;
    }

    public JTextField getTxtBonusPersen() {
        return txtBonusPersen;
    }
}