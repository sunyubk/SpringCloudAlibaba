package com.sy.springcloud.config.sentinel;

import com.alibaba.csp.sentinel.annotation.aspectj.SentinelResourceAspect;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * @ClassName SentinelConfig
 * @Description
 * @Author sunyu
 * @Date 2023/3/6 21:56
 * @Version 1.0
 **/
@Component
public class SentinelConfig {


    /**
     * @Description 配置启动Sentinel的注解
     * @Author sy
     * @Date 2023/3/6
     * @Version 1.0
     **/
    @Bean
    public SentinelResourceAspect sentinelResourceAspect() {
        return new SentinelResourceAspect();
    }
}
