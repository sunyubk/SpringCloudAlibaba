package com.sy.springcloud.service.impl;

import com.sy.springcloud.common.handler.SupportFactory;
import org.springframework.stereotype.Service;

/**
 * @ClassName Test
 * @Description
 * @Author sunyu
 * @Date 2023/3/23 23:03
 * @Version 1.0
 **/
@Service("Test")
public class MyFactoryDemo1 implements SupportFactory {
    @Override
    public String test() {
        return "test123123";
    }
}
