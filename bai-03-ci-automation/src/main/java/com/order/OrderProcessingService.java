package com.order;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class OrderProcessingService {
    private static final Logger logger = LoggerFactory.getLogger(OrderProcessingService.class);

    public double calculateTotal(double subtotal, double discountPercent, double taxRate) {
        logger.info("Calculating order total: subtotal={}, discountPercent={}%, taxRate={}%",
                subtotal, discountPercent, taxRate);

        if (subtotal < 0) {
            logger.error("Invalid subtotal: {}", subtotal);
            throw new IllegalArgumentException("Subtotal cannot be negative");
        }
        if (discountPercent < 0 || discountPercent > 100) {
            logger.error("Invalid discount percent: {}", discountPercent);
            throw new IllegalArgumentException("Discount percent must be between 0 and 100");
        }
        if (taxRate < 0) {
            logger.error("Invalid tax rate: {}", taxRate);
            throw new IllegalArgumentException("Tax rate cannot be negative");
        }

        double discountedAmount = subtotal * (1 - discountPercent / 100.0);
        double taxAmount = discountedAmount * (taxRate / 100.0);
        double finalTotal = discountedAmount + taxAmount;

        logger.info("Order total calculation result: discountedAmount={}, taxAmount={}, finalTotal={}",
                discountedAmount, taxAmount, finalTotal);
        return finalTotal;
    }
}
