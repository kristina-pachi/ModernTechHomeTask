package com.moderntech.ecommerce.models;

import com.moderntech.ecommerce.enums.OrderStatus;

import java.util.ArrayList;
import java.util.List;

public class Order {

    private final int id;
    private final Customer customer;
    private final List<OrderItem> items = new ArrayList<>();
    private OrderStatus status = OrderStatus.PENDING;

    public Order(int id, Customer customer) {
        this.id = id;
        this.customer = customer;
    }

    public void addItem(Product product, int quantity) {
        double total = product.price() * quantity;
        items.add(new OrderItem(product, quantity, total));
    }

    public double getTotal() {
        return items.stream()
                .mapToDouble(OrderItem::lineTotal)
                .sum();
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
        System.out.println("Статус заказа изменился: " + status);
    }

    public void printOrder() {
        System.out.println("=== Заказ #" + id + " ===");
        for (OrderItem item : items) {
            System.out.println(item.product().name() + " x" + item.quantity() +
                    " = " + item.lineTotal());
        }
        System.out.println("Сумма: " + getTotal());
        System.out.println("Статус: " + status);
    }

    public int getId() {
        return id;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public List<OrderItem> getItems() {
        return items;
    }
}