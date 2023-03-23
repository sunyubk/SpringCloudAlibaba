package com.sy.springcloud.common.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Objects;

/**
 * @ClassName Factory
 * @Description
 * 工厂类，工厂类可以放在公共模块中供其他业务模块使用
 * @Author sunyu
 * @Date 2023/3/23 23:08
 * @Version 1.0
 **/
@Service
public class MyFactory {

    @Autowired
    Map<String, SupportFactory> mapss;

    public SupportFactory getSP(Class<?> c) {
        SupportFactory supportFactory = mapss.get(c.getSimpleName());
        if (Objects.isNull(supportFactory)) {
            throw new RuntimeException("没有");
        }
        return supportFactory;
    }
}
