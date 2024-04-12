package com.myShop.order;

import lombok.RequiredArgsConstructor;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class OrderDaoImpl implements OrderDao{
    private final SqlSession session;
    @Override
    public int getOrderId() {
        return session.selectOne("orders.getOrderId");
    }

    @Override
    public int insert(OrderDto dto) {
        return session.insert("orders.insert",dto);
    }
}
