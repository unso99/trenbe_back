package com.myShop.common;

import lombok.RequiredArgsConstructor;
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
@RequiredArgsConstructor
public class CommonController {
    private final CommonService service;

    @PostMapping("/get-common-list")
    private ResponseEntity<CommonResponse> getCommonList(@RequestBody CommonDto dto) {
        CommonResponse response = new CommonResponse();
        try {
            List<CommonDto> list = service.getCommonList(dto);
            list.forEach(item -> {
                log.info("common -> {}", item);
            });
            response.setList(list);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            response.setError(e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
