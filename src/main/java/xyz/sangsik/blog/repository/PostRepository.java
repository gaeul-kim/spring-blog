package xyz.sangsik.blog.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import xyz.sangsik.blog.domain.Category;
import xyz.sangsik.blog.domain.Post;

/**
 * Created by sangsik on 2017-12-14.
 */
@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    Page<Post> findByCategoryAndIsDeleted(Pageable pageable, Category category, boolean isDeleted);

/*
    Page<Post> findByIsDeleted(Pageable pageable, boolean isDeleted);
*/
}
