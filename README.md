## 🦁 [멋사] - JWT 연동 프로젝트
- 강의 리포지토리 : [링크](https://github.com/jhs512/app_2022_10_05)
- 수업 자료 : [링크](https://wiken.io/ken/10698)

## 특정 클래스에 대한 설명 💬
### `RsData` 클래스
> `RestController`에서 Success/Fail에 대한 여부를 반환해주기 위한 클래스
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
### `resultCode`, `msg`의 사용 이유
- 아래 예시와 같이 성공과 실패에 대해 더 자세한 정보를 제공할 수 있다.
  - 성공 : 정상 로그인 || 휴먼회원(일부 기능 사용 가능)
  - 실패 : 아이디 불일치 || 비밀번호 불일치 || 일시 중지 || 영구 정지

### `data`의 사용 이유
> GET /articles
- 위 요청이 들어왔을 때 `articles`에 출력을 해줄 `data`를 담아서 보내줄 수 있다.

---

### `Util` 클래스
> `Json`, `HTTP`과 관련된 처리를 해주는 클래스 

### `Generic` 사용
```java
// Util.spring
public static <T> ResponseEntity<RsData> responseEntityOf(RsData<T> rsData) {
    return responseEntityOf(rsData, null);
}
```
- 데이터를 한정적으로 지정해서 사용해주기 위해 사용

### 가변인자 사용
```java
// Util.json
public static <K, V> Map<K, V> mapOf(Object... args) {
    ...
}
```
```json
{
    "resultCode": "S-1",
    "msg": "로그인 성공, Access Token을 발급합니다.",
    "data": {
        "Authentication": "JWT_Access_Token",
        "username": "abc123"
    },
    "success": true,
    "fail": false
}
```
- 위와 같이 `data`에는 여러 정보가 들어갈 수 있어야한다.
- 때문에 가변인자를 사용해 길이의 제한을 두지 않고, 여러 정보를 추가해준다.

---

## 특정 기술에 대한 설명 💬

### REST API 사용
> 자원을 이름(자원의 표현)으로 구분하여 해당 자원의 상태(정보)를 주고 받는 모든 것을 의미
### 기존 방식
- 게시물 목록 : `GET` http://localhost:8080/article/list
- 게시물 등록(폼) : `GET` http://localhost:8080/article/write
- 게시물 등록 : `POST` http://localhost:8080/article/write
- 게시물 단건 조회 : `GET` http://localhost:8080/article/1
- 게시물 수정(폼) : `GET` http://localhost:8080/article/1/modify
- 게시물 수정 : `POST` http://localhost:8080/article/1/modify
- 게시물 삭제 : `GET` http://localhost:8080/article/1/delete

### REST 방식
- 게시물 목록 : `GET` http://localhost:8080/articles
- 게시물 등록 : `POST` http://localhost:8080/articles
- 게시물 단건 조회 : `GET` http://localhost:8080/article/1
- 게시물 수정 : `PATCH` http://localhost:8080/article/1
- 게시물 삭제 : `DELETE` http://localhost:8080/article/1

---

## Spring Doc - Swagger
> 주로 `REST API`를 구축 했을 때, 프론트 개발자에게 보여주기 위해 사용한다.<br>
> 기본적으로 모두에게 보여지기 때문에 아이디, 비밀번호를 지정해주는 것이 좋다.

### 의존성 추가
```java
implementation 'org.springdoc:springdoc-openapi-ui:1.6.11'
```

### Config 설정
- Security
```java
.authorizeRequests(
        authorizeRequests -> authorizeRequests
                .antMatchers("/v3/api-docs/**", "/swagger-ui/**", "/swagger-ui.html").permitAll()
)
```
> `Swagger`에서 확인을 해야하기 때문에 관련된 URL은 `permitAll`을 해준다.<br>
> 단, 실제 서비스를 운영할 시에는 보안을 걸어주는 것이 좋다.

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
> `Swagger`에 작성해놓을 설명과 정보를 작성한다.

---

### `JWT` 사용
> `Json` 포맷을 이용하여 사용자에 대한 속성을 저장하는 Claim 기반의 `Web Token`

### Spring Security 로그인
- 회원 정보가 일치하는지 확인
- 세션 변수 생성 -> 시큐리티 방식
> `@AuthenticationPrincipal MemberContext memberContext`는 세션에서 데이터를 꺼내옴

### JWT 로그인
> POST /member/login
- 회원 정보가 일치하는지 확인
- `JWT` 형식의 `accessToken`을 발급
> `header`에 `Authorization : Bearer accessToken`로 저장됌
> 이후 세선 정보를 추가해줘야지 `Security`에서 제공하는 어노테이션 사용 가능

## JWT의 문제점과 해결 방안
### 문제 1. 폐기가 불가능하고, 품고 있는 정보를 변경할 수 없다.
> 닉네임 또는 이메일 같은 정보가 저장되어 있고, 토큰 발급 이후에 사용자가 이메일, 닉네임을 변경했다면 정보 불일치가 발생

### 문제 2. 토큰 만료 기간이 길 때, 탈취를 당했다면 손을 쓸 수 없다. 
> 만약 토큰의 만료 기간을 90일로 지정한 뒤 탈취를 당했다면, JWT 토큰의 구조상 폐기가 불가능하다.

### 방안 1. 만료 기간을 짧게 5분 정도로 잡는다.
```java
// MemberService.java
public String generateAccessToken(Member member) {
    return jwtProvider.generateAccessToken(member.getAccessTokenClaims(), 60 * 5);
}
```
> 5분마다 1번씩 로그인을 해줘야하는 번거로움이 있지만, `Refresh Token`을 사용해서 해결한다.<br>
> 클라이언트에서는 `Access Token`과 `Refresh Token`을 가지고 있어야한다.
- `Refresh Token`
  - JWT 형식일 필요는 없으며, DB에서 관리되는 임시 키(비밀번호)라고 생각하자.
  - 해당 Refresh Token으로 재로그인 즉, Access Token을 발급 받는게 가능하기 때문이다.

### JWT Access Token 화이트리스트 방식
> 블랙 리스트 ➡️ 문제가 되는 애들을 기억 <br>
> 화이트 리스트 ➡️ 내가 인정한 애들만 기억
- 최초에 발급된 액세스 토큰을 기록
- 2번째 이후로 요청된 로그인에는 기존 엑세스 토큰을 반환
- 서버에서 매 요청마다, 엑세스 토큰이 유효한지 체크하는 것을 넘어서, DB에 저장되어 있는 인증된 엑세스 토큰인지도 확인
---

## Cache
> `Spring Boot`는 외부 캐시를 지정하지 않으면 `Map`과 같은 내부 메모리 캐시를 사용한다.
> `Application`에서 `@EnableCaching` 어노테이션을 사용한다.

### Setting
`SpringBoot`에서 캐시를 사용하기 위해서는 아래와 같이 간단한 작업 하나만 진행하면 된다.
```java
// Application.java
@...
@EnableCaching
public class SecurtyExamApplication {
  ...
}
```

### 캐시 생성 예제
```java
// MemberService
@Cacheable("key1")
public int getCachedInt() {
    System.out.println("호출됨");
    return 5;
}
```

```java
// CacheTests
@Test
@DisplayName("캐시 사용")
void t1() {
    int rs = memberService.getCachedInt();
    assertThat(rs).isGreaterThan(0);
    System.out.println("rs = " + rs);
    
    rs = memberService.getCachedInt();
    assertThat(rs).isGreaterThan(0);
    System.out.println("rs = " + rs);
}
```
> 위와 같이 `@Cachable("keyName")`을 사용하면 처음 호출이 될 때 `keyName`에 `return` 값을 저장하고,<br>
> 다음 호출이 될 때부터는 함수를 다시 실행하는 것이 아닌 저장된 `keyName`의 `value`를 불러오게 된다.

### 캐시 삭제 예제
```java
@CacheEvict("key1")
public void deleteCachedInt() {
    System.out.println("삭제됨");
}
```
```java
@Test
@DisplayName("캐시 사용, 삭제, 생성")
void t2() {
    // 캐시 생성(rs = 5)
    int rs = memberService.getCachedInt();
    assertThat(rs).isGreaterThan(0);
    System.out.println("rs = " + rs);

    // 캐시 사용(rs = 5)
    rs = memberService.getCachedInt();
    assertThat(rs).isGreaterThan(0);
    System.out.println("rs = " + rs);

    // 캐시 삭제
    memberService.deleteCachedInt();

    // 캐시 생성(rs = 5)
    rs = memberService.getCachedInt();
    assertThat(rs).isGreaterThan(0);
    System.out.println("rs = " + rs);
}
```

### 캐시 수정 예제
```java
@CachePut("key1")
public int updateCachedInt() {
    System.out.println("수정됨");
    return 10;
}
```
```java
@Test
@DisplayName("캐시 사용, 삭제, 생성")
void t2() {
    // 캐시 생성(rs = 5)
    int rs = memberService.getCachedInt();
    assertThat(rs).isGreaterThan(0);
    System.out.println("rs = " + rs);
    
    // 캐시 사용(rs = 5)
    rs = memberService.getCachedInt();
    assertThat(rs).isGreaterThan(0);
    System.out.println("rs = " + rs);

    // 캐시 수정(rs = 10)
    rs = memberService.updateCachedInt();
    assertThat(rs).isGreaterThan(0);
    System.out.println("rs = " + rs);

    // 캐시 사용(rs = 10)
    rs = memberService.getCachedInt();
    assertThat(rs).isGreaterThan(0);
    System.out.println("rs = " + rs);
}
```
---
## Redis 연동
> Redis는 key : value로 이루어진 NoSQL이다.

> SpringBoot 내부 캐시 : 자바 자료구조 100% 활용 가능<br>
> SpringBoot Redis 캐시 : List, Map을 제외한 사용자 정의 객체는 재정의 해주어야 함

### Redis 설치
```bash
brew install redis
```

### Redis 실행 및 사용
```bash
brew services start redis
redis-cli
```

### Spring 프로젝트와 연동
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