package com.moderntech.ecommerce.models;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCart {

    private final List<CartItem> items = new ArrayList<>();

    public void addItem(Product product, int quantity) {
        items.add(new CartItem(product, quantity));
    }

    public void removeItem(int productId) {
        items.removeIf(item -> item.product().id() == productId);
    }

    public List<CartItem> getItems() {
        return items;
    }

    public double getTotalWithVAT() {
        double total = 0.0;
        for (CartItem item : items) {
            total += item.product().price() * item.quantity();
        }
        return total * 1.20; // 20% VAT
    }

    public void printCart() {
        System.out.println("=== Лист покупок ===");
        for (CartItem item : items) {
            System.out.println(item.product().name() + " x" + item.quantity() +
                    " = " + (item.product().price() * item.quantity()));
        }
        System.out.println("Сумма с налогом: " + getTotalWithVAT());
    }
}