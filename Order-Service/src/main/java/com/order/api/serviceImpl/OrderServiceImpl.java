package com.order.api.serviceImpl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.order.api.dto.OrderDto;
import com.order.api.dto.PaymentDto;
import com.order.api.dto.TransactionRequest;
import com.order.api.dto.TransactionResponse;
import com.order.api.entity.OrderEntity;
import com.order.api.repository.OrderRepository;
import com.order.api.service.OrderService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public TransactionResponse createOrder(TransactionRequest transactionRequest) {

        OrderDto orderDto = transactionRequest.getOrderDto();
        PaymentDto paymentDto = transactionRequest.getPaymentDto();
        paymentDto.setOrderAmount(orderDto.getOrderAmount());
        paymentDto.setPaymentStatus("success");
        paymentDto.setOrderId(orderDto.getOrderId());
        OrderEntity map = this.modelMapper.map(orderDto, OrderEntity.class);
        OrderEntity save = this.orderRepository.save(map);
        OrderDto map1 = this.modelMapper.map(save, OrderDto.class);

        paymentDto.setOrderAmount(save.getOrderAmount());
        paymentDto.setPaymentStatus("success");
        paymentDto.setOrderId(save.getOrderId());

        // rest Template to call the payment service....

        PaymentDto paymentDto1 = null;

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        // Create the HTTP request entity with the object as the request body and headers
        HttpEntity<PaymentDto> requestEntity = new HttpEntity<>(paymentDto, headers);

        // Send the POST request and receive the response
//        ResponseEntity<String> responseEntity = restTemplate.postForEntity(
//                "http://localhost:8082/payment/makePayment",
//                requestEntity,
//                String.class
//        );

        ResponseEntity<String> responseEntity = restTemplate.postForEntity(
                "http://PAYMENT-SERVICE/payment/makePayment",
                requestEntity,
                String.class
        );

        String responseBody = responseEntity.getBody();
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            paymentDto1 = objectMapper.readValue(responseBody, PaymentDto.class);

        }catch (Exception e){
            e.printStackTrace();
        }

        TransactionResponse build = TransactionResponse.builder().orderDto(map1).orderAmount(paymentDto1.getOrderAmount()).paymentStatus(paymentDto1.getPaymentStatus()).build();
        return build;
    }
}
