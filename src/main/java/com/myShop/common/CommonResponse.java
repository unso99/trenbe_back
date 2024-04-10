package com.myShop.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CommonResponse {
    private CommonDto dto;
    private List<CommonDto> list;
    private HttpStatus status;
}
