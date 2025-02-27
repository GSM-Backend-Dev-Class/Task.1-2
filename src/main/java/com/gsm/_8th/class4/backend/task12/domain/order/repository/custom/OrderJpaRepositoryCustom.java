package com.gsm._8th.class4.backend.task12.domain.order.repository.custom;

import com.gsm._8th.class4.backend.task12.domain.order.domain.constant.OrderStatus;
import com.gsm._8th.class4.backend.task12.domain.order.entity.OrderJpaEntity;
import java.util.List;

public interface OrderJpaRepositoryCustom {
    List<OrderJpaEntity> searchOrder(OrderStatus orderStatus, Long minPrice, Long maxPrice, Long memberId);
}