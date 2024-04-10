package com.myShop.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommonServiceImpl implements CommonService{
    @Autowired
    private CommonDao dao;
    @Override
    public List<CommonDto> getCommonList(CommonDto dto) {
        return dao.getCommonList(dto);
    }
}
