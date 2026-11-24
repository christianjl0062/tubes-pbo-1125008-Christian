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
        
        add(new JLabel("Silahkan Anda Login Dulu.", SwingConstants.CENTER));

        txtUsername = new JTextField();
        txtUsername.setBorder(BorderFactory.createTitledBorder("Username"));
        add(txtUsername);

        txtPassword = new JPasswordField();
        txtPassword.setBorder(BorderFactory.createTitledBorder("Password"));
        add(txtPassword);

        labelStatusLogin = new JLabel(" ", SwingConstants.CENTER);
        add(labelStatusLogin);

        btnLogin = new JButton("Masuk Ke App Bank Sampah");
        add(btnLogin);

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
        txtUsername.setText("");
        txtPassword.setText("");
        labelStatusLogin.setText(" ");
    }
}
