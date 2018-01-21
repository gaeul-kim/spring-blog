package xyz.sangsik.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.stereotype.Repository;
import xyz.sangsik.blog.domain.Post;

/**
 * Created by sangsik on 2017-12-14.
 */
@Repository
public interface PostRepository extends JpaRepository<Post, Long>, QueryDslPredicateExecutor<Post> {
}
