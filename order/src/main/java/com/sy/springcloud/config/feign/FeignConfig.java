package com.sy.springcloud.config.feign;

import feign.Contract;
import feign.Logger;
import feign.Request;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Description
 * OpenFeign配置类
 * 如果加了@Configuration注解则为全局配置，所有Feign接口都有效
 * 如果想要实现局部配置，则不需要配置@Configuration注解，在想要启用这个配置的Feign接口上面指定这个配置类
 * @Author sy
 * @Date 2023/3/5
 * @Version 1.0
 **/
@Configuration
public class FeignConfig {

    /**
     * @Description 日志级别
     * @Author sy
     * @Date 2023/3/5
     * @Version 1.0
     **/
    @Bean
    public Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }

    /**
     * @Description 契约模式，将OpenFeign注解还原成原生注解，基本不会使用到
     * @Author sy
     * @Date 2023/3/5
     * @Version 1.0
     **/
    @Bean
    public Contract feignContract() {
        return new Contract.Default();
    }

    /**
     * @Description 超时时间配置，第一个参数配置的是连接超时时间，第二个参数配置的是请求处理超时时间
     * @Author sy
     * @Date 2023/3/5
     * @Version 1.0
     **/
    @Bean
    public Request.Options options() {
        return new Request.Options(5000, 10000);
    }
}
