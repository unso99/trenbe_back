package com.myShop.product;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService service;

    @PostMapping("/get-list")
    public ResponseEntity<ProductResponse> getList(@RequestBody ProductDto dto) {
        ProductResponse response = new ProductResponse();
        try {
            List<ProductDto> list = service.getList(dto);
            list.forEach(item -> {
                log.info("product -> {}", item);
            });
            response.setList(list);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            response.setError(e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/post-product")
    public ResponseEntity<ProductResponse> productAdd(@RequestBody ProductDto dto) {
        boolean isSuccess;
        ProductResponse response = new ProductResponse();
        try {
            isSuccess = service.insert(dto);
            if (isSuccess) {
                log.info("product -> {}", dto);
                response.setDto(dto);
            } else {
                log.error("product -> {}", new ProductDto());
                response.setDto(new ProductDto());
            }
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            response.setError(e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/get-product")
    public ResponseEntity<ProductResponse> getProduct(@RequestBody long id) {
        ProductResponse response = new ProductResponse();
        try {
            ProductDto dto = service.getProduct(id);
            log.info("product -> {}", dto);
            response.setDto(dto);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            response.setError(e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
