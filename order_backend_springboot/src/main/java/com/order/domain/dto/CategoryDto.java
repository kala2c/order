package com.order.domain.dto;

import com.order.domain.vo.GoodsVo;
import lombok.Data;

import java.util.List;

/**
 * 响应给后台页面的数据
 */
@Data
public class CategoryDto {

    private Integer Id;

    private String name;

    private String description;

    private List<GoodsVo> goods;
}
