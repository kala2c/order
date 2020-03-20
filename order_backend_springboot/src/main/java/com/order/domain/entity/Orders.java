package com.order.domain.entity;

import com.order.enums.OrderPayStatusEnum;
import com.order.enums.OrderStatusEnum;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.jpa.repository.EntityGraph;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Entity
@Data
@DynamicUpdate
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String orderNo;

    private Integer seatId;

    private String description;

    private BigDecimal amount;

    private Integer status = OrderStatusEnum.NEW.getStatus();

    private Integer payStatus = OrderPayStatusEnum.WAIT_PAY.getStatus();

    private Date createTime;

    private Date updateTime;

    private Integer del = 0;

//    @OneToMany()
//    @JoinColumn(name = "order_id")
//    private List<OrderDetail> orderDetailList;
}
