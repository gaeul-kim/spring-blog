package xyz.sangsik.blog.domain;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import org.springframework.util.StringUtils;

public class PostPredicate {
    public static Predicate search(String category) {
        QPost post = QPost.post;
        BooleanBuilder builder = new BooleanBuilder();

        if (!StringUtils.isEmpty(category)) {
            builder.and(post.category.name.eq(category));
        }
        builder.and(post.isDeleted.eq(false));

        return builder;
    }

    public static Predicate search(String category, String writer) {
        QPost post = QPost.post;
        BooleanBuilder builder = new BooleanBuilder();
/*
        if (!StringUtils.isEmpty(category)) {
            builder.and();
        }
        if (!StringUtils.isEmpty(writer)) {
            builder.and(post.author.name.eq(writer));
        }

        builder.and(post.isDeleted.eq(false));
*/
        return builder;
    }
}