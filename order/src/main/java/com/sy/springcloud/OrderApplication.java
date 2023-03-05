package com.sy.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * 订单服务启动类
 * @author sunyu
 */
@SpringBootApplication
//@RibbonClients(value = {
//        @RibbonClient(name = "stock", configuration = RibbonRandomRuleConfig.class)
//})
@EnableFeignClients
public class OrderApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrderApplication.class, args);
    }


}
