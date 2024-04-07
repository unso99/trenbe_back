package com.myShop.member;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class MemberResponse {
    private MemberDto dto;
    private HttpStatus status;

}
