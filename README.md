## 멋쟁이 사자처럼
### JWT 연동 프로젝트
- 연동되는 리포지토리 : [링크](https://github.com/Jwhyee/LikeLion-JWT-Exam)
- 강의 리포지토리 : [링크](https://github.com/jhs512/app_2022_10_05)
- 수업 자료 : [링크](https://wiken.io/ken/10698)

### RsData, Util 클래스에 `Generic` 사용
```java
// RsData
public class RsData<T> {
    private String resultCode;
    private String msg;
    private T data;
    ...
}

// Util.spring
public static <T> ResponseEntity<RsData> responseEntityOf(RsData<T> rsData) {
    return responseEntityOf(rsData, null);
}
```
- 데이터를 한정적으로 지정해서 사용해주기 위해 사용

## `RsData` 분석
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
- 위 요청이 들어왔을 때 articles에 출력을 해줄 data를 담아서 보내줄 수 있다. 