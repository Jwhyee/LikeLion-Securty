package com.ll.exam.securty_exam.service;

import com.ll.exam.securty_exam.domain.article.Article;
import com.ll.exam.securty_exam.domain.member.Member;
import com.ll.exam.securty_exam.domain.member.MemberContext;
import com.ll.exam.securty_exam.web.dto.ArticleModifyDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ArticleService {

    private final ArticleRepository articleRepository;
    public Article write(Member author, String subject, String content) {
        Article article = Article.builder()
                .author(author)
                .subject(subject)
                .content(content)
                .build();

        articleRepository.save(article);
        return article;
    }

    public List<Article> findAll() {
        return articleRepository.findAllByOrderByIdDesc();
    }

    public Optional<Article> findById(Long id) {
        return articleRepository.findById(id);
    }

    public void delete(Article article) {
        articleRepository.delete(article);
    }

    public boolean actorCanDelete(MemberContext memberContext, Article article) {
        return memberContext.getId() == article.getAuthor().getId();
    }

    public void modify(Article article, ArticleModifyDto articleModifyDto) {
        article.setSubject(articleModifyDto.getSubject());
        article.setContent(articleModifyDto.getContent());

        articleRepository.save(article);
    }

    public boolean actorCanModify(MemberContext memberContext, Article article) {
        return memberContext.getId() == article.getAuthor().getId();
    }
}
