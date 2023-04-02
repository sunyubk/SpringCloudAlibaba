package com.sy.springcloud.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @ClassName stock
 * @Description
 * @Author sunyu
 * @Date 2023/4/2 20:18
 * @Version 1.0
 **/
@Data
@TableName("stock")
public class Stock {

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @TableField("proid")
    private Integer proid;

    @TableField("count")
    private Integer count;
}
