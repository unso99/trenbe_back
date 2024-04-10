package com.myShop.common;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CommonDaoImpl implements CommonDao{
    @Autowired
    private SqlSession session;

    @Override
    public List<CommonDto> getCommonList(CommonDto dto) {
        return session.selectList("common.getCommonList",dto);
    }
}
