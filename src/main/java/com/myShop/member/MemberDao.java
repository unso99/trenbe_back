package com.myShop.member;

import org.springframework.stereotype.Repository;

public interface MemberDao {
    public MemberDto getMember(String email);
    public int insert(MemberDto dto);
}
