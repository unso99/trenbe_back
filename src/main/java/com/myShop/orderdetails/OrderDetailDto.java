package com.myShop.orderdetails;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.type.Alias;

@Alias("orderDetailDto")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderDetailDto {
    private long id;
    private long orders_id;
    private long product_id;
    private String created_at;
}
