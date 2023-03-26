package com.sy.springcloud.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.sy.springcloud.service.openFeign.Stock;
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

    @Autowired
    Stock stockService;

    /**
     * 下单接口
     * @return String
     */
    @PostMapping("/add")
    @SentinelResource("/flow/add")
    public String add() {
        System.out.println("下单成功");
        //String msg = restTemplate.postForObject("http://stock/stock/reduceStock", null, String.class);
        String msg = stockService.reduceStock();
        return "Hello World:" + msg;

    }
    @PostMapping("/addSentinel")
    public String addSentinel() {
        System.out.println("下单成功");
        //String msg = restTemplate.postForObject("http://stock/stock/reduceStock", null, String.class);
        String msg = stockService.reduceStock2();
        return "Hello World:" + msg;

    }

}
