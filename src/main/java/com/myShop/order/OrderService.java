package com.myShop.order;

public interface OrderService {
    public long getOrderId();
    public boolean insert(OrderDto dto);
}
