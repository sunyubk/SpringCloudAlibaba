package com.sy.springcloud.config.ribbon.rule;

import com.alibaba.nacos.api.naming.pojo.Instance;
import com.alibaba.nacos.client.naming.core.Balancer;

import java.util.List;

/**
 * 根据权重随机选择一个实例
 */
public class WeightedBalancer extends Balancer {
    public static Instance chooseInstanceByRandomWeight(List<Instance> instanceList) {
        return getHostByRandomWeight(instanceList);
    }
}
