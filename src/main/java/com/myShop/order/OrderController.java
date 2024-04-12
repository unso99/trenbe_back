package com.myShop.order;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService service;

    @GetMapping("/get-order-id")
    public ResponseEntity<Long> getOrderId() {
        long result = -1;
        try {
            result = service.getOrderId();
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(result, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/post-order")
    public ResponseEntity<OrderResponse> addOrders(@RequestBody OrderDto dto) {
        boolean isSuccess;
        OrderResponse response = new OrderResponse();
        try {
            isSuccess = service.insert(dto);
            if (isSuccess) {
                log.info("orders -> {}", dto);
            } else {
                log.error("orders -> {}", dto);
            }
            response.setDto(dto);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            response.setError(e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
