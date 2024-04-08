package com.myShop.product;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.type.Alias;

@Alias("productDto")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProductDto {
    private long id;
    private String name;
    private long price;
    private String detail;
    private String category;
    private String imageUrl;
}
