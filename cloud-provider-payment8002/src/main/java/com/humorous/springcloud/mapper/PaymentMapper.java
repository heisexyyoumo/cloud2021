package com.humorous.springcloud.mapper;

import com.humorous.springcloud.entities.Payment;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PaymentMapper {

    void save(Payment payment);

    Payment selectPaymentById(Long id);
}
