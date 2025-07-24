package org.example.dao;

import org.example.model.Account;
import org.junit.jupiter.api.*;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;

import java.sql.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class AccountDaoTest {
    @Container
    private static final PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:16")
            .withDatabaseName("testdb")
            .withUsername("test")
            .withPassword("test");

    private static AccountDao accountDao;

    @BeforeAll
    public static void setupDatabase() throws SQLException {
        postgres.start();

        accountDao = new AccountDao() {
            @Override
            protected Connection getConnection() throws SQLException {
                return DriverManager.getConnection(postgres.getJdbcUrl(), postgres.getUsername(), postgres.getPassword());
            }
        };

        try (Connection conn = accountDao.getConnection();
             Statement stmt = conn.createStatement()) {

            stmt.execute("CREATE TABLE accounts(\n" +
                    "\tid SERIAL PRIMARY KEY,\n" +
                    "\tname_account VARCHAR(140) NOT NULL,\n" +
                    "\tname_profil_account VARCHAR(140) NOT NULL,\n" +
                    "\temail VARCHAR(100) NOT NULL,\n" +
                    "\tpassword VARCHAR(30) NOT NULL\n" +
                    ");");
        }
        /*
        --CREATE TABLE accounts (
--	id SERIAL PRIMARY KEY,
	--user_id INT REFERENCES usersAcc(id) ON DELETE CASCADE,
--	name_service TEXT NOT NULL,
--	login TEXT NOT NULL,
--	password TEXT NOT NULL
--);

    }*/

    /*@Disabled("Пока не работает")
    @Test
    void testGetAccounts() {
        accountDao.addAccount(new Account("Test", "Test1", "Test@Email.com", "1234"));

        java.io.ByteArrayOutputStream out = new java.io.ByteArrayOutputStream();
        System.setOut(new java.io.PrintStream(out));

       accountDao.getAccounts();

       String output = out.toString();

       assertTrue(output.contains("Test"));

       System.setOut(System.out);
    }

    @Test
    void testAddAccount() {
        accountDao.addAccount(new Account("Test", "Test1", "Test@Email.com", "1234"));

        try (Connection conn = accountDao.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(
                     "SELECT * FROM accounts WHERE name_account = ?")) {
            pstmt.setString(1, "Test");
            ResultSet rs = pstmt.executeQuery();
            assertTrue(rs.next());
            assertEquals("Test", rs.getString("name_account"));

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void testUpdatePasswordAccount() throws SQLException {
        int id;
        try (Connection conn = accountDao.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(
                     "INSERT INTO accounts (name_account, name_profil_account, email, password) VALUES ('Test'" +
                             ", 'Test1', 'Test@Email.com', '1234')" +
                             " RETURNING id")) {
            ResultSet rs = pstmt.executeQuery();
            assertTrue(rs.next());
            id = rs.getInt("id");
        }

        accountDao.updatePasswordAccount(1, "4321");

        try (Connection conn = accountDao.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(
                     "SELECT password FROM accounts WHERE id = ?")) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            assertTrue(rs.next());
            assertEquals("4321", rs.getString("password"));
        }
    }

    @AfterAll
    public static void stop() {
        postgres.stop();
    }
}*/
