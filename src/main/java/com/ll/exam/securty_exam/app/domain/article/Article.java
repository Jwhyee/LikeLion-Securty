package com.ll.exam.securty_exam.app.domain.article;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ll.exam.securty_exam.app.base.BaseEntity;
import com.ll.exam.securty_exam.app.domain.member.Member;
import com.ll.exam.securty_exam.app.util.Util;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

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
