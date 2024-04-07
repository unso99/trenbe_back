package com.myShop.member;

public interface MemberService {
    public MemberDto getMember(MemberDto dto);
    public boolean insert(MemberDto dto);
}
