package marvel.christian.tubes.pbo.controller;

import java.util.ArrayList;
import java.util.List;

import marvel.christian.tubes.pbo.database.TransaksiRepository;
import marvel.christian.tubes.pbo.database.UserRepository;
import marvel.christian.tubes.pbo.exception.BankSampahException;
import marvel.christian.tubes.pbo.exception.SaldoTidakCukupException;
import marvel.christian.tubes.pbo.models.DataContainer;
import marvel.christian.tubes.pbo.models.DataObserver;
import marvel.christian.tubes.pbo.models.HargaBonusStrategy;
import marvel.christian.tubes.pbo.models.HargaNormalStrategy;
import marvel.christian.tubes.pbo.models.Nasabah;
import marvel.christian.tubes.pbo.models.PricingStrategy;
import marvel.christian.tubes.pbo.models.Recycleable;
import marvel.christian.tubes.pbo.models.Sampah;
import marvel.christian.tubes.pbo.models.SampahFactory;
import marvel.christian.tubes.pbo.models.User;
import marvel.christian.tubes.pbo.view.BankSampahView;

public class BankSampahController {
    private BankSampahView view;
    private User currentUser;
    private PricingStrategy currentStrategy;
    private List<DataObserver> observers;

    public BankSampahController(BankSampahView view) {
        this.view = view;
        this.currentStrategy = new HargaNormalStrategy();
        this.observers = new ArrayList<>();
        this.observers.add(view);
        eventLogin();
        eventRegister();
        eventNasabah();
        eventPetugas();
        eventAdmin();
        eventLogout();
        eventDeleteAkun();
    }

    public void notifyRiwayatObservers() {
        List<String> semuaRiwayat = TransaksiRepository.getAllTransaksi();
        for (int i = 0; i < observers.size(); i++) {
            observers.get(i).updateRiwayatData(semuaRiwayat);
        }
    }

    public void eventLogin() {
        view.getLoginPanel().getBtnLogin().addActionListener(e -> {
            String username = view.getLoginPanel().getUsernameInput().trim();
            String password = view.getLoginPanel().getPasswordInput();

            if (username.isEmpty() || password.isEmpty()) {
                view.getLoginPanel().setStatusLogin("Username dan password gak boleh kosong!");
            } else {
                User userLogin = UserRepository.login(username, password);
                if (userLogin != null) {
                    DataContainer<User> hasilLogin = new DataContainer<>(userLogin, "Login berhasil");
                    this.currentUser = hasilLogin.getIsiData();
                    view.getLoginPanel().resetForm();
                    notifyRiwayatObservers();

                    if (currentUser.getRole().equalsIgnoreCase("NASABAH")) {
                        Nasabah n = (Nasabah) currentUser;
                        view.getNasabahPanel().setInfoUser("Dashboard Nasabah - " + n.getNamaLengkap());
                        view.getNasabahPanel().setSaldoText(n.getSaldo());
                        view.getNasabahPanel().setStatusNasabah(" ");
                        view.getNasabahPanel().getBtnHapusAkun().setText("Hapus Akun");
                        view.showCard("NASABAH_CARD");
                    } else if (currentUser.getRole().equalsIgnoreCase("PETUGAS")) {
                        view.getPetugasPanel().setInfoUser("Dashboard Petugas - " + currentUser.getNamaLengkap());
                        view.getPetugasPanel().setStatusPetugas(" ");
                        view.getPetugasPanel().getBtnHapusAkun().setText("Hapus Akun");
                        view.showCard("PETUGAS_CARD");
                    } else if (currentUser.getRole().equalsIgnoreCase("ADMIN")) {
                        view.getAdminPanel().setInfoUser("Dashboard Admin - " + currentUser.getNamaLengkap());
                        view.getAdminPanel().getBtnHapusAkun().setText("Hapus Akun");
                        view.showCard("ADMIN_CARD");
                    }
                } else {
                    view.getLoginPanel().setStatusLogin("Username atau password salah!");
                }
            }
        });
    }

