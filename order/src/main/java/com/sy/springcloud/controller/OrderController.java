package com.sy.springcloud.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.sy.springcloud.entity.Order;
import com.sy.springcloud.mapper.OrderMapper;
import com.sy.springcloud.service.openFeign.Stock;
import io.seata.spring.annotation.GlobalTransactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * 订单
 * @author sunyu
 */
@Slf4j
@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    private OrderMapper orderMapper;

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

    @GlobalTransactional()
    @GetMapping("/addOrder")
    public String addOrder() {
        Order order = new Order();
        order.setProid(11);
        order.setTotalMoney(1000);
        order.setStatus(0);
        Integer orderResult = orderMapper.insert(order);
        if (orderResult > 0) {
            log.info("----》订单插入成功");
        }
        String stockResult = stockService.rdStock(11, 1);
        // 模拟报错，实现事务回滚
        int i = 1 / 0;
        return stockResult;
    }
}
