package com.humorous.springcloud.controller;

import com.humorous.springcloud.common.CommonResult;
import com.humorous.springcloud.entities.Payment;
import com.humorous.springcloud.service.PaymentFeignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderFeignController {

    @Autowired
    private PaymentFeignService paymentFeignService;

    @GetMapping("/customer/payment/{id}")
    public CommonResult<Payment> selectPaymentById(@PathVariable("id") Long id) {
        return paymentFeignService.selectPaymentById(id);
    }

    @GetMapping("/customer/payment/feign/timeout")
    public String paymentFeignTimeout() {
        return paymentFeignService.paymentFeignTimeout();
    }
}
