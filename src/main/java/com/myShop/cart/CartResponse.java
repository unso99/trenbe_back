package com.myShop.cart;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CartResponse {
    private CartDto dto;
    private List<CartDto> list;
    private HttpStatus status;
}
