package xyz.sangsik.blog.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by sangsik on 2017-12-27.
 */
public enum Category {
    IT("IT"), PROGRAMMING("프로그래밍"), TRAVEL("여행"), ALL("전체목록"), INVALID("유효하지 않은 카테고리");

    Category(String name) {
        this.name = name;
    }

    @Getter
    @Setter
    private String name;
}
