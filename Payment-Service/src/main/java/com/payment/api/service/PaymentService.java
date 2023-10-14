package com.payment.api.service;

import com.payment.api.dto.PaymentDto;

public interface PaymentService {

    PaymentDto createPayment(PaymentDto paymentDto);

    PaymentDto findByOrderId(int orderId);

}
