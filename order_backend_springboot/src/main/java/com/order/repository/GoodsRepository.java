package com.order.repository;

import com.order.domain.entity.Goods;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;
import java.util.List;

public interface GoodsRepository extends JpaRepository<Goods, Integer> {
    /**
     * 根据类目id列表查询商品
     * @param categoryIdList 类目id列表
     * @return 商品列表
     */
    List<Goods> findAllByCategoryIdIn(List<Integer> categoryIdList);

    /**
     * 根据类目id查询商品
     * @param categoryId 类目id
     * @return 商品列表
     */
    List<Goods> findAllByCategoryIdOrderByWeight(Integer categoryId);

    Page<Goods> findAllByCategoryIdAndStatus(Integer categoryId, Integer status, Pageable pageable);
    Page<Goods> findAllByStatus(Integer status, Pageable pageable);
    Page<Goods> findAllByCategoryId(Integer categoryId, Pageable pageable);

    List<Goods> findAllByStatusOrderByWeight(Integer status);

    List<Goods> findAllByIdIn(List<Integer> idList);

    /**
     * 更新商品信息
     * @param id 要更新的商品id
     * @param name 更新后的名字
     * @param description 更新后的介绍
     * @param price 更新后的价格
     * @return Integer 返回结果
     */
    @Modifying
    @Query(value = "update goods set name=?2, description=?3, price=?4 where id=?1 ", nativeQuery = true)
    Integer updateInfo(Integer id, String name, String description, BigDecimal price);
}
