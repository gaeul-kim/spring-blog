package xyz.sangsik.blog.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by sangsik on 2017-12-27.
 */
public enum Category {
    NOTICE("공지사항"), QUESTION("질문과 답변"), MARKET("장터"), INVALID("유효하지 않은 카테고리");

    Category(String name) {
        this.name = name;
    }

    @Getter
    @Setter
    private String name;
}
