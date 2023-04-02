package com.sy.springcloud.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sy.springcloud.entity.Order;
import org.apache.ibatis.annotations.Mapper;

/**
 * @ClassName orderMapper
 * @Description
 * @Author sunyu
 * @Date 2023/4/2 20:33
 * @Version 1.0
 **/
@Mapper
public interface OrderMapper extends BaseMapper<Order> {
}
