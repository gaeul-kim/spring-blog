package xyz.sangsik.blog.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by sangsik on 2017-12-27.
 */
@AllArgsConstructor
public enum Category {
    NOTICE("공지사항"), QUESTION("질문과 답변"), MARKET("장터");

    @Getter
    @Setter
    String name;
}
