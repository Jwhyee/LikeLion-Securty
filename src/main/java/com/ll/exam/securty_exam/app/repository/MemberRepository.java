package com.ll.exam.securty_exam.app.repository;

import com.ll.exam.securty_exam.app.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByUsername(String username);
}