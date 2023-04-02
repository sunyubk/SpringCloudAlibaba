package com.sy.springcloud.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sy.springcloud.entity.Stock;
import org.apache.ibatis.annotations.Mapper;

/**
 * @ClassName StockMapper
 * @Description
 * @Author sunyu
 * @Date 2023/4/2 20:25
 * @Version 1.0
 **/
@Mapper
public interface StockMapper extends BaseMapper<Stock> {
}
