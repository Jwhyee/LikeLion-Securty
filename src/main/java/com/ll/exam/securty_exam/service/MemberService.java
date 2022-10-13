package com.ll.exam.securty_exam.service;

import com.ll.exam.securty_exam.app.config.jwt.JwtProvider;
import com.ll.exam.securty_exam.domain.member.Member;
import com.ll.exam.securty_exam.domain.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    private final JwtProvider jwtProvider;

    public Member join(String username, String password, String email) {
        Member member = Member.builder()
                .username(username)
                .password(password)
                .email(email)
                .build();

        memberRepository.save(member);

        return member;
    }

    public Optional<Member> findByUsername(String username) {
        return memberRepository.findByUsername(username);
    }

    @Transactional
    public String generateAccessToken(Member member) {
        String accessToken = member.getAccessToken();

        if (!StringUtils.hasLength(accessToken)) {
            accessToken = jwtProvider.generateAccessToken(member.getAccessTokenClaims(), 60L * 60 * 24 * 365 * 100);
            member.setAccessToken(accessToken);
        }

        return accessToken;
    }

    public boolean verifyWithWhiteList(Member member, String token) {
        return member.getAccessToken().equals(token);
    }

    @Cacheable("member")
    public Member getByUsername__cached(String username) {
        // 회원 정보가 수정되면 @CacheEvict("member)를 이용해서 지워주고 다시 캐시에 등록해주어야함.
        return findByUsername(username).orElse(null);
    }
}