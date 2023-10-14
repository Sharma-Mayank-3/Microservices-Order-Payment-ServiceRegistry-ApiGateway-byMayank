package com.order.api.controller;

import com.order.api.dto.TransactionRequest;
import com.order.api.dto.TransactionResponse;
import com.order.api.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/makeOrder")
    public ResponseEntity<TransactionResponse> createOrderPayment(@RequestBody TransactionRequest transactionRequest){
        TransactionResponse order = this.orderService.createOrder(transactionRequest);
        return new ResponseEntity<>(order, HttpStatus.OK);
    }

}