    public void eventRegister() {
        view.getLoginPanel().getBtnRegisterAkun().addActionListener(e -> {
            view.getLoginPanel().resetForm();
            view.getRegisterPanel().resetForm();
            view.showCard("REGISTER_CARD");
        });

        view.getRegisterPanel().getBtnKembaliLogin().addActionListener(e -> {
            view.getRegisterPanel().resetForm();
            view.getLoginPanel().resetForm();
            view.showCard("LOGIN_CARD");
        });

        view.getRegisterPanel().getBtnDaftar().addActionListener(e -> {
            String nama = view.getRegisterPanel().getNamaInput();
            String username = view.getRegisterPanel().getUsernameInput();
            String password = view.getRegisterPanel().getPasswordInput();
            String role = view.getRegisterPanel().getSelectedRole();

            if (nama.isEmpty() || username.isEmpty() || password.isEmpty()) {
                view.getRegisterPanel().setStatusRegister("Nama, username, dan password tidak boleh kosong!");
            } else if (username.contains(" ")) {
                view.getRegisterPanel().setStatusRegister("Username tidak boleh mengandung spasi!");
            } else if (UserRepository.isUsernameExists(username)) {
                view.getRegisterPanel().setStatusRegister("Username '" + username + "' sudah dipakai!");
            } else {
                boolean berhasil = UserRepository.register(username, password, nama, role);
                if (berhasil) {
                    view.getRegisterPanel().resetForm();
                    view.getLoginPanel().resetForm();
                    view.getLoginPanel().setStatusLogin("Akun '" + username + "' berhasil dibuat! Silakan login.");
                    view.showCard("LOGIN_CARD");
                } else {
                    view.getRegisterPanel().setStatusRegister("Gagal mendaftarkan akun ke database!");
                }
            }
        });
    }

    public void eventNasabah() {
        view.getNasabahPanel().getBtnTukarUang().addActionListener(e -> {
            view.getNasabahPanel().getBtnHapusAkun().setText("Hapus Akun");

            if (currentUser instanceof Nasabah) {
                Nasabah nasabah = (Nasabah) currentUser;
                String inputTeks = view.getNasabahPanel().getJumlahTukarInput().trim();

                if (inputTeks.isEmpty()) {
                    view.getNasabahPanel().setStatusNasabah("Nominal penukaran tidak boleh kosong!");
                } else {
                    try {
                        double nominal = Double.parseDouble(inputTeks);
                        if (nominal <= 0) {
                            view.getNasabahPanel().setStatusNasabah("Nominal harus lebih dari Rp 0!");
                        } else if (nominal > nasabah.getSaldo()) {
                            throw new SaldoTidakCukupException("Saldo tidak cukup! Saldo Anda: Rp " + nasabah.getSaldo());
                        } else {
                            nasabah.tukarSaldoKeUang(nominal);
                            UserRepository.updateSaldo(nasabah.getUsername(), nasabah.getSaldo());
                            TransaksiRepository.addTransaksi(
                                nasabah.getUsername(),
                                "TUKAR_SALDO",
                                "Penukaran Tunai",
                                nominal
                            );

                            view.getNasabahPanel().setSaldoText(nasabah.getSaldo());
                            view.getNasabahPanel().clearJumlahTukar();
                            view.getNasabahPanel().setStatusNasabah("Berhasil tukar Rp " + nominal + "!");
                            notifyRiwayatObservers();
                        }
                    } catch (NumberFormatException ex) {
                        view.getNasabahPanel().setStatusNasabah("Input nominal harus berupa angka!");
                    } catch (SaldoTidakCukupException ex) {
                        view.getNasabahPanel().setStatusNasabah(ex.getMessage());
                    }
                }
            }
        });
    }

