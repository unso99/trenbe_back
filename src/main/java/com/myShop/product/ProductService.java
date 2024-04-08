package com.myShop.product;

public interface ProductService {
    public boolean insert(ProductDto dto);
    public ProductDto getRecentProduct();
}
