package com.myShop.member;

import lombok.*;
import org.apache.ibatis.type.Alias;

@Alias("memberDto")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class MemberDto {
    private String email;
    private String password;
    private String name;
    private String phone;
    private String nickname;
    private int birth;
}
