package com.order.service.impl;

import com.order.domain.entity.Goods;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class GoodsServiceImplTest {

    @Autowired
    GoodsServiceImpl goodsService;

    @Test
//    @Transactional
    public void createTest()
    {
        Goods goods = new Goods();
        goods.setCategoryId(2);
        goods.setName("火山飘雪");
        goods.setDescription("糖拌西红柿，甘甜美味");
        goods.setPrice(new BigDecimal("8.8"));

        Goods result = goodsService.create(goods);

        Assert.assertNotNull(result);
    }

    @Test
    public void updateInfoTest()
    {
        Goods goods = new Goods();
        goods.setId(1);
        goods.setName("更新测试");
        goods.setDescription("测试更新、更新");
        goods.setPrice(new BigDecimal("8.8"));

        Goods result = goodsService.updateInfo(goods);

        Assert.assertNotNull(result);
    }

    @Test
    public void findAllByCategoryIdTest()
    {
        List<Goods> list = goodsService.findAllByCategoryId(1);

        Assert.assertNotNull(list);
    }

    @Test
    public void findAllTest()
    {
        PageRequest pageRequest = PageRequest.of(0, 5);

        Page<Goods> result = goodsService.findAll(pageRequest, 0, -1);
        List<Goods> list = result.getContent();
        long count = result.getTotalElements();

        Assert.assertEquals(2, count);
    }
}