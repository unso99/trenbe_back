package com.myShop.product;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ProductDaoImpl implements ProductDao{

    @Autowired
    private SqlSession session;
    @Override
    public int insert(ProductDto dto) {
        return session.insert("product.insert",dto);

    }

    @Override
    public long getSeq() {
        return session.selectOne("product.getSeq");
    }

    @Override
    public ProductDto getProduct(long id) {
        return session.selectOne("product.getProduct",id);
    }
}
