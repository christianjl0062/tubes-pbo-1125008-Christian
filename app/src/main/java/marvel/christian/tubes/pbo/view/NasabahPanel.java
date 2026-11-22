package marvel.christian.tubes.pbo.view;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class NasabahPanel extends JPanel {
    private JLabel lblInfoUser;
    private JLabel lblSaldo;
    private JLabel lblStatusNasabah;
    private JTextField txtJumlahTukar;
    private JButton btnTukarUang;
    private JButton btnLogout;
    private JTextArea txtRiwayat;

    public NasabahPanel() {
        JFrame frame = new JFrame(appName);

        JPanel panel = new JPanel();
        panel.add(new JButton("Click Me"));
        frame.add(panel);

        frame.setSize(width, height);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    public String getInfoUser() {
        return lblInfoUser.getText();
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
