package com.myShop.member;

import com.myShop.jwt.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
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

    @Autowired
    private AuthenticationManager authManager;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/login")
    public String login(@RequestBody MemberDto dto) throws Exception {
        try {
            UsernamePasswordAuthenticationToken authToken =
                    new UsernamePasswordAuthenticationToken(dto.getEmail(), dto.getPassword());
            authManager.authenticate(authToken);
        } catch (Exception e) {
            throw new Exception("아이디 혹은 비밀번호가 틀려요.");
        }

        //예외가 발생하지 않았다면 인증을 통과한 것 토큰을 발급해서 응답
        return jwtUtil.generateToken(dto.getEmail());
    }

    @PostMapping("/post-member")
    public ResponseEntity<MemberResponse> memberAdd(@RequestBody MemberDto dto) {
        boolean isSuccess;
        MemberResponse response = new MemberResponse();
        try {
            isSuccess = service.insert(dto);
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
