package marvel.christian.tubes.pbo.view;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class LoginPanel extends JPanel {
    private JTextField txtUsername;
    private JPasswordField txtPassword;
    private JButton btnLogin;
    private JLabel labelStatusLogin;

    public LoginPanel() {
        setLayout(new GridLayout(5, 1, 10, 10));
        setBorder(BorderFactory.createEmptyBorder(25, 50, 25, 50));

        add(new JLabel("Selamat Datang di App EcoSetorSampah!"), SwingConstants.CENTER);
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
    public String getUsernameInput() {
        return txtUsername.getText();
    }

    public void setUsernameInput(String teksInfo) {
        txtUsername.setText(teksInfo);
    }

    public String getPasswordInput() {
        return new String(txtPassword.getPassword());
    }

    public void setPasswordInput(String teks) {
        txtPassword.setText(teks);
    }

    public JButton getBtnLogin() {
        return btnLogin;
    }

    public void setBtnLogin(JButton btnLogin) {
        this.btnLogin = btnLogin;
    }

    public JLabel getStatusLogin() {
        return labelStatusLogin;
    }

    public void setStatusLogin(String pesan) {
        labelStatusLogin.setText(pesan);
    }


    public void resetForm() {
        txtUsername.setText("");
        txtPassword.setText("");
        labelStatusLogin.setText(" ");
    }
}
