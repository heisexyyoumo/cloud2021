package com.humorous.springcloud.service;

import com.humorous.springcloud.entities.Payment;

public interface PaymentService {

    void save(Payment payment);

    Payment selectPaymentById(Long id);
}
