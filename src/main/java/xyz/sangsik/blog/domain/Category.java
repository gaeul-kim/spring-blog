package xyz.sangsik.blog.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by sangsik on 2017-12-27.
 */
@AllArgsConstructor
public enum Category {
    IT("IT", true), PROGRAMMING("프로그래밍", true), TRAVEL("여행", true), INVALID("미분류", true), ALL("전체", false);

    @Getter
    private String name;

    @Setter
    @Getter
    private boolean selectable;
}
