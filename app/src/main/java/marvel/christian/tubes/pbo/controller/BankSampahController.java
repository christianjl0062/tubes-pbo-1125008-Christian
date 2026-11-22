package marvel.christian.tubes.pbo.controller;

import marvel.christian.tubes.pbo.database.UserRepository;
import marvel.christian.tubes.pbo.models.HargaNormalStrategy;
import marvel.christian.tubes.pbo.models.Nasabah;
import marvel.christian.tubes.pbo.models.PricingStrategy;
import marvel.christian.tubes.pbo.models.User;
import marvel.christian.tubes.pbo.view.BankSampahView;

public class BankSampahController {
    private BankSampahView view;
    private PricingStrategy strategiHarga = new HargaNormalStrategy();
    private User currentUser;

    public BankSampahController(BankSampahView view) {
        this.view = view;
        initAppEvents();
    }

    public void initAppEvents() {
        view.getLoginPanel().getBtnLogin().addActionListener(e -> {
            String user = view.getLoginPanel().getUsernameInput();
            String userPassword = view.getLoginPanel().getPasswordInput();

            if (user.isEmpty() || userPassword.isEmpty()) {
                view.getLoginPanel().setStatusLogin("Username dan password gak boleh kosong!");
            } else {
                User userLogin = UserRepository.login(user, userPassword);
                
                if (userLogin == null) {
                    view.getLoginPanel().setStatusLogin("Username atau password salah!");
                } else {
                    this.currentUser = userLogin;
                    view.getLoginPanel().resetForm();

                    if (userLogin.getRole().equalsIgnoreCase("NASABAH")) {
                        Nasabah n = (Nasabah) userLogin;
                        view.getNasabahPanel().setSaldoText(n.getSaldo());
                        view.showCard("NASABAH_CARD");
                    } else if (userLogin.getRole().equalsIgnoreCase("PETUGAS")) {
                        view.showCard("PETUGAS_CARD");
                    } else if (userLogin.getRole().equalsIgnoreCase("ADMIN")) {
                        view.showCard("ADMIN_CARD");
                    }
                }
            }
        });
    }
}
