package com.moderntech.ecommerce.payment;

public final class CreditCardPayment implements PaymentMethod {

    private final String cardNumber;
    private final String holderName;

    public CreditCardPayment(String cardNumber, String holderName) {
        this.cardNumber = cardNumber;
        this.holderName = holderName;
    }

    public String cardNumber() {
        return cardNumber;
    }

    public String holderName() {
        return holderName;
    }
}