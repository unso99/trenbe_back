package com.myShop.order;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.type.Alias;

@Alias("orderDto")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderDto {
    private long id;
    private String member_id;
    private long total_price;
    private String address;
}
