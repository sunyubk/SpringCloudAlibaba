package com.sy.springcloud.common.handler;

/**
 * @ClassName surport
 * @Description
 * 工厂的接口类，这个接口建议和工厂放在一个模块
 * 这样其他模块需要实现这个接口使用的时候只需要引入公共模块就好了，可以避免其他业务模块会发生相互引用的问题
 * @Author sunyu
 * @Date 2023/3/23 23:00
 * @Version 1.0
 **/
public interface SupportFactory {

    public String test();
}
