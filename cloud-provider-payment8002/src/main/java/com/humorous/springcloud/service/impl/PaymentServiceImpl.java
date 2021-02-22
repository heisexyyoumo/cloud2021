package com.humorous.springcloud.service.impl;

import com.humorous.springcloud.entities.Payment;
import com.humorous.springcloud.service.PaymentService;
import com.humorous.springcloud.mapper.PaymentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    PaymentMapper paymentMapper;

    @Override
    public void save(Payment payment) {
        paymentMapper.save(payment);
    }

    @Override
    public Payment selectPaymentById(Long id) {
        return paymentMapper.selectPaymentById(id);
    }
}
