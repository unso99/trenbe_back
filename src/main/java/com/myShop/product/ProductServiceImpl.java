package com.myShop.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductDao dao;

    @Override
    public boolean insert(ProductDto dto) {
        int result = dao.insert(dto);
        return result == 1;
    }

    @Override
    public ProductDto getRecentProduct() {
        long id = dao.getSeq();
        return dao.getProduct(id);
    }

    @Override
    public List<ProductDto> getList() {
        return dao.getList();
    }
}
