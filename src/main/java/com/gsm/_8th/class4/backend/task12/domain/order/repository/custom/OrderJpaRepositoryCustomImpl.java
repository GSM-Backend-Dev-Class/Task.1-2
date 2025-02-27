package com.gsm._8th.class4.backend.task12.domain.order.repository.custom;

import com.gsm._8th.class4.backend.task12.domain.order.domain.constant.OrderStatus;
import com.gsm._8th.class4.backend.task12.domain.order.entity.OrderJpaEntity;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.gsm._8th.class4.backend.task12.domain.order.entity.QOrderJpaEntity.orderJpaEntity;

@Repository
@RequiredArgsConstructor
public class OrderJpaRepositoryCustomImpl implements OrderJpaRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<OrderJpaEntity> searchOrder(OrderStatus orderStatus, Long minPrice, Long maxPrice, Long memberId) {
        return queryFactory
                .selectFrom(orderJpaEntity)
                .where(
                        eqOrderStatus(orderStatus),
                        betweenTotalPrice(minPrice, maxPrice),
                        eqMemberId(memberId)
                )
                .fetch();
    }

    private BooleanExpression eqOrderStatus(OrderStatus orderStatus) {
        return orderStatus != null ? orderJpaEntity.orderStatus.eq(orderStatus) : null;
    }

    private BooleanExpression eqMemberId(Long memberId) {
        return memberId != null ? orderJpaEntity.member.id.eq(memberId) : null;
    }

    private BooleanExpression betweenTotalPrice(Long minPrice, Long maxPrice) {
        return (minPrice != null && maxPrice != null) ? orderJpaEntity.totalPrice.between(minPrice, maxPrice) : null;
    }
}