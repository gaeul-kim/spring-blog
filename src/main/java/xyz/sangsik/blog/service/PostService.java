package xyz.sangsik.blog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import xyz.sangsik.blog.domain.Category;
import xyz.sangsik.blog.domain.Post;
import xyz.sangsik.blog.repository.PostRepository;

/**
 * Created by sangsik on 2017-12-14.
 */
@Service
public class PostService {

    @Autowired
    PostRepository postRepository;

    public Post get(Long id) {
        return postRepository.findOne(id);
    }

    public Post add(Post post) {
        return postRepository.save(post);
    }


    public Page<Post> getAll(Pageable pageable, Category category) {
/*        if (category == null) {
            return postRepository.findByIsDeleted(pageable, false);
        } else {
            return postRepository.findByCategoryAndIsDeleted(pageable, category, false);
        }*/
        return postRepository.findByCategoryAndIsDeleted(pageable, category, false);
    }
}




