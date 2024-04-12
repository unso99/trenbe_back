package com.myShop.order;

public interface OrderDao {
    public int getOrderId();
    public int insert(OrderDto dto);
}
