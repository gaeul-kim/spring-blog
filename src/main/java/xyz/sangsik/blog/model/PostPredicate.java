package xyz.sangsik.blog.model;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import org.springframework.util.StringUtils;
import xyz.sangsik.blog.model.entity.QPost;

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
}