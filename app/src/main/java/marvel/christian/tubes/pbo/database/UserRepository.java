package marvel.christian.tubes.pbo.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import marvel.christian.tubes.pbo.models.Admin;
import marvel.christian.tubes.pbo.models.Nasabah;
import marvel.christian.tubes.pbo.models.Petugas;
import marvel.christian.tubes.pbo.models.User;

public class UserRepository {
    private static final Connection conn;

    static {
        conn = Database.connect();
    }

    public static User login(String username, String password) {
        String sql = "SELECT username, password, nama, role, saldo FROM users WHERE username = ? AND password = ?";
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, username);
            stmt.setString(2, password);
            ResultSet resultSet = stmt.executeQuery();

            if (resultSet.next()) {
                String uname = resultSet.getString("username");
                String pwd = resultSet.getString("password");
                String nama = resultSet.getString("nama");
                String role = resultSet.getString("role");
                double saldo = resultSet.getDouble("saldo");

                if ("NASABAH".equalsIgnoreCase(role)) {
                    Nasabah n = new Nasabah(uname, uname, pwd, nama);
                    n.setSaldo(saldo);
                    return n;
                } else if ("PETUGAS".equalsIgnoreCase(role)) {
                    return new Petugas(uname, uname, pwd, nama);
                } else if ("ADMIN".equalsIgnoreCase(role)) {
                    return new Admin(uname, uname, pwd, nama);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Nasabah getNasabahByUsername(String username) {
        String sql = "SELECT username, password, nama, saldo FROM users WHERE username = ? AND role = 'NASABAH'";
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, username);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                String uname = resultSet.getString("username");
                Nasabah n = new Nasabah(
                    uname,
                    uname,
                    resultSet.getString("password"),
                    resultSet.getString("nama")
                );
                n.setSaldo(resultSet.getDouble("saldo"));
                return n;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static List<Nasabah> getAllNasabah() {
        List<Nasabah> result = new ArrayList<>();
        String sql = "SELECT username, password, nama, saldo FROM users WHERE role = 'NASABAH'";
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet resultSet = stmt.executeQuery();

            while (resultSet.next()) {
                String uname = resultSet.getString("username");
                Nasabah n = new Nasabah(
                    uname,
                    uname,
                    resultSet.getString("password"),
                    resultSet.getString("nama")
                );
                n.setSaldo(resultSet.getDouble("saldo"));
                result.add(n);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static void updateSaldo(String username, double saldoBaru) {
        String sql = "UPDATE users SET saldo = ? WHERE username = ?";
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setDouble(1, saldoBaru);
            preparedStatement.setString(2, username);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static boolean isUsernameExists(String username) {
        String sql = "SELECT username FROM users WHERE username = ?";
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, username);
            ResultSet resultSet = stmt.executeQuery();
            return resultSet.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean register(String username, String password, String nama, String role) {
        String sql = "INSERT INTO users (username, password, nama, role, saldo) VALUES (?, ?, ?, ?, 0.0)";
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, username);
            stmt.setString(2, password);
            stmt.setString(3, nama);
            stmt.setString(4, role.toUpperCase());
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean deleteUser(String username) {
        String sql = "DELETE FROM users WHERE username = ?";
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, username);
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}

