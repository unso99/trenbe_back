package com.myShop.product;

public interface ProductDao {
    public int insert(ProductDto dto);
    public long getSeq();
    public ProductDto getProduct(long id);
}
