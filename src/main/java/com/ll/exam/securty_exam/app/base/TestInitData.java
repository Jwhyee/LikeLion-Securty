package com.ll.exam.securty_exam.app.base;

import com.ll.exam.securty_exam.app.domain.member.Member;
import com.ll.exam.securty_exam.app.service.ArticleService;
import com.ll.exam.securty_exam.app.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@Profile("test")
public class TestInitData {

    @Bean
    CommandLineRunner initData(MemberService memberService, ArticleService articleService, PasswordEncoder passwordEncoder) {
        String password = passwordEncoder.encode("1234");
        return args -> {
            Member member1 = memberService.join("user1", password, "user1@test.com");
            Member member2 = memberService.join("user2", password, "user2@test.com");

            articleService.write(member1, "제목1", "내용1");
            articleService.write(member1, "제목2", "내용2");
            articleService.write(member2, "제목3", "내용3");
            articleService.write(member2, "제목4", "내용4");
        };
    }
}