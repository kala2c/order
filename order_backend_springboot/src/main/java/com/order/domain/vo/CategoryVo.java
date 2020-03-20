package com.order.domain.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

/**
 * 响应给前端的数据
 */
@Data
public class CategoryVo {

    private Integer Id;

    private String name;

    private String description;

    private List<GoodsVo> goods;
}
