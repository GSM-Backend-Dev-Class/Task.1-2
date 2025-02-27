# 과제 1-2(2025/03/02~2025/03/07)

[https://github.com/GSM-Backend-Dev-Class/Task.1-2](https://github.com/GSM-Backend-Dev-Class/Task.1-2)

## 💡요약

- 애플리케이션에 JWT 인증/인가를 적용하는 것이 목표입니다
- 사전에 구현되어 있는 2개의 API에 알맞은 인증 로직을 적용하여주세요
- 사전에 정의되어 있는 클래스,인터페이스를 활용하여 회원가입,로그인,토큰 재발급 API를 구현하여주세요

## ✅ 요구사항

- **`/api/v1/order/{orderId}`,`/api/v1/order/search`** API가 이미 구현되어 있습니다.해당 엔드포인트에 Spring Security와 JJWT 라이브러리를 이용하여 JWT 인증/인가를 구현하여주세요
- 아래 API 명세서에 나와있는 대로 추가적으로 API를 구현하여주세요
- 비밀번호를 저장하거나 비교할 때 Bcrypt 암호화를 사용하여주세요
- 리프레시 토큰을 이용하여  토큰을 재발급 할 때는 **Refesh Token Rotation**를 적용하여주세요
- 애플리케이션 구조를 참고하여 최대한 일관성 있는 구조(기존 구조)로 만들어주세요
- 가능하다면 이미 생성되어 있는 인터페이스,클래스를 변경하지 말아주시고 **`global/security**` 패키지 내부와 **`domain/auth`** 패키지 내부만 수정하여주세요
- SecurityConfig 클래스는 임시로 생성된 클래스 입니다,반드시 내부 코드를 변경하여주세요
- 아래 쿼리문을 DB에 쿼리한 후 시작하여주세요`
```sql

create table member
(
    id           bigint auto_increment
        primary key,
    created_at   timestamp default CURRENT_TIMESTAMP not null,
    updated_at   timestamp default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP,
    address      varchar(255)                        not null,
    email        varchar(255)                        not null,
    is_adult     bit                                 not null,
    name         varchar(255)                        not null,
    password     varchar(255)                        not null,
    phone_number varchar(255)                        not null,
    role         enum ('ROLE_ADMIN', 'ROLE_USER')    not null
);
create table orders
(
    id           bigint auto_increment
        primary key,
    created_at   timestamp default CURRENT_TIMESTAMP                   not null,
    updated_at   timestamp default CURRENT_TIMESTAMP                   not null on update CURRENT_TIMESTAMP,
    address      varchar(255)                                          not null,
    order_number binary(16)                                            not null,
    order_status enum ('CANCELED', 'COMPLETED', 'ORDERED', 'SHIPPING') not null,
    total_price  bigint                                                not null,
    member_id    bigint                                                not null,
    constraint FK1
        foreign key (member_id) references `task-12`.member (id)
);

create table product
(
    id           bigint auto_increment
        primary key,
    created_at   timestamp default CURRENT_TIMESTAMP not null,
    updated_at   timestamp default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP,
    description  varchar(255)                        not null,
    image_url    varchar(255)                        not null,
    price        int                                 not null,
    product_name varchar(255)                        not null,
    stock        int                                 not null
);

create table orders_order_items
(
    order_jpa_entity_id bigint not null,
    order_items_id      bigint not null,
    constraint FK2
        foreign key (order_items_id) references product (id),
    constraint FK3
        foreign key (order_jpa_entity_id) references orders (id)
);


INSERT INTO member (id, name, phone_number, email, password, address, is_adult, role)
VALUES
    (1, '김철수', '010-1234-5678', 'chulsu@example.com', 'password123', '서울시 강남구', true, 'ROLE_USER'),
    (2, '이영희', '010-2345-6789', 'younghee@example.com', 'password123', '부산시 해운대구', true, 'ROLE_ADMIN'),
    (3, '박민수', '010-3456-7890', 'minsu@example.com', 'password123', '대구시 수성구', true, 'ROLE_USER'),
    (4, '최지현', '010-4567-8901', 'jihyun@example.com', 'password123', '인천시 남동구', true, 'ROLE_USER'),
    (5, '정우성', '010-5678-9012', 'woosung@example.com', 'password123', '광주시 서구', true, 'ROLE_ADMIN'),
    (6, '한가희', '010-6789-0123', 'gahee@example.com', 'password123', '대전시 유성구', false, 'ROLE_USER'),
    (7, '오세훈', '010-7890-1234', 'sehoon@example.com', 'password123', '울산시 남구', true, 'ROLE_USER'),
    (8, '강예진', '010-8901-2345', 'yejin@example.com', 'password123', '경기도 성남시', true, 'ROLE_USER');

INSERT INTO product (id, product_name, price, stock, description, image_url)
VALUES
    (1, '무선 이어폰', 99000, 50, '고음질 블루투스 무선 이어폰', 'https://example.com/earphone.jpg'),
    (2, '게이밍 마우스', 45000, 30, 'RGB 조명 기능이 있는 게이밍 마우스', 'https://example.com/mouse.jpg'),
    (3, '기계식 키보드', 120000, 20, '청축 기계식 키보드', 'https://example.com/keyboard.jpg'),
    (4, '스마트워치', 185000, 15, '건강 관리 기능이 포함된 스마트워치', 'https://example.com/smartwatch.jpg'),
    (5, '모니터 27인치', 300000, 10, '고해상도 IPS 패널 모니터', 'https://example.com/monitor.jpg'),
    (6, '외장 SSD 1TB', 160000, 25, '초고속 외장 SSD 1TB', 'https://example.com/ssd.jpg'),
    (7, '무선 충전기', 35000, 40, '고속 충전을 지원하는 무선 충전기', 'https://example.com/charger.jpg'),
    (8, '노이즈 캔슬링 헤드폰', 250000, 12, '액티브 노이즈 캔슬링 기능 탑재', 'https://example.com/headphone.jpg');

INSERT INTO orders (id, member_id, order_number, address, total_price, order_status)
VALUES
    (1, 1, UUID_TO_BIN(UUID()), '서울시 강남구', 120000, 'ORDERED'),
    (2, 2, UUID_TO_BIN(UUID()), '부산시 해운대구', 250000, 'COMPLETED'),
    (3, 3, UUID_TO_BIN(UUID()), '대구시 수성구', 175000, 'SHIPPING'),
    (4, 4, UUID_TO_BIN(UUID()), '인천시 남동구', 98000, 'CANCELED'),
    (5, 5, UUID_TO_BIN(UUID()), '광주시 서구', 220000, 'ORDERED'),
    (6, 6, UUID_TO_BIN(UUID()), '대전시 유성구', 320000, 'COMPLETED'),
    (7, 7, UUID_TO_BIN(UUID()), '울산시 남구', 145000, 'ORDERED'),
    (8, 8, UUID_TO_BIN(UUID()), '경기도 성남시', 210000, 'SHIPPING');

INSERT INTO orders_order_items (order_jpa_entity_id, order_items_id)
VALUES
    (1, 1), (1, 3), (1, 5),
    (2, 2), (2, 4),
    (3, 3), (3, 6), (3, 7),
    (4, 1), (4, 5),
    (5, 2), (5, 6),
    (6, 4), (6, 7),
    (7, 3), (7, 8),
    (8, 5), (8, 6), (8, 8);

```