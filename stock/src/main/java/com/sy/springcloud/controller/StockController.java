package com.sy.springcloud.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 库存
 * @author sunyu
 */
@RestController
@RequestMapping("/stock")
public class StockController {

    @Value(value = "${server.port}")
    String port;

    /**
     * 减少库存接口
     * @return String
     */
    @PostMapping(value = "/reduceStock")
    public String reduceStock() {
        System.out.println("扣减库存");
        return "扣减库存"+port;
    }
    @PostMapping(value = "/reduceStock2")
    public String reduceStock2() {
        int num = 1 / 0;
        System.out.println("扣减库存");
        return "扣减库存"+port;
    }
}
