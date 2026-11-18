package com.bank;

import java.math.BigDecimal;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class BankServiceTest {
    private BankService bankService;

    @BeforeEach
    void setUp() {
        bankService = new BankService();
    }

    @Test
    void testOpenAccountSuccess() {
        Account account = bankService.openAccount("ACC001", "Nguyen Van A", new BigDecimal("1000.00"));
        assertNotNull(account);
        assertEquals("ACC001", account.getAccountNumber());
        assertEquals(new BigDecimal("1000.00"), account.getBalance());
    }

    @Test
    void testDepositSuccess() {
        bankService.openAccount("ACC002", "Tran Thi B", new BigDecimal("500.00"));
        Transaction tx = bankService.deposit("ACC002", new BigDecimal("200.00"));

        assertNotNull(tx);
        Account account = bankService.findAccount("ACC002");
        assertEquals(new BigDecimal("700.00"), account.getBalance());
    }

    @Test
    void testWithdrawInsufficientBalance() {
        bankService.openAccount("ACC003", "Le Van C", new BigDecimal("100.00"));
        assertThrows(IllegalStateException.class, () -> {
            bankService.withdraw("ACC003", new BigDecimal("500.00"));
        });
    }

    @Test
    void testWithdrawNegativeAmount() {
        bankService.openAccount("ACC004", "Pham Van D", new BigDecimal("1000.00"));
        assertThrows(IllegalArgumentException.class, () -> {
            bankService.withdraw("ACC004", new BigDecimal("-50.00"));
        });
    }
}
