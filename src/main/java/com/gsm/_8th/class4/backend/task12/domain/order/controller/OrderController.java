package com.gsm._8th.class4.backend.task12.domain.order.controller;

import com.gsm._8th.class4.backend.task12.domain.order.domain.Order;
import com.gsm._8th.class4.backend.task12.domain.order.domain.constant.OrderStatus;
import com.gsm._8th.class4.backend.task12.domain.order.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/order")
public class OrderController {

    private final OrderService orderService;

    @GetMapping("/{orderId}")
    public Order getOrder(@PathVariable Long orderId) {
        return orderService.getOrder(orderId);
    }

    @GetMapping("/search")
    public List<Order> searchOrder(
            @RequestParam(required = false) OrderStatus orderStatus,
            @RequestParam(required = false) Long minPrice,
            @RequestParam(required = false) Long maxPrice,
            @RequestParam(required = false) Long memberId
    ) {
        return orderService.searchOrder(orderStatus, minPrice, maxPrice, memberId);
    }

}