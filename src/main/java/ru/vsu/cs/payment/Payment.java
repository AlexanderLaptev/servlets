package ru.vsu.cs.payment;

import java.math.BigDecimal;

public class Payment {
    private int paymentId;
    private int bookingId;
    private BigDecimal amount;
    private PaymentStatus status;
    private PaymentMethod paymentMethod;
    private String transactionId;
    private long timestamp;
}
