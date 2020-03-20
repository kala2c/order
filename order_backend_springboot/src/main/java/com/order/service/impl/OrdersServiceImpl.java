package com.order.service.impl;

import com.order.domain.dto.OrderDetailDto;
import com.order.domain.dto.OrderDto;
import com.order.domain.entity.Goods;
import com.order.domain.entity.OrderDetail;
import com.order.domain.entity.Orders;
import com.order.enums.OrderPayStatusEnum;
import com.order.enums.OrderStatusEnum;
import com.order.enums.ResultEnum;
import com.order.exception.AppException;
import com.order.repository.GoodsRepository;
import com.order.repository.OrderDetailRepository;
import com.order.repository.OrdersRepository;
import com.order.service.OrdersService;
import com.order.utils.KeyUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.*;

@Service
public class OrdersServiceImpl implements OrdersService {

    @Autowired
    GoodsRepository goodsRepository;
    @Autowired
    OrdersRepository ordersRepository;
    @Autowired
    OrderDetailRepository orderDetailRepository;

    /**
     * 创建订单
     * @param orderDto 订单dto
     * @return 订单dto
     */
    @Override
    @Transactional
    public OrderDto create(OrderDto orderDto) {
        // 查询商品 数量 金额
        List<OrderDetailDto> orderDetailDtoList = orderDto.getOrderDetails();
        // 组装商品id
        List<Integer> goodsIdList = new ArrayList<>();
        Map<Integer, Integer> goodsMap = new HashMap<>();
        for (OrderDetailDto orderDetailDto : orderDetailDtoList) {
            goodsIdList.add(orderDetailDto.getGoodsId());
            goodsMap.put(orderDetailDto.getGoodsId(), orderDetailDto.getCount());
        }
        //生成订单编号
        String orderNo = KeyUtil.createUniqueKey();
        orderDto.setOrderNo(orderNo);
        // 查询商品信息
        List<Goods> goodsList = goodsRepository.findAllByIdIn(goodsIdList);
        List<OrderDetail> orderDetailList = new ArrayList<>();
        // 计算总价 生成orderDetailList
        BigDecimal amount = new BigDecimal(BigInteger.ZERO);
        for (Goods goods : goodsList) {
//            if (goods.getStock() < goodsMap.get(goods.getId())) {
//                商品库存不够了
//            }

            amount = goods.getPrice()
                    .multiply(new BigDecimal(goodsMap.get(goods.getId())))
                    .add(amount);
            OrderDetail orderDetail = new OrderDetail();
            orderDetail.setOrderNo(orderNo);
            orderDetail.setGoodsId(goods.getId());
            orderDetail.setCount(goodsMap.get(goods.getId()));
            orderDetailList.add(orderDetail);
        }
        // 扣库存
        // 保存订单
        Orders orders = new Orders();
        orders.setOrderNo(orderNo);
        orders.setSeatId(orderDto.getSeatId());
        orders.setDescription(orderDto.getDescription());
        orders.setAmount(amount);
        Orders result1 = ordersRepository.save(orders);
//        if (result1 == null) {
//            throw new AppException(ResultEnum.ORDER_CREATE_FAIL);
//        }
        // 保存订单详情
        List<OrderDetail> result2 = orderDetailRepository.saveAll(orderDetailList);
        if (result2.isEmpty()) {
            throw new AppException(ResultEnum.ORDER_DETAIL_SAVE_FAIL);
        }
        return orderDto;
    }

    @Override
    public OrderDto findOne(Integer orderId) {
        Optional<Orders> optional = ordersRepository.findById(orderId);
        if (!optional.isPresent()) {
            throw new AppException(ResultEnum.ORDER_NOT_FOUND);
        }
        Orders orders = optional.get();
        List<OrderDetail> orderDetailList = orderDetailRepository.findAllByOrderNo(orders.getOrderNo());
        return getOrderDto(orders, orderDetailList);
    }

