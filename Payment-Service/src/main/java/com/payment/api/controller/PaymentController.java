package com.payment.api.controller;

import com.payment.api.dto.PaymentDto;
import com.payment.api.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/payment")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @PostMapping("/makePayment")
    public ResponseEntity<PaymentDto> makePayment(@RequestBody PaymentDto paymentDto){
        PaymentDto payment = this.paymentService.createPayment(paymentDto);
        return new ResponseEntity<>(payment, HttpStatus.CREATED);
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<PaymentDto> findByOrderId(@PathVariable("orderId") int orderId){
        PaymentDto byOrderId = this.paymentService.findByOrderId(orderId);
        return new ResponseEntity<>(byOrderId, HttpStatus.OK);
    }

}
