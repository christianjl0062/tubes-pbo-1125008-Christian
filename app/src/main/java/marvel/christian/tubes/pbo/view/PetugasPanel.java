package marvel.christian.tubes.pbo.view;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class PetugasPanel extends JPanel {
    public PetugasPanel() {
        JPanel panel = new JPanel(new GridLayout(4, 1, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(30, 50, 30, 50));
        panel.add(new JLabel("Silahkan Anda Login Dulu.", SwingConstants.CENTER));
        
        txtUsername = new JTextField();
        txtUsername.setBorder(BorderFactory.createTitledBorder("Username"));
        panel.add(txtUsername);

        txtPassword = new JPasswordField();
        txtPassword.setBorder(BorderFactory.createTitledBorder("Password"));
        panel.add(txtPassword);

        btnLogin = new JButton("Masuk Ke App Bank Sampah");
        panel.add(btnLogin);

        return panel;
    }

    public JButton getBtnLogin() {
        return btnLogin;
    }

    public String getUsernameInput() {
        return txtUsername.getText();
    }

    public void showCard(String cardName) {
        cardLayout.show(mainContainer, cardName);
    }
}
