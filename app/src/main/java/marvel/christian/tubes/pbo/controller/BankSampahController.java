package marvel.christian.tubes.pbo.controller;

import marvel.christian.tubes.pbo.view.BankSampahView;

public class BankSampahController {
    private BankSampahView view;

    public BankSampahController(BankSampahView view) {
        this.view = view;
        initAppEvents();
    }

    public void initAppEvents() {
        view.getBtnLogin().addActionListener(e -> {
            String user = view.getUsernameInput();
            
            if (user.isEmpty()) {
                System.out.println("Username gak boleh kosong!");
            } else {
                view.showCard("DASHBOARD_CARD");
            }
        });
    }
}
