## 멋쟁이 사자처럼
### JWT 연동 프로젝트
- 강의 리포지토리 : [링크](https://github.com/jhs512/app_2022_10_05)
- 수업 자료 : [링크](https://wiken.io/ken/10698)

## `RsData` 클래스
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

## `Util` 클래스
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

## `JWT` 사용
> POST /member/login

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

## REST API 사용
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