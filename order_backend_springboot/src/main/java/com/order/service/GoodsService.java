package com.order.service;

import com.order.domain.entity.Goods;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface GoodsService {

    Goods create(Goods goods);

    Goods updateInfo(Goods goods);

    Goods findOne(Integer id);

    /**
     * 前端api-菜品列表 使用该方法查出全部上架菜品
     * @return 全部上架菜品列表
     */
    List<Goods> findAllByStatusUp();

    List<Goods> findAllByCategoryId(Integer CategoryId);

    Page<Goods> findAll(Pageable pageable, Integer categoryId, Integer status);
}
