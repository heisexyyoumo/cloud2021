package com.humorous.springcloud.controller;


import com.humorous.springcloud.common.CommonResult;
import com.humorous.springcloud.entities.Payment;
import com.humorous.springcloud.lb.LoadBalancer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.List;

@RestController
@Slf4j
public class OrderController {

    @Autowired
    RestTemplate restTemplate;
    @Autowired
    private LoadBalancer loadBalancer;
    @Autowired
    private DiscoveryClient discoveryClient;

    public static final String PAYMENT_URL = "http://CLOUD-PAYMENT-SERVICE";

    @PostMapping("/customer/payment")
    public CommonResult<Payment> create(@RequestBody Payment payment) {
        restTemplate.postForEntity(PAYMENT_URL + "/payment", payment, CommonResult.class);
        return CommonResult.success();
    }

    @GetMapping("/customer/payment/{id}")
    public CommonResult<Payment> selectPaymentById(@PathVariable("id") Long id) {
        return restTemplate.getForObject(PAYMENT_URL + "/payment/" + id, CommonResult.class);
    }

    @GetMapping("/customer/payment/getForEntity/{id}")
    public CommonResult<Payment> selectPaymentById2(@PathVariable("id") Long id) {
        ResponseEntity<CommonResult> entity = restTemplate
                .getForEntity(PAYMENT_URL + "/payment/" + id, CommonResult.class);
        if (entity.getStatusCode().is2xxSuccessful()) {
            return entity.getBody();
        } else {
            return new CommonResult<>(false, "444", "操作失败");
        }
    }


    @GetMapping("/customer/payment/lb")
    public String getPaymentLB() {
        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        if (instances == null || instances.size() <= 0) {
            return null;
        }
        // 传入服务集群来计算出获取具体的服务实例
        ServiceInstance serviceInstance = loadBalancer.instances(instances);
        URI uri = serviceInstance.getUri();
        return restTemplate.getForObject(uri + "/payment/lb", String.class);
    }

    @GetMapping("/customer/payment/zipkin")
    public String paymentZipkin() {
        return restTemplate.getForObject( "http://localhost:8001/payment/zipkin/",String.class);
    }


}
