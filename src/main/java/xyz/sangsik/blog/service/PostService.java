package xyz.sangsik.blog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import xyz.sangsik.blog.domain.Category;
import xyz.sangsik.blog.domain.Post;
import xyz.sangsik.blog.repository.PostRepository;

import javax.transaction.Transactional;

/**
 * Created by sangsik on 2017-12-14.
 */
@Service
public class PostService {
    @Autowired
    PostRepository postRepository;

    public Page<Post> getAll(Pageable pageable, Category category) {
        if (category == Category.ALL) {
            return postRepository.findByIsDeleted(pageable, false);
        } else {
            return postRepository.findByCategoryAndIsDeleted(pageable, category, false);
        }
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
