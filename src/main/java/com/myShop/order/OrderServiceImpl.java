package com.myShop.order;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService{
    private final OrderDao dao;

    @Override
    public long getOrderId() {
        return dao.getOrderId();
    }

    @Override
    public boolean insert(OrderDto dto) {
        int result = dao.insert(dto);
        return result == 1;
    }
}
