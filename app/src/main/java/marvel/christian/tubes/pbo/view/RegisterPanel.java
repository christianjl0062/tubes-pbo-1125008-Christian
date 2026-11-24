package marvel.christian.tubes.pbo.view;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class RegisterPanel extends JPanel {
    private JTextField txtNama;
    private JTextField txtUsername;
    private JPasswordField txtPassword;
    private JComboBox<String> comboRole;
    private JLabel labelStatusRegister;
    private JButton btnDaftar;
    private JButton btnKembaliLogin;

    public RegisterPanel() {
        setLayout(new GridLayout(5, 1, 10, 10));
        setBorder(BorderFactory.createEmptyBorder(25, 50, 25, 50));
        add(new JLabel("Pendaftaran Akun Baru", SwingConstants.CENTER));

        txtNama = new JTextField();
        txtNama.setBorder(BorderFactory.createTitledBorder("Nama Lengkap"));
        add(txtNama);
        txtUsername = new JTextField();
        txtUsername.setBorder(BorderFactory.createTitledBorder("Username Baru"));
        add(txtUsername);
        txtPassword = new JPasswordField();
        txtPassword.setBorder(BorderFactory.createTitledBorder("Password Baru"));
        add(txtPassword);

        String[] pilihanRole = {"NASABAH", "PETUGAS", "ADMIN"};
        comboRole = new JComboBox<>(pilihanRole);
        comboRole.setBorder(BorderFactory.createTitledBorder("Pilih Role Akun"));
        add(comboRole);

        labelStatusRegister = new JLabel(" ", SwingConstants.CENTER);
        add(labelStatusRegister);

        JPanel panelTombol = new JPanel(new GridLayout(1, 2, 10, 0));
        btnDaftar = new JButton("Daftar Sekarang");
        btnKembaliLogin = new JButton("Kembali ke Login");
        panelTombol.add(btnDaftar);
        panelTombol.add(btnKembaliLogin);
        add(panelTombol);

    }
    
    public String getNamaInput() {
        return txtNama.getText().trim();
    }

    public String getUsernameInput() {
        return txtUsername.getText().trim();
    }

    public String getPasswordInput() {
        return new String(txtPassword.getPassword());
    }

    public String getSelectedRole() {
        return (String) comboRole.getSelectedItem();
    }

    public JButton getBtnDaftar() {
        return btnDaftar;
    }

    public JButton getBtnKembaliLogin() {
        return btnKembaliLogin;
    }

    public void setStatusRegister(String pesan) {
        labelStatusRegister.setText(pesan);
    }

    public void resetForm() {
        txtNama.setText("");
        txtUsername.setText("");
        txtPassword.setText("");
        comboRole.setSelectedIndex(0);
        labelStatusRegister.setText(" ");
    }
}
