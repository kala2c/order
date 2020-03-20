package com.order.repository;

import com.order.domain.entity.Orders;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class OrdersRepositoryTest {

    @Autowired
    OrdersRepository ordersRepository;

    @Test
    public void findTest()
    {
        List<Orders> ordersList = ordersRepository.findAll();

        Assert.assertNotNull(ordersList);
    }
}