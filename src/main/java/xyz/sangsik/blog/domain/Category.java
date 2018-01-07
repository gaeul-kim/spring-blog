package xyz.sangsik.blog.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by sangsik on 2017-12-27.
 */
@AllArgsConstructor
public enum Category {
    IT("IT"), PROGRAMMING("프로그래밍"), TRAVEL("여행"), ALL("전체");

    @Getter
    @Setter
    private String name;
}
