package com.myShop.cart;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CartDaoImpl implements CartDao{
    @Autowired
    private SqlSession session;

    @Override
    public int insert(CartDto dto) {
        return session.insert("cart.insert",dto);
    }

    @Override
    public List<CartDto> getCarts(CartDto dto) {
        return session.selectList("cart.getCarts",dto);
    }
}
