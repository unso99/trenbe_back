package com.myShop.product;

import java.util.List;

public interface ProductDao {
    public int insert(ProductDto dto);
    public long getSeq();
    public ProductDto getProduct(long id);
    public List<ProductDto> getList();
}
