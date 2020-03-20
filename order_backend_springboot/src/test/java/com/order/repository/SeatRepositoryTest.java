package com.order.repository;


import com.order.domain.entity.Seat;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SeatRepositoryTest {

    @Autowired
    SeatRepository seatRepository;

    @Test
    public void findAllByStatusInTest()
    {
        List<Integer> list = Arrays.asList(1, 2, 3);

        List<Seat> rlt  = seatRepository.findAllByStatusIn(list);

        Assert.assertNotEquals(0, rlt.size());
    }

    @Test
    public void findByStatusInTest()
    {
        List<Integer> list = Arrays.asList(1, 2, 3);

        List<Seat> rlt  = seatRepository.findAll();

        Assert.assertNotEquals(0, rlt.size());
    }
}
