package com.logging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PaymentProcessor {
    private static final Logger logger = LoggerFactory.getLogger(PaymentProcessor.class);

    public boolean processPayment(String transactionId, String customerId, double amount, String currency) {
        logger.info("Khoi dau giao dich thanh toan: txId={}, customerId={}, amount={}, currency={}",
                transactionId, customerId, amount, currency);

        if (transactionId == null || transactionId.isBlank()) {
            logger.error("Ma giao dich khong hop le (null hoac rong)");
            throw new IllegalArgumentException("Transaction ID cannot be null or blank");
        }
        if (customerId == null || customerId.isBlank()) {
            logger.error("Ma khach hang khong hop le cho txId={}", transactionId);
            throw new IllegalArgumentException("Customer ID cannot be null or blank");
        }
        if (amount <= 0) {
            logger.error("So tien thanh toan khong hop le: txId={}, amount={}", transactionId, amount);
            throw new IllegalArgumentException("Payment amount must be greater than zero");
        }

        if (amount > 50_000_000) {
            logger.warn("Phat hien giao dich gia tri cao vuot nguong: txId={}, amount={}. Yeu cau xac thuc OTP cap 2.",
                    transactionId, amount);
        }

        logger.debug("Dang ket noi cong thanh toan PaymentGateway cho txId={}", transactionId);
        logger.info("Giao dich hoan tat thanh cong: txId={}, customerId={}, soTienDaTru={}",
                transactionId, customerId, amount);
        return true;
    }
}
