package com.order.controller.api;

import com.order.domain.entity.Category;
import com.order.domain.entity.Goods;
import com.order.service.impl.CategoryServiceImpl;
import com.order.service.impl.GoodsServiceImpl;
import com.order.utils.ResultUtil;
import com.order.domain.vo.CategoryVo;
import com.order.domain.vo.GoodsVo;
import com.order.domain.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/goods/")
@Slf4j
public class GoodsApiController {

    @Autowired
    GoodsServiceImpl goodsService;
    @Autowired
    CategoryServiceImpl categoryService;

    @GetMapping("/list")
    public Result<Object> list()
    {
//        查询全部上架商品
        List<Goods> goodsList = goodsService.findAllByStatusUp();
//        查询响应的分类信息
//        List<Integer> categoryIdList = new ArrayList<>();
//        for (Goods goods : goodsList) {
//            categoryIdList.add(goods.getCategoryId());
//        }
//        lambda写法
        List<Integer> categoryIdList = goodsList
                .stream()
//                .map(e -> e.getCategoryId())
                .map(Goods::getCategoryId)
                .collect(Collectors.toList());
        List<Category> categoryList = categoryService.findAllByIdIn(categoryIdList);
//        数据拼装
        List<CategoryVo> categoryVoList = new ArrayList<>();
        for (Category category : categoryList) {
            CategoryVo categoryVo = new CategoryVo();
            BeanUtils.copyProperties(category, categoryVo);

            List<GoodsVo> goodsVoList = new ArrayList<>();
            for (Goods goods : goodsList) {
                if (goods.getCategoryId().equals(category.getId())) {
                    GoodsVo goodsVo = new GoodsVo();
                    BeanUtils.copyProperties(goods, goodsVo);
                    goodsVoList.add(goodsVo);
                }
            }
            categoryVo.setGoods(goodsVoList);
            categoryVoList.add(categoryVo);
        }
        return ResultUtil.success(categoryVoList);
    }

    @GetMapping("/detail/{id}")
    public Result<Object> detail(@PathVariable("id") Integer id)
    {
        Goods goods = goodsService.findOne(id);
        GoodsVo goodsVo = new GoodsVo();
        BeanUtils.copyProperties(goods, goodsVo);
        return ResultUtil.success(goodsVo);
    }
}
