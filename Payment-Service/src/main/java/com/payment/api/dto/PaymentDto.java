package com.payment.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentDto {
    private int paymentId;
    private int orderId;
    private int orderAmount;

    private String paymentStatus;
}
