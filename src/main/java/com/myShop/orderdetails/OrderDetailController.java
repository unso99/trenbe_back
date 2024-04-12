package com.myShop.orderdetails;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/orderDetail")
@RequiredArgsConstructor
public class OrderDetailController {
    private final OrderDetailService service;

    @PostMapping("/post-orderDetail")
    public ResponseEntity<OrderDetailResponse> addOrderDetail(@RequestBody OrderDetailDto dto) {
        boolean isSuccess;
        OrderDetailResponse response = new OrderDetailResponse();
        try {
            isSuccess = service.insert(dto);
            if (isSuccess) {
                log.info("orderDetail -> {}", dto);
            } else {
                log.error("orderDetail -> {}", dto);
            }
            response.setDto(dto);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            response.setError(e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
