package marvel.christian.tubes.pbo.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

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
}
