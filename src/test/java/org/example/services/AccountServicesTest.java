package org.example.services;

import org.example.dao.AccountDao;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

public class AccountServicesTest {

    @Mock
    private AccountDao accountDao;

    @InjectMocks
    private AccountServiceImpl accountService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testPrintInfo() {

    }

    /*@Test
    void testPrintAllInfo() {
        accountService.printAllInfo();
        verify(accountDao).getAllInfoAccounts();
    }

    @Test
    void testPrintInfo() {
        accountService.printInfo();
        verify(accountDao).getAccounts();
    }

    /*@Test
    void testAddAccount() {
        accountService.addAccount("Test1", "Test2", "Test@email.com", "1234");
        verify(accountDao).addAccount(any(Account.class));
    }*/

    /*@Disabled("?")
    @Test
    void testAddEmailAccount() {
        accountService.addAccount("Test1", "Test2", "Testemail.com", "1234");
        when().thenReturn("Ошибка: Email без @! Аккаунт не был добавлен")
    }//

    @Test
    void testDeleteAccount() {
        //accountService.addAccount("Test1", "Test2", "Test@email.com", "1234");
        accountService.deleteAccount(1);
        verify(accountDao).deleteAccount(1);
    }

    @Test
    void testDeleteAllAccount() {
        accountService.deleteAccount();
        verify(accountDao).deleteAccount();
    }

    @Test
    void testUpdatePasswordAccount() {
        accountService.updatePasswordAccount(1, "4321");
        verify(accountDao).updatePasswordAccount(1, "4321");
    }

    @Test
    void testPrintSortASCName() {
        accountService.printSortASCName();
        verify(accountDao).getSortASCNameAccounts();
    }

    @Test
    void testPrintSortDESCName() {
        accountService.printSortDESCName();
        verify(accountDao).getSortDESCNameAccounts();
    }*/

}
