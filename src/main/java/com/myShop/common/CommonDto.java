package com.myShop.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.type.Alias;

@Alias("commonDto")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CommonDto {
    private int code_id;
    private int p_code_id;
    private String code_name;
    private String code_value;
}
