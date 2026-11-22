package marvel.christian.tubes.pbo.controller;

import marvel.christian.tubes.pbo.view.BankSampahView;

public class BankSampahController {
    private BankSampahView view;

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
            } else if (user.equals("nasabah")) {
                view.getLoginPanel().resetForm();
                view.showCard("NASABAH_CARD");
            } else if (user.equals("petugas")) {
                view.getLoginPanel().resetForm();
                view.showCard("PETUGAS_CARD");
            } else if (user.equals("admin")) {
                view.getLoginPanel().resetForm();
                view.showCard("ADMIN_CARD");
            } else {
                view.getLoginPanel().setStatusLogin("Akun tidak ditemukan!");
            }
        });
    }
}
