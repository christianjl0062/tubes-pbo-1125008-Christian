package marvel.christian.tubes.pbo.controller;

import marvel.christian.tubes.pbo.database.TransaksiRepository;
import marvel.christian.tubes.pbo.database.UserRepository;
import marvel.christian.tubes.pbo.exception.BankSampahException;
import marvel.christian.tubes.pbo.exception.SaldoTidakCukupException;
import marvel.christian.tubes.pbo.models.DataContainer;
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
    private PricingStrategy strategiHarga = new HargaNormalStrategy();
    private User currentUser;

    public BankSampahController(BankSampahView view) {
        this.view = view;
        eventLogin();
        eventNasabah();
        eventPetugas();
        eventAdmin();
        eventLogout();
        eventRegister();
        eventDeleteAkun();
    }

    public void eventLogin() {
        view.getLoginPanel().getBtnLogin().addActionListener(e -> {
            String user = view.getLoginPanel().getUsernameInput();
            String pass = view.getLoginPanel().getPasswordInput();

            if (user.isEmpty() || pass.isEmpty()) {
                view.getLoginPanel().setStatusLogin("Username dan password gak boleh kosong!");
            } else {
                User userLogin = UserRepository.login(user, pass);
                if (userLogin == null) {
                    view.getLoginPanel().setStatusLogin("Username atau password salah!");
                } else {
                    DataContainer<User> hasilLogin = new DataContainer<>(userLogin, "Login berhasil");
                    this.currentUser = hasilLogin.getIsiData();
                    view.getLoginPanel().resetForm();

                    if (userLogin.getRole().equalsIgnoreCase("NASABAH")) {
                        Nasabah n = (Nasabah) userLogin;
                        view.getNasabahPanel().setInfoUser("Dashboard Nasabah - " + n.getNamaLengkap());
                        view.getNasabahPanel().setSaldoText(n.getSaldo());
                        view.getNasabahPanel().setStatusNasabah(" ");
                        view.getNasabahPanel().clearJumlahTukar();
                        view.getNasabahPanel().setRiwayatText(String.join("\n", TransaksiRepository.getTransaksiByUsername(n.getUsername())));
                        view.showCard("NASABAH_CARD");
                    } else if (userLogin.getRole().equalsIgnoreCase("PETUGAS")) {
                        view.getPetugasPanel().setInfoUser("Dashboard Petugas - " + userLogin.getNamaLengkap());
                        view.getPetugasPanel().setStatusPetugas(" ");
                        view.getPetugasPanel().setInfoDaurUlang("");
                        view.getPetugasPanel().clearBeratKg();
                        view.updateRiwayatData(TransaksiRepository.getAllTransaksi());
                        view.showCard("PETUGAS_CARD");
                    } else {
                        view.getAdminPanel().setInfoUser("Dashboard Admin - " + userLogin.getNamaLengkap());
                        view.getAdminPanel().setStatusAdmin(" ");
                        view.updateRiwayatData(TransaksiRepository.getAllTransaksi());
                        view.showCard("ADMIN_CARD");
                    }
                }
            }
        });
    }

    private void eventRegister() {
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
            } else {
                if (UserRepository.isUsernameExists(username)) {
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
            }
        });
    }

    private void eventNasabah() {
        view.getNasabahPanel().getBtnTukarUang().addActionListener(e -> {
            if (currentUser instanceof Nasabah) {
                Nasabah nasabah = (Nasabah) currentUser;
                String inputTukar = view.getNasabahPanel().getJumlahTukarInput();

                if (inputTukar.isEmpty()) {
                    view.getNasabahPanel().setStatusNasabah("Nominal penukaran gak boleh kosong!");
                } else {
                    try {
                        double nominalTukar = Double.parseDouble(inputTukar);
                        if (nominalTukar <= 0) {
                            view.getNasabahPanel().setStatusNasabah("Nominal harus lebih dari 0!");
                        } else {
                            nasabah.tarikSaldo(nominalTukar);
                            UserRepository.updateSaldo(nasabah.getUsername(), nasabah.getSaldo());
                            TransaksiRepository.addTransaksi(nasabah.getUsername(), "TUKAR_SALDO", "Penarikan Tunai", nominalTukar);

                            view.getNasabahPanel().setSaldoText(nasabah.getSaldo());
                            view.getNasabahPanel().clearJumlahTukar();
                            view.getNasabahPanel().setStatusNasabah("Berhasil tukar uang Rp " + nominalTukar);
                            view.getNasabahPanel().setRiwayatText(String.join("\n", TransaksiRepository.getTransaksiByUsername(nasabah.getUsername())));
                        }
                    } catch (NumberFormatException ex) {
                        view.getNasabahPanel().setStatusNasabah("Nominal harus berupa angka!");
                    } catch (SaldoTidakCukupException ex) {
                        view.getNasabahPanel().setStatusNasabah(ex.getMessage());
                    }
                }
            }
        });
    }

    

    public void eventPetugas() {
        view.getPetugasPanel().getBtnSetorSampah().addActionListener(e -> {
            String jenisSampah = view.getPetugasPanel().getSelectedJenisSampah();
            String beratInput = view.getPetugasPanel().getBeratKgInput();

            if (beratInput.isEmpty()) {
                view.getPetugasPanel().setStatusPetugas("Berat sampah gak boleh kosong!");
            } else {
                try {
                    double beratKg = Double.parseDouble(beratInput);
                    if (beratKg <= 0) {
                        view.getPetugasPanel().setStatusPetugas("Berat sampah harus lebih dari 0 kg!");
                    } else {
                        SampahFactory factory = new SampahFactory();
                        Sampah sampah = factory.buatSampah(jenisSampah);

                        if (sampah instanceof Recycleable) {
                            view.getPetugasPanel().setInfoDaurUlang(((Recycleable) sampah).recycleSampah());
                        } else {
                            view.getPetugasPanel().setInfoDaurUlang(sampah.infoSampah());
                        }

                        double hargaAkhir = strategiHarga.calculateFinalPrice(sampah.hitungHarga(beratKg));
                        Nasabah nasabahTarget = UserRepository.getNasabahByUsername("tonob82");

                        if (nasabahTarget == null) {
                            view.getPetugasPanel().setStatusPetugas("Akun nasabah tujuan tidak ditemukan!");
                        } else {
                            UserRepository.updateSaldo(nasabahTarget.getUsername(), nasabahTarget.getSaldo() + hargaAkhir);
                            TransaksiRepository.addTransaksi(nasabahTarget.getUsername(), "SETOR_SAMPAH", jenisSampah + " (" + beratKg + " kg)", hargaAkhir);

                            view.getPetugasPanel().clearBeratKg();
                            view.getPetugasPanel().setStatusPetugas("Setoran berhasil! Total: Rp " + hargaAkhir);
                            view.updateRiwayatData(TransaksiRepository.getAllTransaksi());
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
            String pilihan = view.getAdminPanel().getSelectedStrategi();

            if ("Harga Bonus".equalsIgnoreCase(pilihan)) {
                String bonusStr = view.getAdminPanel().getBonusPersenInput();
                if (bonusStr.isEmpty()) {
                    view.getAdminPanel().setStatusAdmin("Persentase bonus tidak boleh kosong!");
                } else {
                    try {
                        double persenBonus = Double.parseDouble(bonusStr);
                        if (persenBonus < 0) {
                            view.getAdminPanel().setStatusAdmin("Persentase bonus tidak boleh negatif!");
                        } else {
                            this.strategiHarga = new HargaBonusStrategy(persenBonus);
                            view.getAdminPanel().setStatusAdmin("Strategi aktif: Harga Bonus (" + persenBonus + "%)");
                        }
                    } catch (NumberFormatException ex) {
                        view.getAdminPanel().setStatusAdmin("Persentase bonus harus berupa angka!");
                    }
                }
            } else {
                this.strategiHarga = new HargaNormalStrategy();
                view.getAdminPanel().setStatusAdmin("Strategi aktif: Harga Normal");
            }
        });
    }

    private void eventLogout() {
        view.getNasabahPanel().getBtnLogout().addActionListener(e -> {
            this.currentUser = null;
            view.getNasabahPanel().setStatusNasabah(" ");
            view.getNasabahPanel().getBtnHapusAkun().setText("Hapus Akun");
            view.showCard("LOGIN_CARD");
        });

        view.getPetugasPanel().getBtnLogout().addActionListener(e -> {
            this.currentUser = null;
            view.getPetugasPanel().setStatusPetugas(" ");
            view.getPetugasPanel().setInfoDaurUlang("");
            view.getPetugasPanel().getBtnHapusAkun().setText("Hapus Akun");
            view.showCard("LOGIN_CARD");
        });

        view.getAdminPanel().getBtnLogout().addActionListener(e -> {
            this.currentUser = null;
            view.getAdminPanel().setStatusAdmin(" ");
            view.getAdminPanel().getBtnHapusAkun().setText("Hapus Akun");
            view.showCard("LOGIN_CARD");
        });
    }

    public void eventDeleteAkun() {
        view.getNasabahPanel().getBtnHapusAkun().addActionListener(e -> {
            if (currentUser != null) {
                if (view.getNasabahPanel().getBtnHapusAkun().getText().equals("Hapus Akun")) {
                    view.getNasabahPanel().getBtnHapusAkun().setText("Yakin Hapus? Coba Pikirkan lagi.");
                    view.getNasabahPanel().setStatusNasabah("Klik 'Yakin Hapus?' untuk hapus akun anda.");
                } else {
                    String usernameDihapus = currentUser.getUsername();
                    boolean berhasil = UserRepository.deleteUser(usernameDihapus);
                    view.getNasabahPanel().getBtnHapusAkun().setText("Hapus Akun");
                    if (berhasil) {
                        this.currentUser = null;
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
