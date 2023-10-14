package com.order.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDto {

    private int orderId;
    private String orderName;
    private int orderQuantity;
    private int orderAmount;
}
