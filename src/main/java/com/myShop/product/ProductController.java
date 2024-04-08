package com.myShop.product;

import com.myShop.member.MemberDto;
import com.myShop.member.MemberResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService service;

    @PostMapping("/post-product")
    public ResponseEntity<ProductResponse> productAdd(@RequestBody ProductDto dto) {
        boolean isSuccess;
        ProductResponse response = new ProductResponse();
        try {
            isSuccess = service.insert(dto);
            ProductDto insertedDto = service.getRecentProduct();
            if (isSuccess) {
                log.info("product = {}", insertedDto.toString());
                response.setDto(insertedDto);
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

}
