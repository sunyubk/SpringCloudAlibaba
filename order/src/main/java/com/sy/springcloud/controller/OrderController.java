package com.sy.springcloud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * 订单
 * @author sunyu
 */
@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    RestTemplate restTemplate;

    /**
     * 下单接口
     * @return String
     */
    @PostMapping("/add")
    public String add() {
        System.out.println("下单成功");
        String msg = restTemplate.postForObject("http://stock/stock/reduceStock", null, String.class);
        return "Hello World:" + msg;

    }

}
