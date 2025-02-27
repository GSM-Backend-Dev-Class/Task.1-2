package com.gsm._8th.class4.backend.task12.domain.order.entity;

import com.gsm._8th.class4.backend.task12.domain.member.entity.MemberJpaEntity;
import com.gsm._8th.class4.backend.task12.domain.order.domain.constant.OrderStatus;
import com.gsm._8th.class4.backend.task12.domain.product.entity.ProductJpaEntity;
import com.gsm._8th.class4.backend.task12.global.entity.BaseIdEntity;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UuidGenerator;

import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Table(name = "orders")
@NoArgsConstructor
public class OrderJpaEntity extends BaseIdEntity {
    @JoinColumn(name = "member_id", nullable = false)
    @ManyToOne
    private MemberJpaEntity member;

    @Column(nullable = false)
    @UuidGenerator
    private UUID orderNumber;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false, name = "order_items")
    @OneToMany
    private List<ProductJpaEntity> orderItems;

    @Column(name = "total_price", nullable = false)
    private Long totalPrice;

    @Column(name = "order_status", nullable = false)
    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    @Builder
    public OrderJpaEntity(Long id, MemberJpaEntity member, UUID orderNumber, String address, List<ProductJpaEntity> orderItems, Long totalPrice, OrderStatus orderStatus) {
        this.id = id;
        this.member = member;
        this.orderNumber = orderNumber;
        this.address = address;
        this.orderItems = orderItems;
        this.totalPrice = totalPrice;
        this.orderStatus = orderStatus;
    }
}