package com.order.service;

import com.order.domain.entity.Category;

import java.util.List;

/**
 * 分类条目
 */
public interface CategoryService {

    /**
     * 新增分类
     * @param category 类目实体
     * @return 返回新增的类目
     */
    Category create(Category category);

    /**
     * 更新类目
     * @param category 类目实体
     * @return 返回更新的类目
     */
    Category updateInfo(Category category);

    /**
     * 查询全部类目
     * @return 类目列表
     */
    List<Category> findAll();

    /**
     * 查询指定类目
     * @param categoryIdList 指定类目的主键列表
     * @return 类目列表
     */
    List<Category> findAllByIdIn(List<Integer> categoryIdList);

}
