package marvel.christian.tubes.pbo.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TransaksiRepository {
    private static final Connection conn;

    static {
        conn = Database.connect();
    }

    public static void addTransaksi(String usernameNasabah, String jenisAktivitas, String detailSampah, double nominal) {
        String sql = "INSERT INTO transaksi (username_nasabah, jenis_aktivitas, detail_sampah, nominal) VALUES (?, ?, ?, ?)";
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, usernameNasabah);
            preparedStatement.setString(2, jenisAktivitas);
            preparedStatement.setString(3, detailSampah);
            preparedStatement.setDouble(4, nominal);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static List<String> getAllTransaksi() {
        List<String> result = new ArrayList<>();
        String sql = "SELECT id, username_nasabah, jenis_aktivitas, detail_sampah, nominal, waktu FROM transaksi ORDER BY id DESC";
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet resultSet = stmt.executeQuery();

            while (resultSet.next()) {
                String baris = String.format(
                    "[%s] %s | %s - %s : Rp %.0f",
                    resultSet.getTimestamp("waktu").toString(),
                    resultSet.getString("username_nasabah"),
                    resultSet.getString("jenis_aktivitas"),
                    resultSet.getString("detail_sampah"),
                    resultSet.getDouble("nominal")
                );
                result.add(baris);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static List<String> getTransaksiByUsername(String usernameNasabah) {
        String sql = "INSERT INTO mahasiswa_matakuliah (nim, kode_matakuliah) VALUES (?, ?) ON CONFLICT DO NOTHING";
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, nim);
            stmt.setString(2, kodeMatkul);
            int rows = stmt.executeUpdate();
            if (rows > 0) {
                System.out.println("Mata kuliah berhasil diambil!");
            } else {
                System.out.println("MK sudah diambil, atau terjadi kesalahan.");
            }
        } catch (SQLException e) {
            System.out.println("Gagal mengambil mata kuliah. Pastikan NIM dan Kode Matkul valid.");
            e.printStackTrace();
        }
    }    
}
