package xyz.sangsik.blog.domain;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import org.springframework.util.StringUtils;
import xyz.sangsik.blog.entity.QPost;

public class PostPredicate {
    public static Predicate search(Category category, String writer) {
        QPost post = QPost.post;
        BooleanBuilder builder = new BooleanBuilder();

        if (category != Category.ALL) {
            builder.and(post.category.eq(category));
        }
        if (!StringUtils.isEmpty(writer)) {
            builder.and(post.writer.name.eq(writer));
        }

        builder.and(post.isDeleted.eq(false));

        return builder;
    }
}