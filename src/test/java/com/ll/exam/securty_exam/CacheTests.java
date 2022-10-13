package com.ll.exam.securty_exam;

import com.ll.exam.securty_exam.app.cache.CacheTestService;
import com.ll.exam.securty_exam.app.cache.PersonCache;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@Transactional
@ActiveProfiles("test")
class CacheTests {

    @Autowired
    private CacheTestService cacheTestService;

    @Test
    @DisplayName("캐시 사용")
    void t1() {
        int rs = cacheTestService.getCachedInt();
        assertThat(rs).isGreaterThan(0);
        System.out.println("rs = " + rs);

        rs = cacheTestService.getCachedInt();
        assertThat(rs).isGreaterThan(0);
        System.out.println("rs = " + rs);
    }

    @Test
    @DisplayName("캐시 사용, 삭제, 생성")
    void t2() {
        int rs = cacheTestService.getCachedInt();
        assertThat(rs).isGreaterThan(0);
        System.out.println("rs = " + rs);

        rs = cacheTestService.getCachedInt();
        assertThat(rs).isGreaterThan(0);
        System.out.println("rs = " + rs);

        rs = cacheTestService.getCachedInt();
        assertThat(rs).isGreaterThan(0);
        System.out.println("rs = " + rs);

        cacheTestService.deleteCachedInt();

        rs = cacheTestService.getCachedInt();
        assertThat(rs).isGreaterThan(0);
        System.out.println("rs = " + rs);

    }

    @Test
    @DisplayName("캐시 사용, 수정, 사용")
    void t3() {
        int rs = cacheTestService.getCachedInt();
        assertThat(rs).isGreaterThan(0);
        System.out.println("rs = " + rs);

        rs = cacheTestService.getCachedInt();
        assertThat(rs).isGreaterThan(0);
        System.out.println("rs = " + rs);

        rs = cacheTestService.getCachedInt();
        assertThat(rs).isGreaterThan(0);
        System.out.println("rs = " + rs);

        rs = cacheTestService.updateCachedInt();
        assertThat(rs).isGreaterThan(0);
        System.out.println("rs = " + rs);

        rs = cacheTestService.getCachedInt();
        assertThat(rs).isGreaterThan(0);
        System.out.println("rs = " + rs);

    }

    @Test
    @DisplayName("캐시 Key는 Parameter로 자동 지정된다.")
    void t4() {
        // 캐시 생성(rs = 9)
        int rs = cacheTestService.plus(3, 6);
        assertThat(rs).isGreaterThan(0);
        System.out.println("rs = " + rs);

        // 캐시 사용(rs = 9)
        rs = cacheTestService.plus(3, 6);
        assertThat(rs).isGreaterThan(0);
        System.out.println("rs = " + rs);

        // 캐시 생성(rs = 7)
        rs = cacheTestService.plus(5, 2);
        assertThat(rs).isGreaterThan(0);
        System.out.println("rs = " + rs);

        // 캐시 사용(rs = 7)
        rs = cacheTestService.plus(5, 2);
        assertThat(rs).isGreaterThan(0);
        System.out.println("rs = " + rs);

        // 캐시 사용(rs = 9)
        rs = cacheTestService.plus(3, 6);
        assertThat(rs).isGreaterThan(0);
        System.out.println("rs = " + rs);
    }

    @Test
    @DisplayName("레퍼런스 매개변수")
    void t5() throws Exception{
        PersonCache p1 = new PersonCache(1, "홍길동1");
        PersonCache p2 = new PersonCache(1, "홍길동2");

        System.out.println(p1.equals(p2));

        String personName = cacheTestService.getName(p1, 5);
        System.out.println("personName = " + personName);

        personName = cacheTestService.getName(p2, 10);
        System.out.println("personName = " + personName);
    }
}