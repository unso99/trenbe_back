package com.myShop.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class MemberServiceImpl implements MemberService {

    @Autowired
    private MemberDao dao;

    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    @Override
    public MemberDto getMember(MemberDto dto) {
        return dao.getMember(dto.getEmail());
    }

    @Override
    public boolean insert(MemberDto dto) {
        dto.setPassword(encoder.encode(dto.getPassword()));
        int result = dao.insert(dto);
        System.out.println(result);

        return result == 1;
    }
}
