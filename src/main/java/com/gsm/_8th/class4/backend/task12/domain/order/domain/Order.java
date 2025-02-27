package com.gsm._8th.class4.backend.task12.domain.order.domain;

import com.gsm._8th.class4.backend.task12.domain.member.domain.Member;
import com.gsm._8th.class4.backend.task12.domain.order.domain.constant.OrderStatus;
import com.gsm._8th.class4.backend.task12.domain.product.domain.Product;
import lombok.Builder;
import lombok.Getter;

import java.util.List;
import java.util.UUID;

@Getter
@Builder
public class Order {
    private Long id;
    private Member member;
    private UUID orderNumber;
    private String address;
    private List<Product> orderItems;
    private Long totalPrice;
    private OrderStatus orderStatus;
}