package marvel.christian.tubes.pbo.view;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.GridLayout;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import marvel.christian.tubes.pbo.models.DataObserver;

// bagian nya Christian.
public class BankSampahView implements DataObserver{
    private JFrame frame;
    private CardLayout cardLayout;
    private JPanel mainContainer;
    private JTextField txtUsername;
    private JPasswordField txtPassword;
    private JButton btnLogin;
    private JTextArea txtRiwayat;

    public BankSampahView() {
        frame = new JFrame("Bank Sampah Digital - ");
        cardLayout = new CardLayout();
        mainContainer = new JPanel(cardLayout);

        mainContainer.add(createLoginPanel(), "LOGIN_CARD");
        mainContainer.add(createDummyDashboard(), "DASHBOARD_CARD");

        frame.add(mainContainer);
        frame.setSize(650, 420);
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
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panel.add(new JLabel("Selamat, Anda Berhasil Login!"));

        txtRiwayat = new JTextArea();
        txtRiwayat.setEditable(false);
        panel.add(new JScrollPane(txtRiwayat), BorderLayout.CENTER);

        return panel;
    }

    @Override
    public void updateRiwayatData(List<String> daftarRiwayat) {
        if (txtRiwayat != null) {
            String teksRiwayat = "";
            for (int i = 0; i < daftarRiwayat.size(); i++) {
                teksRiwayat += "- " + daftarRiwayat.get(i) + "\n";
            }
            txtRiwayat.setText(teksRiwayat);
        }
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
