package com.ll.exam.securty_exam.app.service;

import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CacheTestService {
    @Cacheable("key1")
    public int getCachedInt() {
        System.out.println("호출됨");
        return 5;
    }

    @CacheEvict("key1")
    public void deleteCachedInt() {
        System.out.println("삭제됨");
    }

    @CachePut("key1")
    public int updateCachedInt() {
        System.out.println("수정됨");
        return 10;
    }

    @Cacheable(value="sum")
    public int plus(int a, int b) {
        System.out.println("캐시 생성");
        return a + b;
    }
}
