package com.humorous.springcloud.controller;

import com.humorous.springcloud.common.CommonResult;
import com.humorous.springcloud.entities.Payment;
import com.humorous.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.TimeUnit;

@RestController
@Slf4j
public class PaymentController {

    @Autowired
    PaymentService paymentService;
    @Value("${server.port}")
    private String serverPort;

    @PostMapping("/payment")
    public CommonResult<Payment> save(@RequestBody Payment payment) {
        paymentService.save(payment);
        return CommonResult.success();
    }

    @GetMapping("/payment/{id}")
    public CommonResult<Payment> selectPaymentById(@PathVariable("id") Long id) {
        log.info("serverPort: {}", serverPort);
        return CommonResult.success(paymentService.selectPaymentById(id));
    }

    @GetMapping("/payment/lb")
    public String getPaymentLB() {
        return serverPort;
    }

    @GetMapping("/payment/feign/timeout")
    public String paymentFeignTimeout() {
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return serverPort;
    }

}
