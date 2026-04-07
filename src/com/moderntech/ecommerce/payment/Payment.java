package com.moderntech.ecommerce.payment;

import com.moderntech.ecommerce.models.Order;

public interface Payment {
    PaymentStatus pay(Order order, PaymentMethod method);
}