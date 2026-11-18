package marvel.christian.tubes.pbo.controller;
import java.awt.CardLayout;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

// bagian nya Christian.
public class BankSampahController {
    private JFrame frame;
    private CardLayout cardLayout;
    private JPanel mainContainer;

    public BankSampahController() {
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

        JButton btnLogin = new JButton("Masuk Ke App Bank Sampah");
        panel.add(btnLogin);

        return panel;
    }
}
