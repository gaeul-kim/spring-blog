package xyz.sangsik.blog.model;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import org.springframework.util.StringUtils;
import xyz.sangsik.blog.model.entity.QPost;

public class PostPredicate {
    public static Predicate getPostsDefault(String category) {
        QPost post = QPost.post;
        BooleanBuilder builder = new BooleanBuilder();

        if (!StringUtils.isEmpty(category)) {
            builder.and(post.category.name.eq(category));
        }
        builder.and(post.isDeleted.eq(false));

        return builder;
    }

    public static Predicate search(String keyword) {
        QPost post = QPost.post;
        BooleanBuilder builder = new BooleanBuilder();

        if (!StringUtils.isEmpty(keyword)) {
            builder
                    .or(post.title.containsIgnoreCase(keyword))
                    .or(post.content.containsIgnoreCase(keyword));
        }
        builder.and(post.isDeleted.eq(false));

        return builder;
    }
}