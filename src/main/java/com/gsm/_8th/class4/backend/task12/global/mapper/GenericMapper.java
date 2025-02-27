package com.gsm._8th.class4.backend.task12.global.mapper;

public interface GenericMapper<ENTITY, DOMAIN> {
    ENTITY toEntity(DOMAIN domain);

    DOMAIN toDomain(ENTITY entity);
}