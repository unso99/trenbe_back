package com.myShop.order;

public interface OrderService {
    public int getOrderId();
    public boolean insert(OrderDto dto);
}
