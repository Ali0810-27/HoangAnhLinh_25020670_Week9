package com.bank;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Transaction {
    private final String transactionId;
    private final String accountNumber;
    private final String type;
    private final BigDecimal amount;
    private final LocalDateTime timestamp;

    public Transaction(String transactionId, String accountNumber, String type, BigDecimal amount) {
        this.transactionId = transactionId;
        this.accountNumber = accountNumber;
        this.type = type;
        this.amount = amount;
        this.timestamp = LocalDateTime.now();
    }

    public String getTransactionId() {
        return transactionId;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getType() {
        return type;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }
}
