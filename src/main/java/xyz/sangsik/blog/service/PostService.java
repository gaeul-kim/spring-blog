package xyz.sangsik.blog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import xyz.sangsik.blog.domain.Category;
import xyz.sangsik.blog.domain.Post;
import xyz.sangsik.blog.domain.PostPredicate;
import xyz.sangsik.blog.domain.User;
import xyz.sangsik.blog.repository.PostRepository;

import javax.transaction.Transactional;

/**
 * Created by sangsik on 2017-12-14.
 */
@Service
public class PostService {
    @Autowired
    PostRepository postRepository;

    @Transactional
    public Page<Post> search(Category category, String writer, Pageable pageable) {
        return postRepository.findAll(PostPredicate.search(category, writer), pageable);
    }

    @Transactional
    public Post get(Long id) {
        Post post = postRepository.findOne(id);
        if (post != null) {
            post.increaseViewCount();
        }
        return post;
    }

    public Post add(Post post) {
        return postRepository.save(post);
    }
}
