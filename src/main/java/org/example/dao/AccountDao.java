package org.example.dao;

import org.example.model.Account;

import java.sql.*;
import java.util.ArrayList;

public class AccountDao {
    private final String url = "jdbc:postgresql://localhost:5432/Account Management";
    private final String user = "postgres";
    private final String password = "1512BDS7425";

    public ArrayList<String> getAccounts(int userId) {
    ArrayList<String> accounts = new ArrayList<>();
    String sql = "SELECT name_service, login, password FROM accounts " +
            "WHERE user_id = ?;";
    try (Connection conn = getConnection();
         PreparedStatement pstmt = conn.prepareStatement(sql)) {
        pstmt.setInt(1, userId);
        ResultSet rs = pstmt.executeQuery();

        int count = 0;
        while (rs.next()) {
            String nameService = rs.getString("name_service");
            String login = rs.getString("login");
            String password = rs.getString("password");

            String output = ++count + ") Сервис: " + nameService + "\n" +
                    "Логин: " + login + "\nПароль: " + password + ".\n";
            accounts.add(output);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return accounts;
}

    public void addAccount(Account account, int userId) {
        String sql = "INSERT INTO accounts(user_id, name_service, login, password) " +
                "VALUES(?, ?, ?, ?);";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, userId);
            pstmt.setString(2, account.getNameService());
            pstmt.setString(3, account.getLogin());
            pstmt.setString(4, account.getPassword());
            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updatePasswordAccount(int id, int userId, String newPassword) {
        String sql = "UPDATE accounts " +
                "SET password = ? " +
                "WHERE id = ? AND user_id = ?;";

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, newPassword);
            pstmt.setInt(2, id);
            pstmt.setInt(3, userId);
            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteAccount(int userId,  int id) {
        String sql = "DELETE FROM accounts " +
                "WHERE user_id = ? AND id = ?;";

        try (Connection conn = getConnection()) {
            conn.setAutoCommit(false);
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setInt(1, userId);
                pstmt.setInt(2, id);

                pstmt.executeUpdate();
                conn.commit();
            } catch (SQLException e) {
                conn.rollback();
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteAllAccount(int userId) {
        String sql = "DELETE FROM accounts " +
                "WHERE user_id = ?;";

        try (Connection conn = getConnection()) {
            conn.setAutoCommit(false);

            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setInt(1, userId);

                pstmt.executeUpdate();
                conn.commit();
            } catch (SQLException e) {
                conn.rollback();
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<String> getAllInfoAccounts(int userId) {
        ArrayList<String> accounts = new ArrayList<>();
        String sql = "SELECT * FROM accounts " +
                "WHERE user_id = ?;";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, userId);
            ResultSet rs = pstmt.executeQuery();

            int count = 0;
            while (rs.next()) {
                int id = rs.getInt("id");
                String nameService = rs.getString("name_service");
                String login = rs.getString("login");
                String password = rs.getString("password");

                String output = ++count + ") id сервиса " + nameService + ": " + id;

                /*String output = ++count + ") пароль и id сервиса " + nameService + ":\n" +
                        "id: " + id + "\n" +
                        "пароль: " + password;*/
                accounts.add(output);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return accounts;
    }

    public ArrayList<String> getSortASCNameAccounts(int userId) {
        int count = 0;
        ArrayList<String> accounts = new ArrayList<>();
        String sql = "SELECT name_service, login, password FROM accounts " +
                "WHERE user_id = ? "+
                "ORDER BY name_service ASC;";

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, userId);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                String nameService = rs.getString("name_service");
                String login = rs.getString("login");
                String password = rs.getString("password");

                String output = ++count + ") Сервис: " + nameService + "\n" +
                        "Логин: " + login + "\nПароль: " + password + ".\n";
                accounts.add(output);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return accounts;
    }

    public ArrayList<String> getSortDESCNameAccounts(int userId) {
        int count = 0;
        ArrayList<String> accounts = new ArrayList<>();
        String sql = "SELECT name_service, login, password FROM accounts " +
                "WHERE user_id = ? "+
                "ORDER BY name_service DESC;";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, userId);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                String nameService = rs.getString("name_service");
                String login = rs.getString("login");
                String password = rs.getString("password");

                String output = ++count + ") Сервис: " + nameService + "\n" +
                        "Логин: " + login + "\nПароль: " + password + ".\n";
                accounts.add(output);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return accounts;
    }

    protected Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }
}
