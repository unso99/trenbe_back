package com.myShop.product;

import java.util.List;

public interface ProductDao {
    public int insert(ProductDto dto);
    public ProductDto getProduct(long id);
    public List<ProductDto> getList(ProductDto dto);

    public List<ProductDto> search(String keyword);
}
