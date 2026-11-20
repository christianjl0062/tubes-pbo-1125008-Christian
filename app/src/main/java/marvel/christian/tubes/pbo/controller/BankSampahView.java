package marvel.christian.tubes.pbo.controller;
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

    public BankSampahView() {
        frame = new JFrame("Bank Sampah Digital - ");
        cardLayout = new CardLayout();
        mainContainer = new JPanel(cardLayout);

        JPanel loginPanel = createLoginView();
        JPanel dummyDashboard = new JPanel();
        dummyDashboard.add(new JLabel("Selamat, Anda Berhasil Login!"));
        mainContainer.add(loginPanel, "LOGIN_CARD");
        mainContainer.add(dummyDashboard, "DASHBOARD_CARD");

        frame.add(mainContainer);
        frame.setSize(600, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public JPanel createLoginView() {
        JPanel panel = new JPanel(new GridLayout(4, 1, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(30, 50, 30, 50));
        panel.add(new JLabel("Silahkan Anda Login Dulu.", SwingConstants.CENTER));
        
        JTextField txtUsername = new JTextField();
        txtUsername.setBorder(BorderFactory.createTitledBorder("Username"));
        panel.add(txtUsername);

        JPasswordField txtPassword = new JPasswordField();
        txtPassword.setBorder(BorderFactory.createTitledBorder("Password"));
        panel.add(txtPassword);

        JButton btnLogin = new JButton("Masuk Ke App Bank Sampah");
        panel.add(btnLogin);

        return panel;
        
        // referensi buat bagiannya yg addAction: niruin sintaksnya dari cheatsheet GCR
        btnLogin.addActionListener(e -> {
            String user = txtUsername.getText();
            if (user.isEmpty()) {
                System.out.println("Username gak boleh kosong!");
            } else {
                cardLayout.show(mainContainer, "DASHBOARD_CARD");
            }
        });
    }
}
