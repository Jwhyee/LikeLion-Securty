package com.ll.exam.securty_exam;

import com.ll.exam.securty_exam.app.service.MemberService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.transaction.annotation.Transactional;

import java.nio.charset.StandardCharsets;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@Transactional
@ActiveProfiles("test")
class CacheTests {

    @Autowired
    private MemberService memberService;

    @Test
    @DisplayName("캐시 사용")
    void t1() {
        // Given
        int rs = memberService.getCachedInt();
        // When

        // Then
        assertThat(rs).isGreaterThan(0);
        System.out.println("rs = " + rs);

        // Given
        rs = memberService.getCachedInt();
        // When

        // Then
        assertThat(rs).isGreaterThan(0);
        System.out.println("rs = " + rs);
    }
}