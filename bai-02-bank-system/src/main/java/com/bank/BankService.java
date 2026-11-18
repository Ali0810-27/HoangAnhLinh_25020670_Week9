package com.bank;

import java.math.BigDecimal;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BankService {
    private static final Logger LOGGER = LoggerFactory.getLogger(BankService.class);
    private final Map<String, Account> accounts = new ConcurrentHashMap<>();

    public Account openAccount(String accountNumber, String ownerName, BigDecimal initialBalance) {
        LOGGER.info("Opening account: number={}, owner={}, initialBalance={}",
                accountNumber, ownerName, initialBalance);
        if (accounts.containsKey(accountNumber)) {
            LOGGER.warn("Attempted to open duplicate account: {}", accountNumber);
            throw new IllegalArgumentException("Account already exists: " + accountNumber);
        }
        Account account = new Account(accountNumber, ownerName, initialBalance);
        accounts.put(accountNumber, account);
        LOGGER.debug("Account registered into memory cache with ID: {}", accountNumber);
        return account;
    }

    public Account findAccount(String accountNumber) {
        LOGGER.debug("Looking up account number: {}", accountNumber);
        Account account = accounts.get(accountNumber);
        if (account == null) {
            LOGGER.warn("Account not found: {}", accountNumber);
        }
        return account;
    }

    public Transaction deposit(String accountNumber, BigDecimal amount) {
        LOGGER.info("Processing deposit: account={}, amount={}", accountNumber, amount);
        if (amount == null || amount.compareTo(BigDecimal.ZERO) <= 0) {
            LOGGER.error("Invalid deposit amount rejected: {}", amount);
            throw new IllegalArgumentException("Deposit amount must be strictly positive");
        }
        Account account = findAccount(accountNumber);
        if (account == null) {
            LOGGER.error("Deposit failed: account {} does not exist", accountNumber);
            throw new IllegalArgumentException("Account does not exist: " + accountNumber);
        }
        account.setBalance(account.getBalance().add(amount));
        Transaction tx = new Transaction(UUID.randomUUID().toString(), accountNumber, "DEPOSIT", amount);
        LOGGER.info("Deposit successful: txId={}, account={}, newBalance={}",
                tx.getTransactionId(), accountNumber, account.getBalance());
        return tx;
    }

    public Transaction withdraw(String accountNumber, BigDecimal amount) {
        LOGGER.info("Processing withdrawal: account={}, amount={}", accountNumber, amount);
        if (amount == null || amount.compareTo(BigDecimal.ZERO) <= 0) {
            LOGGER.error("Invalid withdrawal amount rejected: {}", amount);
            throw new IllegalArgumentException("Withdrawal amount must be strictly positive");
        }
        Account account = findAccount(accountNumber);
        if (account == null) {
            LOGGER.error("Withdrawal failed: account {} not found", accountNumber);
            throw new IllegalArgumentException("Account not found: " + accountNumber);
        }
        if (account.getBalance().compareTo(amount) < 0) {
            LOGGER.warn("Insufficient funds: account={}, currentBalance={}, requested={}",
                    accountNumber, account.getBalance(), amount);
            throw new IllegalStateException("Insufficient funds in account: " + accountNumber);
        }
        account.setBalance(account.getBalance().subtract(amount));
        Transaction tx = new Transaction(UUID.randomUUID().toString(), accountNumber, "WITHDRAW", amount);
        LOGGER.info("Withdrawal completed: txId={}, account={}, remainingBalance={}",
                tx.getTransactionId(), accountNumber, account.getBalance());
        return tx;
    }
}
