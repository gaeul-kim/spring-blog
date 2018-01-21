package xyz.sangsik.blog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import xyz.sangsik.blog.domain.Post;
import xyz.sangsik.blog.domain.PostPredicate;
import xyz.sangsik.blog.web.dto.post.PostResponseDto;
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
    public Page<PostResponseDto> getPosts(String category, Pageable pageable) {
        Page<Post> posts = postRepository.findAll(PostPredicate.search(category), pageable);
        Page<PostResponseDto> dtos = posts.map(new Converter<Post, PostResponseDto>() {
            @Override
            public PostResponseDto convert(Post post) {
                return new PostResponseDto(post);
            }
        });
        return dtos;
    }

    @Transactional
    public PostResponseDto get(Long id) {
        Post post = postRepository.findOne(id);
        if (post == null) {
            throw new RuntimeException("Invalid Request : Post does not exist or has been deleted");
        }
        post.increaseViewCount();
        PostResponseDto dto = new PostResponseDto(post);
        return dto;
    }

    public Post add(Post post) {
        return postRepository.save(post);
    }
}
