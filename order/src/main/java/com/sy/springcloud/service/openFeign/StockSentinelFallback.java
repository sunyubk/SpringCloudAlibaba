package com.sy.springcloud.service.openFeign;

import org.springframework.stereotype.Component;

/**
 * @ClassName StockSentinelFallback
 * @Description
 * @Author sunyu
 * @Date 2023/3/26 19:59
 * @Version 1.0
 **/
@Component
public class StockSentinelFallback implements Stock{
    @Override
    public String reduceStock() {
        return "流控11";
    }

    @Override
    public String reduceStock2() {
        return "降级了2";
    }

    @Override
    public String rdStock(Integer proid, Integer num) {
        return null;
    }
}
