package org.example.dao;

import org.example.model.User;

import java.sql.*;
import java.util.ArrayList;

public class UsersAccDao {
    private final String url = "jdbc:postgresql://localhost:5432/Account Management";
    private final String user = "postgres";
    private final String password = "1512BDS7425";

    public void addUser(User user) {
        String sql = "INSERT INTO users(login, password) " +
                "VALUES(?, ?);";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, user.getLogin());
            pstmt.setString(2, user.getPassword());
            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<String> getLoginAndPassword() {
        ArrayList<String> output = new ArrayList<>();
        String sql = "SELECT login, password " +
                "FROM users;";
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                String login = rs.getString("login");
                String password = rs.getString("password");
                output.add(login + "/" + password);
           }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return output;
    }

    public ArrayList <String> getUserLogin() {
        ArrayList <String> userLogin = new ArrayList<>();
        String sql = "SELECT login " +
                "FROM users;";

        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                String login = rs.getString("login");
                userLogin.add(login);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userLogin;
    }

    public int getId(String login) {
        String sql = "SELECT id FROM users WHERE login = ?";

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, login);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("id");
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return 0;
    }


    protected Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }
}
