package org.example.dao;

import org.example.model.Account;

import java.sql.*;
import java.util.ArrayList;

public class AccountDao {
    private final String url = "jdbc:postgresql://localhost:5432/postgres";
    private final String user = "postgres";
    private final String password = "1512BDS7425";

    public ArrayList<String> getAccounts() {
        int count = 0;
        ArrayList<String> accounts = new ArrayList<>();
        String sql = "SELECT name_service, login, password " +
                "FROM accounts;";
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

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

    public void addAccount(Account account) {
        String sql = "INSERT INTO accounts(name_service, login, password) " +
                "VALUES(?, ?, ?);";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, account.getNameService());
            pstmt.setString(2, account.getLogin());
            pstmt.setString(3, account.getPassword());
            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updatePasswordAccount(int id, String newPassword) {
        String sql = "UPDATE accounts SET password = ? WHERE id = ?;";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, newPassword);
            pstmt.setInt(2, id);
            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteAccount(int id) {
        String sql = "DELETE FROM accounts WHERE id = ?;";

        try (Connection conn = getConnection()) {
            conn.setAutoCommit(false);

            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setInt(1, id);

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

    public void deleteAccount() {
        String sql = "DELETE FROM accounts;";

        try (Connection conn = getConnection()) {
            conn.setAutoCommit(false);

            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
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

    public ArrayList<String> getAllInfoAccounts() {
        int count = 0;
        ArrayList<String> accounts = new ArrayList<>();
        String sql = "SELECT * FROM accounts;";
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                int id = rs.getInt("id");
                String nameService = rs.getString("name_service");
                String login = rs.getString("login");
                String password = rs.getString("password");

                String output = ++count + ") id сервиса " + nameService + ": " + id;
                accounts.add(output);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return accounts;
    }

    public ArrayList<String> getSortASCNameAccounts() {
        int count = 0;
        ArrayList<String> accounts = new ArrayList<>();
        String sql = "SELECT name_service, login, password FROM accounts " +
                "ORDER BY name_service ASC;";
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

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

    public ArrayList<String> getSortDESCNameAccounts() {
        int count = 0;
        ArrayList<String> accounts = new ArrayList<>();
        String sql = "SELECT name_service, login, password FROM accounts " +
                "ORDER BY name_service DESC;";
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

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
