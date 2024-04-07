package com.myShop.member;

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
@RequestMapping("/member")
public class MemberController {

    @Autowired
    private MemberService service;

    @PostMapping("/post-member")
    public ResponseEntity<MemberResponse> memberAdd(@RequestBody MemberDto dto) {
        boolean isSuccess;
        MemberResponse response = new MemberResponse();
        try {
            isSuccess = service.insert(dto);
            System.out.println(isSuccess);
            MemberDto insertedDto = service.getMember(dto);
            if (isSuccess) {
                log.info("member = {}", insertedDto.toString());
                response.setDto(insertedDto);
                response.setStatus(HttpStatus.OK);
            } else {
                log.error("member = {}", insertedDto.toString());
                response.setDto(insertedDto);
                response.setStatus(HttpStatus.OK);
            }
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
