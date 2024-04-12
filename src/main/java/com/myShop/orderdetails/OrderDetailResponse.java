package com.myShop.orderdetails;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderDetailResponse {
    private OrderDetailDto dto;
    private List<OrderDetailDto> list;
    private String error;
}
