package com.myShop.member;

import com.myShop.jwt.JwtUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
@RequiredArgsConstructor
public class MemberController {

    private final MemberService service;
    private final AuthenticationManager authManager;
    private final JwtUtil jwtUtil;

    @PostMapping("/get-member")
    public ResponseEntity<MemberResponse> getMember(@RequestBody MemberDto dto) {
        MemberResponse response = new MemberResponse();
        try{
            MemberDto member = service.getMember(dto);
            log.info("member -> {}", member);
            response.setDto(member);
            return new ResponseEntity<>(response,HttpStatus.OK);
        }catch (Exception e) {
            response.setError(e.getMessage());
            return new ResponseEntity<>(response,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<MemberResponse> login(@RequestBody MemberDto dto) throws Exception {
        MemberResponse response = new MemberResponse();
        try {
            UsernamePasswordAuthenticationToken authToken =
                    new UsernamePasswordAuthenticationToken(dto.getEmail(), dto.getPassword());
            authManager.authenticate(authToken);
        } catch (Exception e) {
            response.setError(e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);

        }
        response.setToken(jwtUtil.generateToken(dto.getEmail()));
        //예외가 발생하지 않았다면 인증을 통과한 것 토큰을 발급해서 응답
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/post-member")
    public ResponseEntity<MemberResponse> memberAdd(@RequestBody MemberDto dto) {
        boolean isSuccess;
        MemberResponse response = new MemberResponse();
        try {
            isSuccess = service.insert(dto);
            MemberDto insertedDto = service.getMember(dto);
            if (isSuccess) {
                log.info("member -> {}", insertedDto.toString());
            } else {
                log.error("member -> {}", insertedDto.toString());
            }
            response.setDto(insertedDto);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            response.setError(e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
