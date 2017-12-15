package xyz.sangsik.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import xyz.sangsik.blog.domain.Article;

/**
 * Created by sangsik on 2017-12-14.
 */
@Repository
public interface ArticleRepository extends JpaRepository<Article, Long> {
}