    public void eventPetugas() {
        view.getPetugasPanel().getBtnSetorSampah().addActionListener(e -> {
            view.getPetugasPanel().getBtnHapusAkun().setText("Hapus Akun");

            String jenisSampah = view.getPetugasPanel().getSelectedJenisSampah();
            String inputBerat = view.getPetugasPanel().getBeratKgInput().trim();

            if (inputBerat.isEmpty()) {
                view.getPetugasPanel().setStatusPetugas("Berat sampah (Kg) tidak boleh kosong!");
            } else {
                try {
                    double beratKg = Double.parseDouble(inputBerat);
                    if (beratKg <= 0) {
                        view.getPetugasPanel().setStatusPetugas("Berat sampah harus lebih dari 0 Kg!");
                    } else {
                        List<Nasabah> daftarNasabah = UserRepository.getAllNasabah();
                        if (daftarNasabah.isEmpty()) {
                            view.getPetugasPanel().setStatusPetugas("Tidak ada akun Nasabah di database!");
                        } else {
                            Nasabah nasabahTarget = daftarNasabah.get(0);
                            SampahFactory factory = new SampahFactory();
                            Sampah sampahObj = factory.createSampah(jenisSampah);

                            double hargaDasar = sampahObj.hitungHarga(beratKg);
                            double totalNilai = currentStrategy.calculateFinalPrice(hargaDasar);
                            double saldoBaru = nasabahTarget.getSaldo() + totalNilai;

                            UserRepository.updateSaldo(nasabahTarget.getUsername(), saldoBaru);
                            TransaksiRepository.addTransaksi(
                                nasabahTarget.getUsername(),
                                "SETOR_SAMPAH",
                                jenisSampah + " (" + beratKg + " Kg)",
                                totalNilai
                            );

                            if (sampahObj instanceof Recycleable) {
                                Recycleable itemDaurUlang = (Recycleable) sampahObj;
                                view.getPetugasPanel().setInfoDaurUlang(itemDaurUlang.recycleSampah());
                            }

                            view.getPetugasPanel().clearBeratKg();
                            view.getPetugasPanel().setStatusPetugas(
                                "Setoran " + nasabahTarget.getUsername() + " sukses! +Rp " + totalNilai
                            );
                            notifyRiwayatObservers();
                        }
                    }
                } catch (NumberFormatException ex) {
                    view.getPetugasPanel().setStatusPetugas("Berat sampah harus berupa angka!");
                } catch (BankSampahException ex) {
                    view.getPetugasPanel().setStatusPetugas(ex.getMessage());
                }
            }
        });
    }

    public void eventAdmin() {
        view.getAdminPanel().getBtnSimpanStrategi().addActionListener(e -> {
            view.getAdminPanel().getBtnHapusAkun().setText("Hapus Akun");

            String pilihanStrategi = view.getAdminPanel().getSelectedStrategi();
            if ("Harga Normal".equals(pilihanStrategi)) {
                this.currentStrategy = new HargaNormalStrategy();
                view.getAdminPanel().setStatusAdmin("Strategi aktif: Harga Normal");
            } else {
                String inputBonus = view.getAdminPanel().getBonusPersenInput().trim();
                try {
                    double persenBonus = Double.parseDouble(inputBonus);
                    if (persenBonus < 0) {
                        view.getAdminPanel().setStatusAdmin("Persentase bonus tidak boleh negatif!");
                    } else {
                        this.currentStrategy = new HargaBonusStrategy(persenBonus);
                        view.getAdminPanel().setStatusAdmin("Strategi aktif: Harga Bonus (+" + persenBonus + "%)");
                    }
                } catch (NumberFormatException ex) {
                    view.getAdminPanel().setStatusAdmin("Persentase bonus harus berupa angka!");
                }
            }
        });
    }

