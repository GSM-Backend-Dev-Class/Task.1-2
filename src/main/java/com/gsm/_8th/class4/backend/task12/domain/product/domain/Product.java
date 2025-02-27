package com.gsm._8th.class4.backend.task12.domain.product.domain;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Product {
    public final Long id;
    public final String productName;
    public final Integer price;
    public final Integer stock;
    public final String description;
    public final String imageUrl;
}
