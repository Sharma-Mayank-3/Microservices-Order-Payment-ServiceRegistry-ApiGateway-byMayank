package com.payment.api.serviceImpl;

import com.payment.api.dto.PaymentDto;
import com.payment.api.entity.Payment;
import com.payment.api.repository.PaymentRepository;
import com.payment.api.service.PaymentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public PaymentDto createPayment(PaymentDto paymentDto) {

        Payment map = this.modelMapper.map(paymentDto, Payment.class);
        Payment save = this.paymentRepository.save(map);
        return this.modelMapper.map(save, PaymentDto.class);

    }

    @Override
    public PaymentDto findByOrderId(int orderId) {
        Payment byOrderId = this.paymentRepository.findByOrderId(orderId);
        return this.modelMapper.map(byOrderId, PaymentDto.class);
    }
}
