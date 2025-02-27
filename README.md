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