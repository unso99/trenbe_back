package com.myShop.product;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProductDaoImpl implements ProductDao{

    @Autowired
    private SqlSession session;
    @Override
    public int insert(ProductDto dto) {
        return session.insert("product.insert",dto);

    }
    @Override
    public ProductDto getProduct(long id) {
        return session.selectOne("product.getProduct",id);
    }

    @Override
    public List<ProductDto> getList(ProductDto dto) {
        return session.selectList("product.getList",dto);
    }
}
