package com.myShop.orderdetails;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderDetailServiceImpl implements OrderDetailService{
    private final OrderDetailDao dao;

    @Override
    public boolean insert(OrderDetailDto dto) {
        int result = dao.insert(dto);
        return result == 1;
    }
}
