package com.humorous.springcloud.service;

import com.humorous.springcloud.common.CommonResult;
import com.humorous.springcloud.entities.Payment;
import org.springframework.stereotype.Component;

@Component
public class PaymentFallbackService implements PaymentService {
    @Override
    public CommonResult<Payment> paymentSQL(Long id) {
        return new CommonResult<>(false, "444", "服务降级返回，---PaymentFallbackService",
                new Payment(id, "ErrorSerial"));
    }
}
