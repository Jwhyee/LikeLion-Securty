## ๐ฆ [๋ฉ์ฌ] - JWT ์ฐ๋ ํ๋ก์ ํธ
- ๊ฐ์ ๋ฆฌํฌ์งํ ๋ฆฌ : [๋งํฌ](https://github.com/jhs512/app_2022_10_05)
- ์์ ์๋ฃ : [๋งํฌ](https://wiken.io/ken/10698)

## ํน์  ํด๋์ค์ ๋ํ ์ค๋ช ๐ฌ
### `RsData` ํด๋์ค
> `RestController`์์ Success/Fail์ ๋ํ ์ฌ๋ถ๋ฅผ ๋ฐํํด์ฃผ๊ธฐ ์ํ ํด๋์ค
```java
public class RsData<T> {
    private String resultCode;
    private String msg;
    private T data;

    public static <T> RsData<T> of(String resultCode, String msg) {
        return new RsData<>(resultCode, msg, null);
    }
}
```
### `resultCode`, `msg`์ ์ฌ์ฉ ์ด์ 
- ์๋ ์์์ ๊ฐ์ด ์ฑ๊ณต๊ณผ ์คํจ์ ๋ํด ๋ ์์ธํ ์ ๋ณด๋ฅผ ์ ๊ณตํ  ์ ์๋ค.
  - ์ฑ๊ณต : ์ ์ ๋ก๊ทธ์ธ || ํด๋จผํ์(์ผ๋ถ ๊ธฐ๋ฅ ์ฌ์ฉ ๊ฐ๋ฅ)
  - ์คํจ : ์์ด๋ ๋ถ์ผ์น || ๋น๋ฐ๋ฒํธ ๋ถ์ผ์น || ์ผ์ ์ค์ง || ์๊ตฌ ์ ์ง

### `data`์ ์ฌ์ฉ ์ด์ 
> GET /articles
- ์ ์์ฒญ์ด ๋ค์ด์์ ๋ `articles`์ ์ถ๋ ฅ์ ํด์ค `data`๋ฅผ ๋ด์์ ๋ณด๋ด์ค ์ ์๋ค.

---

### `Util` ํด๋์ค
> `Json`, `HTTP`๊ณผ ๊ด๋ จ๋ ์ฒ๋ฆฌ๋ฅผ ํด์ฃผ๋ ํด๋์ค 

### `Generic` ์ฌ์ฉ
```java
// Util.spring
public static <T> ResponseEntity<RsData> responseEntityOf(RsData<T> rsData) {
    return responseEntityOf(rsData, null);
}
```
- ๋ฐ์ดํฐ๋ฅผ ํ์ ์ ์ผ๋ก ์ง์ ํด์ ์ฌ์ฉํด์ฃผ๊ธฐ ์ํด ์ฌ์ฉ

### ๊ฐ๋ณ์ธ์ ์ฌ์ฉ
```java
// Util.json
public static <K, V> Map<K, V> mapOf(Object... args) {
    ...
}
```
```json
{
    "resultCode": "S-1",
    "msg": "๋ก๊ทธ์ธ ์ฑ๊ณต, Access Token์ ๋ฐ๊ธํฉ๋๋ค.",
    "data": {
        "Authentication": "JWT_Access_Token",
        "username": "abc123"
    },
    "success": true,
    "fail": false
}
```
- ์์ ๊ฐ์ด `data`์๋ ์ฌ๋ฌ ์ ๋ณด๊ฐ ๋ค์ด๊ฐ ์ ์์ด์ผํ๋ค.
- ๋๋ฌธ์ ๊ฐ๋ณ์ธ์๋ฅผ ์ฌ์ฉํด ๊ธธ์ด์ ์ ํ์ ๋์ง ์๊ณ , ์ฌ๋ฌ ์ ๋ณด๋ฅผ ์ถ๊ฐํด์ค๋ค.

---

## ํน์  ๊ธฐ์ ์ ๋ํ ์ค๋ช ๐ฌ

### REST API ์ฌ์ฉ
> ์์์ ์ด๋ฆ(์์์ ํํ)์ผ๋ก ๊ตฌ๋ถํ์ฌ ํด๋น ์์์ ์ํ(์ ๋ณด)๋ฅผ ์ฃผ๊ณ  ๋ฐ๋ ๋ชจ๋  ๊ฒ์ ์๋ฏธ
### ๊ธฐ์กด ๋ฐฉ์
- ๊ฒ์๋ฌผ ๋ชฉ๋ก : `GET` http://localhost:8080/article/list
- ๊ฒ์๋ฌผ ๋ฑ๋ก(ํผ) : `GET` http://localhost:8080/article/write
- ๊ฒ์๋ฌผ ๋ฑ๋ก : `POST` http://localhost:8080/article/write
- ๊ฒ์๋ฌผ ๋จ๊ฑด ์กฐํ : `GET` http://localhost:8080/article/1
- ๊ฒ์๋ฌผ ์์ (ํผ) : `GET` http://localhost:8080/article/1/modify
- ๊ฒ์๋ฌผ ์์  : `POST` http://localhost:8080/article/1/modify
- ๊ฒ์๋ฌผ ์ญ์  : `GET` http://localhost:8080/article/1/delete

### REST ๋ฐฉ์
- ๊ฒ์๋ฌผ ๋ชฉ๋ก : `GET` http://localhost:8080/articles
- ๊ฒ์๋ฌผ ๋ฑ๋ก : `POST` http://localhost:8080/articles
- ๊ฒ์๋ฌผ ๋จ๊ฑด ์กฐํ : `GET` http://localhost:8080/article/1
- ๊ฒ์๋ฌผ ์์  : `PATCH` http://localhost:8080/article/1
- ๊ฒ์๋ฌผ ์ญ์  : `DELETE` http://localhost:8080/article/1

---

## Spring Doc - Swagger
> ์ฃผ๋ก `REST API`๋ฅผ ๊ตฌ์ถ ํ์ ๋, ํ๋ก ํธ ๊ฐ๋ฐ์์๊ฒ ๋ณด์ฌ์ฃผ๊ธฐ ์ํด ์ฌ์ฉํ๋ค.<br>
> ๊ธฐ๋ณธ์ ์ผ๋ก ๋ชจ๋์๊ฒ ๋ณด์ฌ์ง๊ธฐ ๋๋ฌธ์ ์์ด๋, ๋น๋ฐ๋ฒํธ๋ฅผ ์ง์ ํด์ฃผ๋ ๊ฒ์ด ์ข๋ค.

### ์์กด์ฑ ์ถ๊ฐ
```java
implementation 'org.springdoc:springdoc-openapi-ui:1.6.11'
```

### Config ์ค์ 
- Security
```java
.authorizeRequests(
        authorizeRequests -> authorizeRequests
                .antMatchers("/v3/api-docs/**", "/swagger-ui/**", "/swagger-ui.html").permitAll()
)
```
> `Swagger`์์ ํ์ธ์ ํด์ผํ๊ธฐ ๋๋ฌธ์ ๊ด๋ จ๋ URL์ `permitAll`์ ํด์ค๋ค.<br>
> ๋จ, ์ค์  ์๋น์ค๋ฅผ ์ด์ํ  ์์๋ ๋ณด์์ ๊ฑธ์ด์ฃผ๋ ๊ฒ์ด ์ข๋ค.

- Spring Doc
```java
@Bean
public OpenAPI springShopOpenAPI() {
    return new OpenAPI()
            .info(new Info().title("SpringShop API")
                    .description("Spring shop sample application")
                    .version("v0.0.1")
                    .license(new License().name("Apache 2.0").url("http://springdoc.org")))
            .externalDocs(new ExternalDocumentation()
                    .description("SpringShop Wiki Documentation")
                    .url("https://springshop.wiki.github.org/docs"));
}
```
> `Swagger`์ ์์ฑํด๋์ ์ค๋ช๊ณผ ์ ๋ณด๋ฅผ ์์ฑํ๋ค.

---

### `JWT` ์ฌ์ฉ
> `Json` ํฌ๋งท์ ์ด์ฉํ์ฌ ์ฌ์ฉ์์ ๋ํ ์์ฑ์ ์ ์ฅํ๋ Claim ๊ธฐ๋ฐ์ `Web Token`

### Spring Security ๋ก๊ทธ์ธ
- ํ์ ์ ๋ณด๊ฐ ์ผ์นํ๋์ง ํ์ธ
- ์ธ์ ๋ณ์ ์์ฑ -> ์ํ๋ฆฌํฐ ๋ฐฉ์
> `@AuthenticationPrincipal MemberContext memberContext`๋ ์ธ์์์ ๋ฐ์ดํฐ๋ฅผ ๊บผ๋ด์ด

### JWT ๋ก๊ทธ์ธ
> POST /member/login
- ํ์ ์ ๋ณด๊ฐ ์ผ์นํ๋์ง ํ์ธ
- `JWT` ํ์์ `accessToken`์ ๋ฐ๊ธ
> `header`์ `Authorization : Bearer accessToken`๋ก ์ ์ฅ๋
> ์ดํ ์ธ์  ์ ๋ณด๋ฅผ ์ถ๊ฐํด์ค์ผ์ง `Security`์์ ์ ๊ณตํ๋ ์ด๋ธํ์ด์ ์ฌ์ฉ ๊ฐ๋ฅ

## JWT์ ๋ฌธ์ ์ ๊ณผ ํด๊ฒฐ ๋ฐฉ์
### ๋ฌธ์  1. ํ๊ธฐ๊ฐ ๋ถ๊ฐ๋ฅํ๊ณ , ํ๊ณ  ์๋ ์ ๋ณด๋ฅผ ๋ณ๊ฒฝํ  ์ ์๋ค.
> ๋๋ค์ ๋๋ ์ด๋ฉ์ผ ๊ฐ์ ์ ๋ณด๊ฐ ์ ์ฅ๋์ด ์๊ณ , ํ ํฐ ๋ฐ๊ธ ์ดํ์ ์ฌ์ฉ์๊ฐ ์ด๋ฉ์ผ, ๋๋ค์์ ๋ณ๊ฒฝํ๋ค๋ฉด ์ ๋ณด ๋ถ์ผ์น๊ฐ ๋ฐ์

### ๋ฌธ์  2. ํ ํฐ ๋ง๋ฃ ๊ธฐ๊ฐ์ด ๊ธธ ๋, ํ์ทจ๋ฅผ ๋นํ๋ค๋ฉด ์์ ์ธ ์ ์๋ค. 
> ๋ง์ฝ ํ ํฐ์ ๋ง๋ฃ ๊ธฐ๊ฐ์ 90์ผ๋ก ์ง์ ํ ๋ค ํ์ทจ๋ฅผ ๋นํ๋ค๋ฉด, JWT ํ ํฐ์ ๊ตฌ์กฐ์ ํ๊ธฐ๊ฐ ๋ถ๊ฐ๋ฅํ๋ค.

### ๋ฐฉ์ 1. ๋ง๋ฃ ๊ธฐ๊ฐ์ ์งง๊ฒ 5๋ถ ์ ๋๋ก ์ก๋๋ค.
```java
// MemberService.java
public String generateAccessToken(Member member) {
    return jwtProvider.generateAccessToken(member.getAccessTokenClaims(), 60 * 5);
}
```
> 5๋ถ๋ง๋ค 1๋ฒ์ฉ ๋ก๊ทธ์ธ์ ํด์ค์ผํ๋ ๋ฒ๊ฑฐ๋ก์์ด ์์ง๋ง, `Refresh Token`์ ์ฌ์ฉํด์ ํด๊ฒฐํ๋ค.<br>
> ํด๋ผ์ด์ธํธ์์๋ `Access Token`๊ณผ `Refresh Token`์ ๊ฐ์ง๊ณ  ์์ด์ผํ๋ค.
- `Refresh Token`
  - JWT ํ์์ผ ํ์๋ ์์ผ๋ฉฐ, DB์์ ๊ด๋ฆฌ๋๋ ์์ ํค(๋น๋ฐ๋ฒํธ)๋ผ๊ณ  ์๊ฐํ์.
  - ํด๋น Refresh Token์ผ๋ก ์ฌ๋ก๊ทธ์ธ ์ฆ, Access Token์ ๋ฐ๊ธ ๋ฐ๋๊ฒ ๊ฐ๋ฅํ๊ธฐ ๋๋ฌธ์ด๋ค.

### JWT Access Token ํ์ดํธ๋ฆฌ์คํธ ๋ฐฉ์
> ๋ธ๋ ๋ฆฌ์คํธ โก๏ธ ๋ฌธ์ ๊ฐ ๋๋ ์ ๋ค์ ๊ธฐ์ต <br>
> ํ์ดํธ ๋ฆฌ์คํธ โก๏ธ ๋ด๊ฐ ์ธ์ ํ ์ ๋ค๋ง ๊ธฐ์ต
- ์ต์ด์ ๋ฐ๊ธ๋ ์ก์ธ์ค ํ ํฐ์ ๊ธฐ๋ก
- 2๋ฒ์งธ ์ดํ๋ก ์์ฒญ๋ ๋ก๊ทธ์ธ์๋ ๊ธฐ์กด ์์ธ์ค ํ ํฐ์ ๋ฐํ
- ์๋ฒ์์ ๋งค ์์ฒญ๋ง๋ค, ์์ธ์ค ํ ํฐ์ด ์ ํจํ์ง ์ฒดํฌํ๋ ๊ฒ์ ๋์ด์, DB์ ์ ์ฅ๋์ด ์๋ ์ธ์ฆ๋ ์์ธ์ค ํ ํฐ์ธ์ง๋ ํ์ธ
---

## Cache
> `Spring Boot`๋ ์ธ๋ถ ์บ์๋ฅผ ์ง์ ํ์ง ์์ผ๋ฉด `Map`๊ณผ ๊ฐ์ ๋ด๋ถ ๋ฉ๋ชจ๋ฆฌ ์บ์๋ฅผ ์ฌ์ฉํ๋ค.
> `Application`์์ `@EnableCaching` ์ด๋ธํ์ด์์ ์ฌ์ฉํ๋ค.

### Setting
`SpringBoot`์์ ์บ์๋ฅผ ์ฌ์ฉํ๊ธฐ ์ํด์๋ ์๋์ ๊ฐ์ด ๊ฐ๋จํ ์์ ํ๋๋ง ์งํํ๋ฉด ๋๋ค.
```java
// Application.java
@...
@EnableCaching
public class SecurtyExamApplication {
  ...
}
```

### ์บ์ ์์ฑ ์์ 
```java
// MemberService
@Cacheable("key1")
public int getCachedInt() {
    System.out.println("ํธ์ถ๋จ");
    return 5;
}
```

```java
// CacheTests
@Test
@DisplayName("์บ์ ์ฌ์ฉ")
void t1() {
    int rs = memberService.getCachedInt();
    assertThat(rs).isGreaterThan(0);
    System.out.println("rs = " + rs);
    
    rs = memberService.getCachedInt();
    assertThat(rs).isGreaterThan(0);
    System.out.println("rs = " + rs);
}
```
> ์์ ๊ฐ์ด `@Cachable("keyName")`์ ์ฌ์ฉํ๋ฉด ์ฒ์ ํธ์ถ์ด ๋  ๋ `keyName`์ `return` ๊ฐ์ ์ ์ฅํ๊ณ ,<br>
> ๋ค์ ํธ์ถ์ด ๋  ๋๋ถํฐ๋ ํจ์๋ฅผ ๋ค์ ์คํํ๋ ๊ฒ์ด ์๋ ์ ์ฅ๋ `keyName`์ `value`๋ฅผ ๋ถ๋ฌ์ค๊ฒ ๋๋ค.

### ์บ์ ์ญ์  ์์ 
```java
@CacheEvict("key1")
public void deleteCachedInt() {
    System.out.println("์ญ์ ๋จ");
}
```
```java
@Test
@DisplayName("์บ์ ์ฌ์ฉ, ์ญ์ , ์์ฑ")
void t2() {
    // ์บ์ ์์ฑ(rs = 5)
    int rs = memberService.getCachedInt();
    assertThat(rs).isGreaterThan(0);
    System.out.println("rs = " + rs);

    // ์บ์ ์ฌ์ฉ(rs = 5)
    rs = memberService.getCachedInt();
    assertThat(rs).isGreaterThan(0);
    System.out.println("rs = " + rs);

    // ์บ์ ์ญ์ 
    memberService.deleteCachedInt();

    // ์บ์ ์์ฑ(rs = 5)
    rs = memberService.getCachedInt();
    assertThat(rs).isGreaterThan(0);
    System.out.println("rs = " + rs);
}
```

### ์บ์ ์์  ์์ 
```java
@CachePut("key1")
public int updateCachedInt() {
    System.out.println("์์ ๋จ");
    return 10;
}
```
```java
@Test
@DisplayName("์บ์ ์ฌ์ฉ, ์ญ์ , ์์ฑ")
void t2() {
    // ์บ์ ์์ฑ(rs = 5)
    int rs = memberService.getCachedInt();
    assertThat(rs).isGreaterThan(0);
    System.out.println("rs = " + rs);
    
    // ์บ์ ์ฌ์ฉ(rs = 5)
    rs = memberService.getCachedInt();
    assertThat(rs).isGreaterThan(0);
    System.out.println("rs = " + rs);

    // ์บ์ ์์ (rs = 10)
    rs = memberService.updateCachedInt();
    assertThat(rs).isGreaterThan(0);
    System.out.println("rs = " + rs);

    // ์บ์ ์ฌ์ฉ(rs = 10)
    rs = memberService.getCachedInt();
    assertThat(rs).isGreaterThan(0);
    System.out.println("rs = " + rs);
}
```
---
## Redis ์ฐ๋
> Redis๋ key : value๋ก ์ด๋ฃจ์ด์ง NoSQL์ด๋ค.

> SpringBoot ๋ด๋ถ ์บ์ : ์๋ฐ ์๋ฃ๊ตฌ์กฐ 100% ํ์ฉ ๊ฐ๋ฅ<br>
> SpringBoot Redis ์บ์ : List, Map์ ์ ์ธํ ์ฌ์ฉ์ ์ ์ ๊ฐ์ฒด๋ ์ฌ์ ์ ํด์ฃผ์ด์ผ ํจ

### Redis ์ค์น
```bash
brew install redis
```

### Redis ์คํ ๋ฐ ์ฌ์ฉ
```bash
brew services start redis
redis-cli
```

### Spring ํ๋ก์ ํธ์ ์ฐ๋
```bash
# build.gradle
implementation 'org.springframework.boot:spring-boot-starter-cache'
implementation 'org.springframework.boot:spring-boot-starter-data-redis'
```
```yaml
# application.yml
spring:
  cache:
    type: redis
  redis:
    host: 127.0.0.1
    port: 6379
```