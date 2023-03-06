package com.sy.springcloud.controller;

import com.alibaba.csp.sentinel.Entry;
import com.alibaba.csp.sentinel.EntryType;
import com.alibaba.csp.sentinel.SphU;
import com.alibaba.csp.sentinel.Tracer;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeRule;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeRuleManager;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName SentinelContoller
 * @Description Sentinel 流控实例
 * @Author sunyu
 * @Date 2023/3/5 20:32
 * @Version 1.0
 **/
@Slf4j
@RestController
@RequestMapping(value = "/sentinel")
public class SentinelController {

    private static final String RESOURCE_NAME = "hello";

    private static final String USER_RESOURCE_NAME = "user";

    private static final String DEGRADE_RESOURCE_NAME = "degrade";

    /**
     * @Description 测试流控接口
     * @Author sy
     * @Date 2023/3/6
     * @Version 1.0
     **/
    @GetMapping("/hello")
    public String hello() {
        Entry entry = null;
        try {
            // sentinel 针对资源进行的限制
            entry = SphU.entry(RESOURCE_NAME);
            String result = "Hello World!";
            log.info(result);
            return result;
        } catch (BlockException e) {
            // 自愿访问组织，被限流或被降级
            // 进行相应的处理操作
            log.info("被限流了");
            return "被流控了！";
        } catch (Exception e) {
            // 若需要配置降级规则，需要通过这种方式记录业务异常
            Tracer.traceEntry(e, entry);
        }finally {
            if (entry != null) {
                entry.exit();
            }
        }
        return null;
    }


    /**
     * @Description 测试流控注解接口
     * @SentinelResource 改善接口中资源定义和被流控降级后的处理方法
     * value: 定义的资源名称
     * blockHandler: 被流控降级后的处理方法(默认该方法必须生命在同一个类中)（优先级高于fallback）
     * blockHandlerClass: 可以将处理方法放到别的类中
     * fallback: 异常的处理方法
     * fallbackClass: 可以异常的处理方法放在白的类中
     * ExceptionsToIgnore：排除异常不被fallback拦截
     * @Author sy
     * @Date 2023/3/6
     * @Version 1.0
     **/
    @GetMapping("/user")
    @SentinelResource(value = USER_RESOURCE_NAME, blockHandler = "blockHandleForUser")
    public String user(String id) {
        log.info("user:" + id);
        return "user";
    }

    /**
     * @Description 被流控后的处理方法，该方法必须是由public声明的，入参与返回值要与原方法一致
     * @Author sy
     * @Date 2023/3/6
     * @Version 1.0
     **/
    public String blockHandleForUser(String id, BlockException e) {
        e.printStackTrace();
        log.info("user被流控了");
        return "user被流控了";
    }


    /**
     * @Description 测试熔断降级接口
     * @Author sy
     * @Date 2023/3/6
     * @Version 1.0
     **/
    @GetMapping("/degrade")
    @SentinelResource(value = DEGRADE_RESOURCE_NAME, entryType = EntryType.IN, blockHandler = "blockHandleForDegrade")
    public String degrade() {
        int s = 1 / 0;
        return "degrade";
    }

    /**
     * @Description 被熔断降级后的处理方法，该方法必须是由public声明的，入参与返回值要与原方法一致
     * @Author sy
     * @Date 2023/3/6
     * @Version 1.0
     **/
    public String blockHandleForDegrade(BlockException e) {
        e.printStackTrace();
        log.info("user被熔断了");
        return "user被熔断了";
    }


    /**
     * @Description 初始化流控规则
     * @PostConstruct 注解是Spring的注解，作用就是当当前类被加载的时候就会初始化当前这个方法
     * @Author sy
     * @Date 2023/3/5
     * @Version 1.0
     **/
    @PostConstruct
    private static void initFlowRules() {
        // 流控规则
        List<FlowRule> rules = new ArrayList<>();
        // 定义流控对象
        FlowRule ruleHello = new FlowRule();
        // 设置受保护的资源
        ruleHello.setResource(RESOURCE_NAME);
        // 设置流控规则策略 QPS/线程数
        ruleHello.setGrade(RuleConstant.FLOW_GRADE_QPS);
        // 阈值
        ruleHello.setCount(1);
        rules.add(ruleHello);

        // 为使用@SentinelResouce注解的接口定义流控规则
        // 定义流控对象
        FlowRule ruleUser = new FlowRule();
        // 设置受保护的资源
        ruleUser.setResource(USER_RESOURCE_NAME);
        // 设置流控规则策略 QPS/线程数
        ruleUser.setGrade(RuleConstant.FLOW_GRADE_QPS);
        // 阈值
        ruleUser.setCount(1);
        rules.add(ruleUser);
        // 加载配置好的规则
        FlowRuleManager.loadRules(rules);

    }

    /**
     * @Description 初始化降级规则
     * @Author sy
     * @Date 2023/3/6
     * @Version 1.0
     **/
    @PostConstruct
    public void initDegradeRule() {
        List<DegradeRule> list = new ArrayList<>();
        // 定义降级规则对象
        DegradeRule degradeRule = new DegradeRule();
        // 定义降级规则资源
        degradeRule.setResource(DEGRADE_RESOURCE_NAME);
        // 设置熔断降级的策略：异常数   多种策略：慢调用策略、异常比例、异常数策略
        degradeRule.setGrade(RuleConstant.DEGRADE_GRADE_EXCEPTION_COUNT);
        // 阈值
        degradeRule.setCount(2);
        // 熔断时长  熔断过后会进入半开状态，回复调用的第一次调用就发生异常的话会直接进行熔断，不会根据这里的配置进行判断
        degradeRule.setTimeWindow(10);
        // 熔断触发的最小请求数，请求数小于该值时即使异常比率超出阈值也不会熔断
        degradeRule.setMinRequestAmount(2);
        // 间隔时长，可以理解为触发异常需要在这个时间间隔内
        degradeRule.setStatIntervalMs(60 * 1000);
        list.add(degradeRule);

        // 加载降级规则
        DegradeRuleManager.loadRules(list);

    }

}
