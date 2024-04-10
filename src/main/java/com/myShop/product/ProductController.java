package com.myShop.product;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService service;

    @PostMapping("/get-list")
    public ResponseEntity<ProductResponse> getList(@RequestBody ProductDto dto) {
        ProductResponse response = new ProductResponse();
        try {
            List<ProductDto> list = service.getList(dto);
            list.forEach(item ->{
                log.info("product = {}",item);
            });
            response.setList(list);
            response.setStatus(HttpStatus.OK);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
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
                log.info("product = {}", dto);
                response.setDto(dto);
            } else {
                log.error("product = {}", new ProductDto());
                response.setDto(new ProductDto());
            }
            response.setStatus(HttpStatus.OK);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/get-product")
    public ResponseEntity<ProductResponse> getProduct(@RequestBody long id) {
        ProductResponse response = new ProductResponse();
        try {
            ProductDto dto = service.getProduct(id);
            log.info("product = {}",dto);
            response.setDto(dto);
            response.setStatus(HttpStatus.OK);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
