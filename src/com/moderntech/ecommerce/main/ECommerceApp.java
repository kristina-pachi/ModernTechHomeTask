package com.moderntech.ecommerce.main;

public class ECommerceApp {
    public static void main(String[] args) {

        // 1. Создаём каталог товаров
        var catalog = new java.util.ArrayList<com.moderntech.ecommerce.models.Product>();

        catalog.add(new com.moderntech.ecommerce.models.Product(
                1, "iPhone 15", com.moderntech.ecommerce.enums.ProductCategory.SMARTPHONE, 1200.0, 10));

        catalog.add(new com.moderntech.ecommerce.models.Product(
                2, "MacBook Air", com.moderntech.ecommerce.enums.ProductCategory.LAPTOP, 1800.0, 5));

        catalog.add(new com.moderntech.ecommerce.models.Product(
                3, "iPad Pro", com.moderntech.ecommerce.enums.ProductCategory.TABLET, 900.0, 7));

        catalog.add(new com.moderntech.ecommerce.models.Product(
                4, "Sony A7 Camera", com.moderntech.ecommerce.enums.ProductCategory.CAMERA, 2500.0, 3));

        catalog.add(new com.moderntech.ecommerce.models.Product(
                5, "USB‑C Cable", com.moderntech.ecommerce.enums.ProductCategory.ACCESSORY, 20.0, 100));

        // Вывод каталога
        System.out.println("=== Каталог товаров ===");
        for (var p : catalog) {
            System.out.println(p.id() + ". " + p.name() + " — " + p.price() + " (" + p.stock() + " in stock)");
        }
        // 2. Создаём покупателя
        var customer = new com.moderntech.ecommerce.models.Customer(
                1,
                "Kristina",
                "kristina@example.com"
        );

        customer.printInfo();

        // 3. Создаём корзину
        var cart = new com.moderntech.ecommerce.models.ShoppingCart();

        // Добавляем товары в корзину
        cart.addItem(catalog.get(0), 1); // iPhone 15
        cart.addItem(catalog.get(4), 3); // USB‑C Cable x3
        cart.addItem(catalog.get(2), 1); // iPad Pro

        // Вывод корзины
        cart.printCart();

        // 4. Создаём заказ на основе корзины
        var order = new com.moderntech.ecommerce.models.Order(1, customer);

        // Переносим товары из корзины в заказ
        for (var item : cart.getItems()) {
            order.addItem(item.product(), item.quantity());
        }

        // Выводим заказ
        order.printOrder();

        // 5. Меняем статус заказа
        order.setStatus(com.moderntech.ecommerce.enums.OrderStatus.CONFIRMED);
        order.setStatus(com.moderntech.ecommerce.enums.OrderStatus.PROCESSING);
        order.setStatus(com.moderntech.ecommerce.enums.OrderStatus.SHIPPED);

        // 6. Оплата заказа

        System.out.println("\n=== Оплата через Ozon ===");
        var ozonPayment = new com.moderntech.ecommerce.payment.OzonPayment();

        // Пробуем оплатить картой
        var card = new com.moderntech.ecommerce.payment.CreditCardPayment("1234-5678-9999-0000", "Kristina");
        var ozonResult1 = ozonPayment.pay(order, card);
        System.out.println("Payment result: " + ozonResult1);

        // Пробуем оплатить электронным кошельком (Ozon не поддерживает)
        var wallet = new com.moderntech.ecommerce.payment.DigitalWalletPayment("WALLET-777");
        var ozonResult2 = ozonPayment.pay(order, wallet);
        System.out.println("Результат платежа: " + ozonResult2);

        // Пробуем оплатить наложенным платежом
        var cod = new com.moderntech.ecommerce.payment.CashOnDelivery("Kristina");
        var ozonResult3 = ozonPayment.pay(order, cod);
        System.out.println("Результат платежа: " + ozonResult3);


        // Wildberries
        System.out.println("\n=== Оплата через Wildberries ===");
        var wbPayment = new com.moderntech.ecommerce.payment.WildberriesPayment();

        // Электронный кошелёк — SUCCESS
        var wbResult1 = wbPayment.pay(order, wallet);
        System.out.println("Результат платежа: " + wbResult1);

        // Карта — PROCESSING
        var wbResult2 = wbPayment.pay(order, card);
        System.out.println("Результат платежа: " + wbResult2);

        // Наложенный платёж — FAILED
        var wbResult3 = wbPayment.pay(order, cod);
        System.out.println("Результат платежа: " + wbResult3);
    }
}