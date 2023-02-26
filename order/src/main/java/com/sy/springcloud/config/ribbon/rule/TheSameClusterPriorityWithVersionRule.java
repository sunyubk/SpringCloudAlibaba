package com.sy.springcloud.config.ribbon.rule;


import com.alibaba.cloud.nacos.NacosDiscoveryProperties;
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

import java.util.List;
import java.util.stream.Collectors;

/**
 * 同版本同集群优先调用--负载均衡策略（金丝雀/灰度测试）
 * 1、优先同版本同集群的服务调用
 * 2、没有 条件1的，同版本跨集群的服务调用
 * 3、不可以进行不同版本的服务调用
 */
@Slf4j
public class TheSameClusterPriorityWithVersionRule extends AbstractLoadBalancerRule {

    @Autowired
    private NacosServiceManager nacosServiceManager;

    @Autowired
    private NacosDiscoveryProperties nacosDiscoveryProperties;


    @Override
    public void initWithNiwsConfig(IClientConfig iClientConfig) {

    }

    @Override
    public Server choose(Object o) {
        try {
            // 获取服务所在的集群名称
            String clusterName = nacosDiscoveryProperties.getClusterName();
            // 获取服务的版本号
            String version = nacosDiscoveryProperties.getMetadata().get("version");
            log.info("当前集群名称：{},服务版本号：{}", clusterName, version);
            // 获取当前的负载均衡器
            BaseLoadBalancer loadBalancer = (BaseLoadBalancer) this.getLoadBalancer();
            // 获取目标服务的实例名称
            String serviceName = loadBalancer.getName();
            log.info("目标服务的实例名称：{}", serviceName);
            // 获取nacos服务注册发现的api
            NamingService namingService = nacosServiceManager.getNamingService();
            // 通过namimgService获取当前注册的所有实例
            List<Instance> allInstance = namingService.getAllInstances(serviceName);

            // 过滤筛选同版本的服务
            List<Instance> sameVersionInstance;
            sameVersionInstance = allInstance.stream().filter(instance -> instance.getMetadata().get("version").equals(version)).collect(Collectors.toList());


            // 过滤筛选同版本下的同集群的服务实例
            List<Instance> sameClusterInstanceList;
            sameClusterInstanceList = sameVersionInstance.stream().filter(instance -> instance.getClusterName().equals(clusterName)).collect(Collectors.toList());
            log.info("统计群的服务实例：{}", sameClusterInstanceList);
            Instance toBeChooseInstance;
            // 选择合适的服务实例（必须同版本）
            if (sameClusterInstanceList == null || sameClusterInstanceList.size() == 0) {
                // 从所有同版本的服务实例中选一个
                toBeChooseInstance = WeightedBalancer.chooseInstanceByRandomWeight(sameVersionInstance);
                log.info("跨集群调用---{}", toBeChooseInstance.getPort());
            } else {
                // 从本集群中随机选择一个服务实例
                toBeChooseInstance = WeightedBalancer.chooseInstanceByRandomWeight(sameClusterInstanceList);
                log.info("同集群调用---{}", toBeChooseInstance.getPort());
            }
            return new NacosServer(toBeChooseInstance);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
