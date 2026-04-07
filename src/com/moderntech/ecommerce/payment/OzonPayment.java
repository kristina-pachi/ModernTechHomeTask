package com.moderntech.ecommerce.payment;

import com.moderntech.ecommerce.models.Order;

public class OzonPayment implements Payment {

    @Override
    public PaymentStatus pay(Order order, PaymentMethod method) {
        System.out.println("=== Ozon Платеж ===");
        System.out.println("Обработка платежа по заказу #" + order.getId());

        if (method instanceof CreditCardPayment card) {
            System.out.println("Оплата кредитной картой: " + card.cardNumber());
            return PaymentStatus.SUCCESS;

        } else if (method instanceof CashOnDelivery cod) {
            System.out.println("Оплата при доставке: " + cod.recipientName());
            return PaymentStatus.PROCESSING;

        } else if (method instanceof DigitalWalletPayment wallet) {
            System.out.println("Цифровой кошелек не поддерживается Ozon: " + wallet.walletId());
            return PaymentStatus.FAILED;
        }

        return PaymentStatus.FAILED;
    }
}