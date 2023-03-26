package com.sy.springcloud.service.openFeign;

import com.sy.springcloud.config.feign.FeignConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * @Description
 * feign接口
 * name：对应的调用的服务的实例名称
 * path：对应的调用的接口的地址，就是对应的Controller指定的@RequestMapping()配置的地址
 * configuration = FeignConfig.class，可以加入这个配置，这个配置用于这个接口使用的feign配置类，就是局部配置的时候使用的
 * @Author sy
 * @Date 2023/3/5
 * @Version 1.0
 **/
@FeignClient(name = "stock", path = "/stock", fallback = StockSentinelFallback.class)
public interface Stock {

    /**
     * @Description 这里的接口就与调用的Controller里面的接口一直就可以了，其实将对应的接口复制过来就可以了，只不过是没有实现的
     * @Author sy
     * @Date 2023/3/5
     * @Version 1.0
     **/
    @PostMapping(value = "/reduceStock")
    public String reduceStock();

    @PostMapping(value = "/reduceStock2")
    public String reduceStock2();
}
