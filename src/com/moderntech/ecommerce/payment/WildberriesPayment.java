package com.moderntech.ecommerce.payment;

import com.moderntech.ecommerce.models.Order;

public class WildberriesPayment implements Payment {

    @Override
    public PaymentStatus pay(Order order, PaymentMethod method) {
        System.out.println("=== Wildberries Платеж ===");
        System.out.println("Обработка платежа по заказу #" + order.getId());

        if (method instanceof DigitalWalletPayment wallet) {
            System.out.println("Оплата с помощью цифрового кошелька: " + wallet.walletId());
            return PaymentStatus.SUCCESS;

        } else if (method instanceof CreditCardPayment card) {
            System.out.println("Оплата кредитной картой: " + card.cardNumber());
            return PaymentStatus.PROCESSING;

        } else if (method instanceof CashOnDelivery cod) {
            System.out.println("Оплата наличными при доставке не поддерживается Wildberries");
            return PaymentStatus.FAILED;
        }

        return PaymentStatus.FAILED;
    }
}