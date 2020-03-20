package com.order.service.impl;

import com.order.domain.entity.Goods;
import com.order.enums.GoodsStatusEnum;
import com.order.enums.ResultEnum;
import com.order.exception.AppException;
import com.order.repository.GoodsRepository;
import com.order.service.GoodsService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * 商品service
 */
@Service
public class GoodsServiceImpl implements GoodsService {

    @Autowired
    GoodsRepository goodsRepository;

    /**
     * 创建菜品
     * @param goods 菜品实体
     * @return 菜品实体
     */
    @Override
    public Goods create(Goods goods) {
        return goodsRepository.save(goods);
    }

    /**
     * 更新菜品信息
     * @param goods 菜品实体 包含id、name、description、price
     * @return 菜品实体
     */
    @Override
    @Transactional
    public Goods updateInfo(Goods goods) {
        Optional<Goods> optional = goodsRepository.findById(goods.getId());
        if (optional.isPresent()) {
            Goods goodsTemp = optional.get();
            BeanUtils.copyProperties(goods, goodsTemp);
            goodsRepository.updateInfo(goodsTemp.getId(), goodsTemp.getName(), goodsTemp.getDescription(), goods.getPrice());
            return goods;
        } else {
            throw new AppException(ResultEnum.GOODS_NOT_FOUND);
        }
    }

    /**
     * 根据id查找菜品
     * @param id 菜品id
     * @return 菜品实体
     */
    @Override
    public Goods findOne(Integer id) {
        Optional<Goods> optional = goodsRepository.findById(id);
        if (optional.isPresent()) {
            return optional.get();
        } else {
            throw new AppException(ResultEnum.GOODS_NOT_FOUND);
        }
    }

    /**
     * 前端菜品列表api使用该方法查出全部上架菜品
     * @return 全部上架菜品列表
     */
    @Override
    public List<Goods> findAllByStatusUp() {
        return goodsRepository.findAllByStatusOrderByWeight(GoodsStatusEnum.UP.getStatus());
    }

    @Override
    public List<Goods> findAllByCategoryId(Integer categoryId) {
        return goodsRepository.findAllByCategoryIdOrderByWeight(categoryId);
    }

    /**
     * 后台查询商品列表
     * @param pageable 分页
     * @param categoryId 分类id
     * @return 商品page
     */
    @Override
    public Page<Goods> findAll(Pageable pageable, Integer categoryId, Integer status) {
        Page<Goods> goods = null;
        if (categoryId == 0 && status == -1) {
            goods = goodsRepository.findAllByCategoryId(categoryId, pageable);
        } else if (categoryId != 0 && status == -1) {
            goods = goodsRepository.findAllByCategoryId(categoryId, pageable);
        } else if (categoryId == 0 && status != -1) {
            goods = goodsRepository.findAllByStatus(status, pageable);
        } else if (categoryId != 0 && status != -1) {
            goods = goodsRepository.findAllByCategoryIdAndStatus(categoryId, status, pageable);
        }
        return goods;
    }
}
