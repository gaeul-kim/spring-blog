package xyz.sangsik.blog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import xyz.sangsik.blog.model.entity.Post;
import xyz.sangsik.blog.model.PostPredicate;
import xyz.sangsik.blog.repository.PostRepository;

import javax.transaction.Transactional;

/**
 * Created by sangsik on 2017-12-14.
 */
@Service
public class PostService {

    @Autowired
    PostRepository postRepository;

    public Page<Post> getPosts(String category, Pageable pageable) {
        return postRepository.findAll(PostPredicate.search(category), pageable);
    }

    @Transactional
    public Post get(Long id) {
        Post post = postRepository.findOne(id);
        if (post == null) {
            throw new RuntimeException("Invalid Request : Post does not exist or has been deleted");
        }
        post.increaseViewCount();
        return post;
    }

    public Post add(Post post) {
        return postRepository.save(post);
    }
}
