package com.myShop.cart;

import java.util.List;

public interface CartService {
    public boolean insert(CartDto dto);
    public List<CartDto> getCarts(CartDto dto);
}
