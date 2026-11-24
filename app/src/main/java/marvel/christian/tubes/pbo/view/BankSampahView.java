package marvel.christian.tubes.pbo.view;
import java.awt.CardLayout;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;
import javax.swing.JTextField;

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
    private LoginPanel loginPanel;
    private NasabahPanel nasabahPanel;
    private PetugasPanel petugasPanel;
    private AdminPanel adminPanel;

    public BankSampahView() {
        frame = new JFrame("Bank Sampah Digital - EcoTrashBank");
        cardLayout = new CardLayout();
        mainContainer = new JPanel(cardLayout);

        loginPanel = new LoginPanel();
        nasabahPanel = new NasabahPanel();
        petugasPanel = new PetugasPanel();
        adminPanel = new AdminPanel();

        mainContainer.add(loginPanel, "LOGIN_CARD");
        mainContainer.add(nasabahPanel, "NASABAH_CARD");
        mainContainer.add(petugasPanel, "PETUGAS_CARD");
        mainContainer.add(adminPanel, "ADMIN_CARD");
        
        frame.add(mainContainer);
        frame.setSize(650, 420);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    @Override
    public void updateRiwayatData(List<String> daftarRiwayat) {
        if (daftarRiwayat != null) {
            String teksRiwayat = "";
            for (int i = 0; i < daftarRiwayat.size(); i++) {
                teksRiwayat += "- " + daftarRiwayat.get(i) + "\n";
            }
            nasabahPanel.setRiwayatText(teksRiwayat);
            petugasPanel.setRiwayatText(teksRiwayat);
            adminPanel.setRiwayatText(teksRiwayat);
        } else {
            return;
        }
    }

    public LoginPanel getLoginPanel() {
        return loginPanel;
    }

    public NasabahPanel getNasabahPanel() {
        return nasabahPanel;
    }

    public PetugasPanel getPetugasPanel() {
        return petugasPanel;
    }

    public AdminPanel getAdminPanel() {
        return adminPanel;
    }

    public void showCard(String cardName) {
        cardLayout.show(mainContainer, cardName);
    }
}
