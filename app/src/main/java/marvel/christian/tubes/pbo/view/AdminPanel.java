package marvel.christian.tubes.pbo.view;

import javax.swing.JButton;
import javax.swing.JPanel;

public class AdminPanel extends JPanel {
    public AdminPanel {
        
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
