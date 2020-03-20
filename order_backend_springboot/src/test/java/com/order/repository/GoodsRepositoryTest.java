package com.order.repository;


import com.order.domain.entity.Goods;
import com.order.enums.GoodsStatusEnum;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class GoodsRepositoryTest {

    @Autowired
    GoodsRepository goodsRepository;

    @Test
    public void findAllByCategoryIdInTest()
    {
        List<Integer> list = Arrays.asList(1, 2, 3);

        List<Goods> rlt = goodsRepository.findAllByCategoryIdIn(list);

        Assert.assertNotEquals(0, rlt.size());
    }

    @Test
    public void createTest()
    {
        Goods goods = new Goods();
        goods.setCategoryId(2);
        goods.setName("粉身碎骨小青龙");
        goods.setDescription("精选优质黄瓜，辅以美味作料");
        goods.setPrice(new BigDecimal("8.5"));

        Goods createRlt = goodsRepository.save(goods);
        Assert.assertNotNull(createRlt);
    }

    @Test
    @Transactional
    public void updateInfoTest()
    {
        Integer rlt = goodsRepository.updateInfo(9, "燃面", "这个也不错", new BigDecimal("3.0"));
        Assert.assertNotNull(rlt);
    }

    @Test
    public void findAllByStatusOrderByWeightDescTest()
    {
        List<Goods> list = goodsRepository.findAllByStatusOrderByWeight(GoodsStatusEnum.UP.getStatus());

        Assert.assertNotNull(list);
    }

    @Test
    public void findAllByStatusByOrderByWeightDescTest()
    {
        log.info("status:{}", GoodsStatusEnum.UP.getStatus());
        List<Goods> list = goodsRepository.findAllByStatusOrderByWeight(GoodsStatusEnum.UP.getStatus());

        Assert.assertNotNull(list);
    }


}
