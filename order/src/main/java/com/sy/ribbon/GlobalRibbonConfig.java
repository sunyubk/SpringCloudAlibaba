package com.sy.ribbon;

import com.netflix.loadbalancer.IRule;
import com.sy.springcloud.config.ribbon.rule.TheSameClusterPriorityRule;
import com.sy.springcloud.config.ribbon.rule.TheSameClusterPriorityWithVersionRule;
import com.sy.springcloud.config.ribbon.rule.WeightedBalancerRule;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * @Description 全局负载均衡配
 * @Author sy
 * @Date 2023/3/5
 * @Version 1.0
 **/
@Component
public class GlobalRibbonConfig {

    /**
     * @Description 配置自定义负载均衡规则
     * @Author sy
     * @Date 2023/3/5
     * @Version 1.0
     **/
    @Bean
    public IRule iRule() {
        // 实现带有权重的负载均衡策略
        return new WeightedBalancerRule();
        // 实现同集群优先的负载均衡策略
        //return new TheSameClusterPriorityRule();
        // 实现同版本同集群优先的负载均衡策略（必须同版本，可以不同集群）
        //return new TheSameClusterPriorityWithVersionRule();
    }

}
