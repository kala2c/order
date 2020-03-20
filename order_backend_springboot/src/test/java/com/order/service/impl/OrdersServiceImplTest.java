package com.order.service.impl;

import com.order.domain.dto.OrderDetailDto;
import com.order.domain.dto.OrderDto;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.awt.print.Pageable;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class OrdersServiceImplTest {

    @Autowired
    OrdersServiceImpl ordersService;

    @Test
    public void createTest() {
        OrderDto orderDto = new OrderDto();
        orderDto.setSeatId(1);
        orderDto.setDescription("备注信息");
        List<OrderDetailDto> orderDetailDtoList = new ArrayList<>();
        OrderDetailDto orderDetailDto1 = new OrderDetailDto();
        OrderDetailDto orderDetailDto2 = new OrderDetailDto();
        OrderDetailDto orderDetailDto3 = new OrderDetailDto();
        orderDetailDto1.setGoodsId(1);
        orderDetailDto1.setCount(2);
        orderDetailDto2.setGoodsId(2);
        orderDetailDto2.setCount(1);
        orderDetailDto3.setGoodsId(3);
        orderDetailDto3.setCount(1);
        orderDetailDtoList.add(orderDetailDto1);
        orderDetailDtoList.add(orderDetailDto2);
        orderDetailDtoList.add(orderDetailDto3);
        orderDto.setOrderDetails(orderDetailDtoList);
        OrderDto result = ordersService.create(orderDto);
        OrderDto result1 = ordersService.create(orderDto);
        OrderDto result2 = ordersService.create(orderDto);
        Assert.assertNotNull(result);
    }

    @Test
    public void findTest() {
        OrderDto orderDto = ordersService.findOne(1);
        OrderDto orderDto1 = ordersService.findOne("15826871061808821");
        Assert.assertNotNull(orderDto);
    }

    @Test
    public void findListTest()
    {
        PageRequest pageRequest = PageRequest.of(0, 10);
        Page<OrderDto> orderDtoList1 = ordersService.findFinishedList(pageRequest);
        Page<OrderDto> orderDtoList2 = ordersService.findCancelList(pageRequest);
        List<OrderDto> orderDtoList3 = ordersService.findUnFinishedList();

        Assert.assertNotNull(orderDtoList1);
        Assert.assertNotNull(orderDtoList2);
        Assert.assertNotNull(orderDtoList3);
    }

    @Test
    public void acceptTest() {
        Integer result = ordersService.accept(3);

        log.info("结果：{}", result);
    }

    @Test
    public void finish() {
        Integer result = ordersService.finish(4);

        log.info("结果：{}", result);
    }

    @Test
    public void cancel() {
        Integer result = ordersService.cancel(2);
        log.info("结果：{}", result);
    }

    @Test
    public void pay() {
    }
}