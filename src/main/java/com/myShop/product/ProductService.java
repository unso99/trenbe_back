package com.myShop.product;

import java.util.List;

public interface ProductService {
    public boolean insert(ProductDto dto);
    public ProductDto getRecentProduct();
    public List<ProductDto> getList();
}
