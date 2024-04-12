package com.myShop.order;

public interface OrderDao {
    public long getOrderId();
    public int insert(OrderDto dto);
}
