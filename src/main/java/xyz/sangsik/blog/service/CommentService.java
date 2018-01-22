package xyz.sangsik.blog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.sangsik.blog.domain.Comment;
import xyz.sangsik.blog.repository.CommentRepository;

import java.util.List;

@Service
public class CommentService {

    @Autowired
    CommentRepository commentRepository;

    public Comment add(Comment comment) {
        return commentRepository.save(comment);
    }

    public List<Comment> getComments(Long postId) {
        return commentRepository.findByPostId(postId);
    }
}
