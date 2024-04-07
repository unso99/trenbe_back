package com.myShop.member;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class MemberDaoImpl implements MemberDao {

    @Autowired
    private SqlSession session;

    @Override
    public MemberDto getMember(String email) {
        return session.selectOne("member.getMember",email);
    }

    @Override
    public int insert(MemberDto dto) {
        return session.insert("member.insert", dto);
    }
}
