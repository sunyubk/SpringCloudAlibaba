package com.sy.springcloud.service.impl;

import com.sy.springcloud.common.handler.SupportFactory;
import org.springframework.stereotype.Service;

/**
 * @ClassName Test2
 * @Description
 * @Author sunyu
 * @Date 2023/3/23 23:07
 * @Version 1.0
 **/
@Service("Test2")
public class MyFactoryDemo2 implements SupportFactory {
    @Override
    public String test() {
        return "test2";
    }
}
