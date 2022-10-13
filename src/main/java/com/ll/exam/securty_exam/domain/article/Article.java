package com.ll.exam.securty_exam.domain.article;
import com.ll.exam.securty_exam.app.base.BaseEntity;
import com.ll.exam.securty_exam.domain.member.Member;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@ToString(callSuper = true)
public class Article extends BaseEntity {
    @ManyToOne
    private Member author;

    private String subject;

    private String content;

    public Article(long id) {
        super(id);
    }
}
