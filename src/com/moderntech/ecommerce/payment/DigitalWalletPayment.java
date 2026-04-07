package com.moderntech.ecommerce.payment;

public final class DigitalWalletPayment implements PaymentMethod {

    private final String walletId;

    public DigitalWalletPayment(String walletId) {
        this.walletId = walletId;
    }

    public String walletId() {
        return walletId;
    }
}