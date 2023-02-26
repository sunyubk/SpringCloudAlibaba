package com.sy.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 订单服务启动类
 * @author sunyu
 */
@SpringBootApplication
//@RibbonClients(value = {
//        @RibbonClient(name = "stock", configuration = RibbonRandomRuleConfig.class)
//})
public class OrderApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrderApplication.class, args);
    }


}
