package com.ll.exam.securty_exam.app.web.controller;

import com.ll.exam.securty_exam.app.base.RsData;
import com.ll.exam.securty_exam.app.domain.article.Article;
import com.ll.exam.securty_exam.app.service.ArticleService;
import com.ll.exam.securty_exam.app.util.Util;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/articles")
public class ArticleController {

    private final ArticleService articleService;

    @GetMapping("")
    public ResponseEntity<RsData> list() {
        List<Article> articles = articleService.findAll();
        return Util.spring.responseEntityOf(
                RsData.successOf(
                        Util.mapOf(
                                "articles", articles
                        )
                )
        );
    }
}
