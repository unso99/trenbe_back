package com.myShop.cart;

import java.util.List;

public interface CartDao {
    public int insert(CartDto dto);
    public List<CartDto> getCarts(CartDto dto);
}
