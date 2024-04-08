package com.myShop.product;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.type.Alias;
import org.springframework.web.multipart.MultipartFile;

@Alias("productDto")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProductDto {
    private long id;
    private String name;
    private long price;
    private String brand;
    private String category;
    private String imageUrl;
}
