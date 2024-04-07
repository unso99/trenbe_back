package com.myShop.member;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class MemberResponse {
    private MemberDto dto;
    private List<MemberDto> list;
    private String token;
    private HttpStatus status;

}
