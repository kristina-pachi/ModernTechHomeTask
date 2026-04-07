package com.moderntech.ecommerce.payment;

public final class CashOnDelivery implements PaymentMethod {

    private final String recipientName;

    public CashOnDelivery(String recipientName) {
        this.recipientName = recipientName;
    }

    public String recipientName() {
        return recipientName;
    }
}