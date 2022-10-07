package com.ll.exam.securty_exam.app.service;


import com.ll.exam.securty_exam.app.domain.article.Article;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ArticleRepository extends JpaRepository<Article, Long> {
    List<Article> findAllByOrderByIdDesc();
}
