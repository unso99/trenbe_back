package com.myShop.product;

import java.util.List;

public interface ProductService {
    public boolean insert(ProductDto dto);
    public ProductDto getProduct(long id);
    public List<ProductDto> getList(ProductDto dto);
}
