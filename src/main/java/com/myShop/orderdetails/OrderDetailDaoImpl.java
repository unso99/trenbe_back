package com.myShop.orderdetails;

import lombok.RequiredArgsConstructor;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class OrderDetailDaoImpl implements OrderDetailDao{
    private final SqlSession session;
    @Override
    public int insert(OrderDetailDto dto) {
        return session.insert("orderDetails.insert",dto);
    }
}
