package com.myShop.cart;

import com.myShop.product.ProductDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.type.Alias;

@Alias("cartDto")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CartDto {
    private long id;
    private String member_id;
    private long product_id;
    private String createdAt;
    private String product_name;
    private long product_price;
    private String product_brand;
    private String product_imageUrl;
}
