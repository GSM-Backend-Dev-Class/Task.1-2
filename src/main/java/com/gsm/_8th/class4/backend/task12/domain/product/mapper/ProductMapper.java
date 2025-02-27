package com.gsm._8th.class4.backend.task12.domain.product.mapper;

import com.gsm._8th.class4.backend.task12.domain.product.domain.Product;
import com.gsm._8th.class4.backend.task12.domain.product.entity.ProductJpaEntity;
import com.gsm._8th.class4.backend.task12.global.mapper.GenericMapper;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper implements GenericMapper<ProductJpaEntity, Product> {
    @Override
    public ProductJpaEntity toEntity(Product domain) {
        return ProductJpaEntity.builder()
                .id(domain.getId())
                .productName(domain.getProductName())
                .price(domain.getPrice())
                .stock(domain.getStock())
                .description(domain.getDescription())
                .imageUrl(domain.getImageUrl())
                .build();
    }

    @Override
    public Product toDomain(ProductJpaEntity jpaEntity) {
        return Product.builder()
                .id(jpaEntity.getId())
                .productName(jpaEntity.getProductName())
                .price(jpaEntity.getPrice())
                .stock(jpaEntity.getStock())
                .description(jpaEntity.getDescription())
                .imageUrl(jpaEntity.getImageUrl())
                .build();
    }
}