package com.gsm._8th.class4.backend.task12.domain.order.mapper;

import com.gsm._8th.class4.backend.task12.domain.member.mapper.MemberMapper;
import com.gsm._8th.class4.backend.task12.domain.order.domain.Order;
import com.gsm._8th.class4.backend.task12.domain.order.entity.OrderJpaEntity;
import com.gsm._8th.class4.backend.task12.domain.product.mapper.ProductMapper;
import com.gsm._8th.class4.backend.task12.global.mapper.GenericMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class OrderMapper implements GenericMapper<OrderJpaEntity, Order> {

    private final MemberMapper memberMapper;
    private final ProductMapper productMapper;

    @Override
    public OrderJpaEntity toEntity(Order domain) {
        return OrderJpaEntity.builder()
                .id(domain.getId())
                .member(memberMapper.toEntity(domain.getMember()))
                .orderNumber(domain.getOrderNumber())
                .address(domain.getAddress())
                .orderItems(domain.getOrderItems().stream().map(productMapper::toEntity).collect(Collectors.toList()))
                .totalPrice(domain.getTotalPrice())
                .orderStatus(domain.getOrderStatus())
                .build();
    }

    @Override
    public Order toDomain(OrderJpaEntity jpaEntity) {
        return Order.builder()
                .id(jpaEntity.getId())
                .member(memberMapper.toDomain(jpaEntity.getMember()))
                .orderNumber(jpaEntity.getOrderNumber())
                .address(jpaEntity.getAddress())
                .orderItems(jpaEntity.getOrderItems().stream().map(productMapper::toDomain).collect(Collectors.toList()))
                .totalPrice(jpaEntity.getTotalPrice())
                .orderStatus(jpaEntity.getOrderStatus())
                .build();
    }
}