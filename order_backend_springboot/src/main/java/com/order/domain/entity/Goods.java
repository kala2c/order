package com.order.domain.entity;

import com.order.enums.GoodsStatusEnum;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Data
@DynamicUpdate
public class Goods {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer categoryId;

    private String name;

    private String description;

    private String icon = "http://static.c2wei.cn/logo.png";

    private Integer stock = 99;

    private BigDecimal price;

    private Integer weight = 0;

    private Integer status = GoodsStatusEnum.UP.getStatus();

    private Date createTime;

    private Date updateTime;

    private Integer del = 0;
    
    public Goods() {
    }
}
