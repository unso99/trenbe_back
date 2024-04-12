package com.myShop.cart;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/cart")
@RequiredArgsConstructor
public class CartController {
    private final CartService service;

    @PostMapping("/delete-cart")
    public ResponseEntity<CartResponse> deleteCart(@RequestBody CartDto dto) {
        boolean isSuccess;
        CartResponse response = new CartResponse();
        try {
            isSuccess = service.delete(dto);
            log.info("cart -> {}", dto);
            List<CartDto> list = service.getCarts(dto);
            if (isSuccess) {
                response.setDto(dto);
                response.setList(list);
            }
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            response.setError(e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/post-cart")
    public ResponseEntity<CartResponse> cartAdd(@RequestBody CartDto dto) {
        boolean isSuccess;
        CartResponse response = new CartResponse();
        try {
            isSuccess = service.insert(dto);
            log.info("cart -> {}", dto);
            if (isSuccess) {
                response.setDto(dto);
            }
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            response.setError(e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/get-carts")
    public ResponseEntity<CartResponse> getCarts(@RequestBody CartDto dto) {
        CartResponse response = new CartResponse();
        try {
            List<CartDto> list = service.getCarts(dto);
            list.forEach(item -> {
                log.info("cart -> {}", item);
            });
            response.setList(list);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            response.setError(e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
