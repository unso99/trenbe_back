package com.myShop.common;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/common")
public class CommonController {
    @Autowired
    private CommonService service;

    @PostMapping("/get-common-list")
    private ResponseEntity<CommonResponse> getCommonList(@RequestBody CommonDto dto) {
        CommonResponse response = new CommonResponse();
        try {
            List<CommonDto> list = service.getCommonList(dto);
            list.forEach(item -> {
                log.info("common -> {}", item);
            });
            response.setList(list);
            response.setStatus(HttpStatus.OK);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            log.error(e.getMessage());
            response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
