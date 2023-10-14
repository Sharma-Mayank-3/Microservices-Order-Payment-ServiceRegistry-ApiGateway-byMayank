package com.order.api.service;

import com.order.api.dto.OrderDto;
import com.order.api.dto.TransactionRequest;
import com.order.api.dto.TransactionResponse;

import java.util.List;

public interface OrderService {

    TransactionResponse createOrder(TransactionRequest transactionRequest);

}
