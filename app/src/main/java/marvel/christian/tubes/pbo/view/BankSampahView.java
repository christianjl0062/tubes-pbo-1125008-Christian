package marvel.christian.tubes.pbo.view;
import java.awt.CardLayout;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

// bagian nya Christian.
public class BankSampahView {
    private JFrame frame;
    private CardLayout cardLayout;
    private JPanel mainContainer;
    private JTextField txtUsername;
    private JPasswordField txtPassword;
    private JButton btnLogin;

    public BankSampahView() {
        frame = new JFrame("Bank Sampah Digital - ");
        cardLayout = new CardLayout();
        mainContainer = new JPanel(cardLayout);

        mainContainer.add(createLoginPanel(), "LOGIN_CARD");
        mainContainer.add(createDummyDashboard(), "DASHBOARD_CARD");

        frame.add(mainContainer);
        frame.setSize(600, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public JPanel createLoginPanel() {
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
    
    public JPanel createDummyDashboard() {
        JPanel panel = new JPanel();
        panel.add(new JLabel("Selamat, Anda Berhasil Login!"));
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
