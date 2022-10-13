package com.ll.exam.securty_exam;

import com.ll.exam.securty_exam.app.service.CacheTestService;
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
    @DisplayName("캐시 조건 파라미터")
    void t4() {
        int rs = cacheTestService.plus(3, 6);
        assertThat(rs).isGreaterThan(0);
        System.out.println("rs = " + rs);

        rs = cacheTestService.plus(3, 6);
        assertThat(rs).isGreaterThan(0);
        System.out.println("rs = " + rs);

        rs = cacheTestService.plus(5, 2);
        assertThat(rs).isGreaterThan(0);
        System.out.println("rs = " + rs);

        rs = cacheTestService.plus(5, 2);
        assertThat(rs).isGreaterThan(0);
        System.out.println("rs = " + rs);
    }
}