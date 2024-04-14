package com.myShop.product;

import org.springframework.beans.factory.annotation.Autowired;
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
    public ProductDto getProduct(long id) {
        return dao.getProduct(id);

    }

    @Override
    public List<ProductDto> getList(ProductDto dto) {
        return dao.getList(dto);
    }

    @Override
    public List<ProductDto> search(String keyword) {
        return dao.search(keyword);
    }
}
