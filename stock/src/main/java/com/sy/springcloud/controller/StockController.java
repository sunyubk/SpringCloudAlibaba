package com.sy.springcloud.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.sy.springcloud.entity.Stock;
import com.sy.springcloud.mapper.StockMapper;
import io.seata.spring.annotation.GlobalTransactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 库存
 * @author sunyu
 */
@RestController
@RequestMapping("/stock")
public class StockController {

    @Autowired
    private StockMapper stockMapper;

    @Value(value = "${server.port}")
    String port;

    /**
     * 减少库存接口
     * @return String
     */
    @PostMapping(value = "/reduceStock")
    public String reduceStock() {
        System.out.println("扣减库存");
        return "扣减库存"+port;
    }
    @PostMapping(value = "/reduceStock2")
    public String reduceStock2() {
        int num = 1 / 0;
        System.out.println("扣减库存");
        return "扣减库存"+port;
    }

    @GetMapping("/rdStock")
    public String rdStock(@RequestParam("proid") Integer proid, @RequestParam("num") Integer num) {
        Stock stock = stockMapper.selectOne(new LambdaQueryWrapper<>(Stock.class).eq(Stock::getProid, proid));
        System.out.println(stock);

        stock.setCount(stock.getCount() - num);
        Integer stockResult = stockMapper.updateById(stock);
        if (stockResult > 0) {
            return "库存更新成功";
        }
        return "库存更新失败";
    }
}
