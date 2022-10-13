package com.ll.exam.securty_exam.service;


import com.ll.exam.securty_exam.domain.article.Article;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ArticleRepository extends JpaRepository<Article, Long> {
    List<Article> findAllByOrderByIdDesc();
}
