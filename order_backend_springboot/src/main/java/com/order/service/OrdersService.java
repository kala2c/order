package com.order.service;

import com.order.domain.dto.OrderDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface OrdersService {

    /**
     * 创建订单
     * @param orderDto 订单dto
     * @return 订单dto
     */
    OrderDto create(OrderDto orderDto);

//    根据id查询订单详情
    OrderDto findOne(Integer orderId);

//    根据订单号查询订单详情
    OrderDto findOne(String orderNo);

    /**
     * 查询已完成订单列表
     * @param pageable 分页参数
     * @return 订单列表
     */
    Page<OrderDto> findFinishedList(Pageable pageable);

    /**
     * 查询未接单和未完成得到订单
     * @return 订单列表
     */
    List<OrderDto> findUnFinishedList();

    /**
     * 查询已取消的订单
     * @return 订单列表
     */
    Page<OrderDto> findCancelList(Pageable pageable);

    /**
     * 接单
     * @param orderId 订单id
     * @return 结果
     */
    Integer accept(Integer orderId);

    /**
     * 结束订单
     * @param orderId 订单id
     * @return 结果
     */
    Integer finish(Integer orderId);

    /**
     * 取消订单
     * @param orderId 订单id
     * @return 结果
     */
    Integer cancel(Integer orderId);

    /**
     * 支付订单
     * @param orderId 订单id
     * @return 结果
     */
    Integer pay(Integer orderId);
}
