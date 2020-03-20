package com.order.repository;

import com.order.domain.entity.Orders;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrdersRepository extends JpaRepository<Orders, Integer> {

    Orders findOneByOrderNo(String orderNo);

    List<Orders> findAllBySeatId(Integer seatId);

    List<Orders> findAllByStatus(Integer status);

    List<Orders> findAllByStatusIn(List<Integer> statusList);

    Page<Orders> findAllByStatus(Integer status, Pageable pageable);

    List<Orders> findAllByPayStatus(Integer payStatus);

    /**
     * 修改订单状态
     * @param orderId 订单id
     * @param newStatus 修改后的订单状态
     * @param oldStatus 订单原先的状态
     * @return 修改结果
     */
    @Modifying
    @Query(value = "update orders set status=?2 where id=?1 and status=?3", nativeQuery = true)
    Integer changeStatus(Integer orderId, Integer newStatus, Integer oldStatus);

    /**
     * 修改订单状态
     * @param orderId 订单id
     * @param newStatus 修改后的订单状态
     * @return 修改结果
     */
    @Modifying
    @Query(value = "update orders set status=?2 where id=?1", nativeQuery = true)
    Integer changeStatus(Integer orderId, Integer newStatus);

    /**
     * 修改支付状态
     * @param orderId 订单id
     * @param newStatus 修改后的订单状态
     * @return 修改结果
     */
    @Modifying
    @Query(value = "update orders set pay_status=?2 where id=?1 and pay_status=0", nativeQuery = true)
    Integer changePayStatus(Integer orderId, Integer newStatus);

}
