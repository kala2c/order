package com.order.repository;


import com.order.domain.entity.Category;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class CategoryRepositoryTest {
    @Autowired
    CategoryRepository categoryRepository;
    
    @Test
    public void findTest()
    {
        List<Category> rlt = categoryRepository.findAll();

        Assert.assertNotEquals(0 , rlt.size());
    }

}
