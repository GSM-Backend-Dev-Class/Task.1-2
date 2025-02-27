package com.gsm._8th.class4.backend.task12.domain.order.service;

import com.gsm._8th.class4.backend.task12.domain.order.domain.Order;
import com.gsm._8th.class4.backend.task12.domain.order.domain.constant.OrderStatus;
import com.gsm._8th.class4.backend.task12.domain.order.exception.OrderNotFoundException;
import com.gsm._8th.class4.backend.task12.domain.order.mapper.OrderMapper;
import com.gsm._8th.class4.backend.task12.domain.order.repository.OrderJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderJpaRepository orderJpaRepository;
    private final OrderMapper orderMapper;

    public Order getOrder(Long orderId) {
        return orderJpaRepository.findById(orderId)
                .map(orderMapper::toDomain)
                .orElseThrow(OrderNotFoundException::new);
    }

    public List<Order> searchOrder(OrderStatus orderStatus, Long minPrice, Long maxPrice, Long memberId) {
        return orderJpaRepository.searchOrder(orderStatus, minPrice, maxPrice, memberId)
                .stream()
                .map(orderMapper::toDomain)
                .toList();
    }
}
