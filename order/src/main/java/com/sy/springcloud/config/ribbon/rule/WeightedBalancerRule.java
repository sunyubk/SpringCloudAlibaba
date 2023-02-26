package com.sy.springcloud.config.ribbon.rule;

import com.alibaba.cloud.nacos.NacosServiceManager;
import com.alibaba.cloud.nacos.ribbon.NacosServer;
import com.alibaba.nacos.api.naming.NamingService;
import com.alibaba.nacos.api.naming.pojo.Instance;
import com.netflix.client.config.IClientConfig;
import com.netflix.loadbalancer.AbstractLoadBalancerRule;
import com.netflix.loadbalancer.BaseLoadBalancer;
import com.netflix.loadbalancer.Server;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 根据权重随机选择一个实例
 */
@Slf4j
public class WeightedBalancerRule extends AbstractLoadBalancerRule {

    @Autowired
    private NacosServiceManager nacosServiceManager;
    @Override
    public void initWithNiwsConfig(IClientConfig iClientConfig) {

    }

    @Override
    public Server choose(Object o) {
        try {
            log.info("key:{}",o);
            // 调用父类方法，获得当前使用的均衡负载器
            BaseLoadBalancer loadBalancer = (BaseLoadBalancer) this.getLoadBalancer();
            // 获取目标服务的名称
            String serviceName = loadBalancer.getName();
            // 获取nacos的服务发现的API
            NamingService namingService = nacosServiceManager.getNamingService();

            // 根据名称获取服务发现实例，在selectOneHealthyInstance中，nacos实现了权重的负载均衡算法
            Instance instance = namingService.selectOneHealthyInstance(serviceName);

            return new NacosServer(instance);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
