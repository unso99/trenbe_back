package com.myShop.cart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartServiceImpl implements CartService{
    @Autowired
    private CartDao dao;
    @Override
    public boolean insert(CartDto dto) {
        int result = dao.insert(dto);
        return result == 1;
    }

    @Override
    public List<CartDto> getCarts(CartDto dto) {
        return dao.getCarts(dto);
    }
}
