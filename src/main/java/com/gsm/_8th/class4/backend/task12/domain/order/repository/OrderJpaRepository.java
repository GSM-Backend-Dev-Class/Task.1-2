package com.gsm._8th.class4.backend.task12.domain.order.repository;

import com.gsm._8th.class4.backend.task12.domain.order.entity.OrderJpaEntity;
import com.gsm._8th.class4.backend.task12.domain.order.repository.custom.OrderJpaRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderJpaRepository extends JpaRepository<OrderJpaEntity, Long>, OrderJpaRepositoryCustom {
}