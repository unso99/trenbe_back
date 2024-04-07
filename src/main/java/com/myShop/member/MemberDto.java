package com.myShop.member;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.type.Alias;

@Alias("memberDto")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class MemberDto {
    private String email;
    private String password;
    private String name;
    private String phone;
    private String nickname;
    private int birth;
}
