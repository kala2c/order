package com.order.service.impl;


import com.order.domain.entity.Category;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class CategoryServiceImplTest {

    @Autowired
//    CategoryService categoryService;

    @Test
    @Transactional  //让测试的数据不插入数据库 在测试里会完全回滚
    public void SaveTest()
    {
        Category category = new Category();
        category.setName("测试");
        category.setDescription("测试测试测试测试测试");
//        Category rlt = categoryService.save(category);
//        Assert.assertNotNull(rlt);
    }

}