    //组合orderDto
    private OrderDto getOrderDto(Orders orders, List<OrderDetail> orderDetailList) {
        if (orderDetailList.isEmpty()) {
            throw new AppException(ResultEnum.ORDER_EMPTY);
        }
        List<OrderDetailDto> orderDetailDtoList = new ArrayList<>();
        for (OrderDetail orderDetail : orderDetailList) {
            OrderDetailDto orderDetailDto = new OrderDetailDto();
            BeanUtils.copyProperties(orderDetail, orderDetailDto);
            orderDetailDtoList.add(orderDetailDto);
        }
        OrderDto orderDto = new OrderDto();
        BeanUtils.copyProperties(orders, orderDto);
        orderDto.setOrderDetails(orderDetailDtoList);
        return orderDto;
    }

    @Override
    public OrderDto findOne(String orderNo) {
        Orders orders = ordersRepository.findOneByOrderNo(orderNo);
        if (orders == null) {
            throw new AppException(ResultEnum.ORDER_NOT_FOUND);
        }
        List<OrderDetail> orderDetailList = orderDetailRepository.findAllByOrderNo(orderNo);
        return getOrderDto(orders, orderDetailList);
    }

    @Override
    public Page<OrderDto> findFinishedList(Pageable pageable) {
        Page<Orders> ordersPageList = ordersRepository.findAllByStatus(OrderStatusEnum.FINISHED.getStatus(), pageable);
        return getOrderDtos(pageable, ordersPageList);
    }

    @Override
    public Page<OrderDto> findCancelList(Pageable pageable) {
        Page<Orders> ordersPageList = ordersRepository.findAllByStatus(OrderStatusEnum.CANCEL.getStatus(), pageable);
        return getOrderDtos(pageable, ordersPageList);
    }

    // findFinishedList 和 findCancelList的公共部分
    private Page<OrderDto> getOrderDtos(Pageable pageable, Page<Orders> ordersPageList) {
        List<OrderDto> orderDtoList = new ArrayList<>();
        for (Orders orders : ordersPageList.getContent()) {
            OrderDto orderDto = new OrderDto();
//            orderDto.setStatusText();
            BeanUtils.copyProperties(orders, orderDto);
            orderDtoList.add(orderDto);
        }
        return new PageImpl<OrderDto>(orderDtoList, pageable, ordersPageList.getTotalPages());
    }

    @Override
    public List<OrderDto> findUnFinishedList() {
        List<Integer> list = new ArrayList<>();
        list.add(OrderStatusEnum.NEW.getStatus());
        list.add(OrderStatusEnum.ACCEPT.getStatus());
        List<Orders> ordersList = ordersRepository.findAllByStatusIn(list);
        List<OrderDto> orderDtoList = new ArrayList<>();
        for (Orders orders : ordersList) {
            OrderDto orderDto = new OrderDto();
            BeanUtils.copyProperties(orders, orderDto);
            orderDtoList.add(orderDto);
        }
        return orderDtoList;
    }

    @Override
    @Transactional
    public Integer accept(Integer orderId) {
        return ordersRepository.changeStatus(orderId, OrderStatusEnum.ACCEPT.getStatus(), OrderStatusEnum.NEW.getStatus());
    }

    @Override
    @Transactional
    public Integer finish(Integer orderId) {
        return ordersRepository.changeStatus(orderId, OrderStatusEnum.FINISHED.getStatus(), OrderStatusEnum.ACCEPT.getStatus());
    }

    @Override
    @Transactional
    public Integer cancel(Integer orderId) {
        return ordersRepository.changeStatus(orderId, OrderStatusEnum.CANCEL.getStatus());
    }

    @Override
    @Transactional
    public Integer pay(Integer orderId) {
        return ordersRepository.changePayStatus(orderId, OrderPayStatusEnum.PAYED.getStatus());
    }
}
