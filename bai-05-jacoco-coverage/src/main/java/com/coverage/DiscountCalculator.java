package com.coverage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DiscountCalculator {
    private static final Logger logger = LoggerFactory.getLogger(DiscountCalculator.class);

    public double calculateDiscount(double amount, String customerTier) {
        logger.info("Calculating discount: amount={}, tier={}", amount, customerTier);

        if (amount < 0) {
            logger.error("Amount is negative: {}", amount);
            throw new IllegalArgumentException("Amount cannot be negative");
        }
        if (customerTier == null) {
            logger.error("Customer tier is null");
            throw new IllegalArgumentException("Customer tier cannot be null");
        }

        double rate;
        switch (customerTier.toUpperCase()) {
            case "VIP":
                rate = amount >= 1000 ? 0.20 : 0.15;
                break;
            case "GOLD":
                rate = amount >= 500 ? 0.10 : 0.08;
                break;
            case "SILVER":
                rate = 0.05;
                break;
            case "REGULAR":
                rate = 0.0;
                break;
            default:
                logger.warn("Unknown customer tier: {}", customerTier);
                throw new IllegalArgumentException("Unknown customer tier: " + customerTier);
        }

        double discount = amount * rate;
        logger.info("Calculated discount: {} (rate={}%)", discount, rate * 100);
        return discount;
    }
}
