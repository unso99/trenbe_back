package com.myShop.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class MemberDetailService implements UserDetailsService {

    @Autowired
    private MemberDao dao;

    //Sptring security가 로그인 처리시 호출하는 메소드
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        MemberDto dto = dao.getMember(email);
        //만일 저장된 email이 없다면
        if (dto == null) {
            System.out.println("사용자 정보 없음");
            //예외를 발생
            throw new UsernameNotFoundException("존재하지 않는 사용자입니다");
        }

        return new User(dto.getEmail(), dto.getPassword(), Collections.emptyList());
    }
}
