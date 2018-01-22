package xyz.sangsik.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import xyz.sangsik.blog.domain.Comment;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByPostId(Long postId);
}