    public void eventLogout() {
        view.getNasabahPanel().getBtnLogout().addActionListener(e -> {
            this.currentUser = null;
            view.getNasabahPanel().clearJumlahTukar();
            view.getNasabahPanel().setStatusNasabah(" ");
            view.getNasabahPanel().getBtnHapusAkun().setText("Hapus Akun");
            view.showCard("LOGIN_CARD");
        });

        view.getPetugasPanel().getBtnLogout().addActionListener(e -> {
            this.currentUser = null;
            view.getPetugasPanel().clearBeratKg();
            view.getPetugasPanel().setStatusPetugas(" ");
            view.getPetugasPanel().setInfoDaurUlang("");
            view.getPetugasPanel().getBtnHapusAkun().setText("Hapus Akun");
            view.showCard("LOGIN_CARD");
        });

        view.getAdminPanel().getBtnLogout().addActionListener(e -> {
            this.currentUser = null;
            view.getAdminPanel().getBtnHapusAkun().setText("Hapus Akun");
            view.showCard("LOGIN_CARD");
        });
    }

    public void eventDeleteAkun() {
        view.getNasabahPanel().getBtnHapusAkun().addActionListener(e -> {
            if (currentUser != null) {
                if (view.getNasabahPanel().getBtnHapusAkun().getText().equals("Hapus Akun")) {
                    view.getNasabahPanel().getBtnHapusAkun().setText("Yakin Hapus?");
                    view.getNasabahPanel().setStatusNasabah("Klik 'Yakin Hapus?' lagi untuk hapus akun!");
                } else {
                    String usernameDihapus = currentUser.getUsername();
                    boolean berhasil = UserRepository.deleteUser(usernameDihapus);
                    view.getNasabahPanel().getBtnHapusAkun().setText("Hapus Akun");
                    if (berhasil) {
                        this.currentUser = null;
                        view.getNasabahPanel().clearJumlahTukar();
                        view.getLoginPanel().resetForm();
                        view.getLoginPanel().setStatusLogin("Akun '" + usernameDihapus + "' berhasil dihapus.");
                        view.showCard("LOGIN_CARD");
                    } else {
                        view.getNasabahPanel().setStatusNasabah("Gagal menghapus akun dari database!");
                    }
                }
            }
        });

        view.getPetugasPanel().getBtnHapusAkun().addActionListener(e -> {
            if (currentUser != null) {
                if (view.getPetugasPanel().getBtnHapusAkun().getText().equals("Hapus Akun")) {
                    view.getPetugasPanel().getBtnHapusAkun().setText("Yakin Hapus?");
                    view.getPetugasPanel().setStatusPetugas("Klik 'Yakin Hapus?' lagi untuk hapus akun!");
                } else {
                    String usernameDihapus = currentUser.getUsername();
                    boolean berhasil = UserRepository.deleteUser(usernameDihapus);
                    view.getPetugasPanel().getBtnHapusAkun().setText("Hapus Akun");
                    if (berhasil) {
                        this.currentUser = null;
                        view.getPetugasPanel().clearBeratKg();
                        view.getLoginPanel().resetForm();
                        view.getLoginPanel().setStatusLogin("Akun '" + usernameDihapus + "' berhasil dihapus.");
                        view.showCard("LOGIN_CARD");
                    } else {
                        view.getPetugasPanel().setStatusPetugas("Gagal menghapus akun dari database!");
                    }
                }
            }
        });

        view.getAdminPanel().getBtnHapusAkun().addActionListener(e -> {
            if (currentUser != null) {
                if (view.getAdminPanel().getBtnHapusAkun().getText().equals("Hapus Akun")) {
                    view.getAdminPanel().getBtnHapusAkun().setText("Yakin Hapus?");
                    view.getAdminPanel().setStatusAdmin("Klik 'Yakin Hapus?' lagi untuk hapus akun!");
                } else {
                    String usernameDihapus = currentUser.getUsername();
                    boolean berhasil = UserRepository.deleteUser(usernameDihapus);
                    view.getAdminPanel().getBtnHapusAkun().setText("Hapus Akun");
                    if (berhasil) {
                        this.currentUser = null;
                        view.getLoginPanel().resetForm();
                        view.getLoginPanel().setStatusLogin("Akun '" + usernameDihapus + "' berhasil dihapus.");
                        view.showCard("LOGIN_CARD");
                    } else {
                        view.getAdminPanel().setStatusAdmin("Gagal menghapus akun dari database!");
                    }
                }
            }
        });
    }
}
