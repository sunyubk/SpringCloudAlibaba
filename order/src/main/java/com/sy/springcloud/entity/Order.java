package com.sy.springcloud.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @ClassName Order
 * @Description
 * @Author sunyu
 * @Date 2023/4/2 20:30
 * @Version 1.0
 **/
@Data
@TableName("`order`")
public class Order {

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @TableField("proid")
    private Integer proid;

    @TableField("total_money")
    private Integer totalMoney;

    @TableField("status")
    private Integer status;
}
