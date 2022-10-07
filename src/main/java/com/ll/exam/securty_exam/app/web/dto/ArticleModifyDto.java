package com.ll.exam.securty_exam.app.web.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class ArticleModifyDto {
    @NotEmpty(message = "제목을 입력해주세요.")
    private String subject;
    @NotEmpty(message = "내용을 입력해주세요.")
    private String content;

}
