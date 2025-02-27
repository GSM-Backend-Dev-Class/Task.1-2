package com.gsm._8th.class4.backend.task12.domain.product.entity;

import com.gsm._8th.class4.backend.task12.global.entity.BaseIdEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "product")
@NoArgsConstructor
public class ProductJpaEntity extends BaseIdEntity {

    @Column(name = "product_name", nullable = false)
    private String productName;

    @Column(name = "price", nullable = false)
    private Integer price;

    @Column(name = "stock", nullable = false)
    private Integer stock;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "image_url", nullable = false)
    private String imageUrl;

    @Builder
    public ProductJpaEntity(Long id, String productName, Integer price, Integer stock, String description, String imageUrl) {
        this.id = id;
        this.productName = productName;
        this.price = price;
        this.stock = stock;
        this.description = description;
        this.imageUrl = imageUrl;
    }
}