package xyz.sangsik.blog.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.sangsik.blog.domain.Comment;
import xyz.sangsik.blog.service.CommentService;
import xyz.sangsik.blog.service.PostService;
import xyz.sangsik.blog.service.UserService;
import xyz.sangsik.blog.web.dto.comment.CommentRequestDto;
import xyz.sangsik.blog.web.dto.comment.CommentResponseDto;

import javax.inject.Provider;
import java.util.ArrayList;
import java.util.List;

@RestController
public class CommentController {

    @Autowired
    Provider<CommentRequestDto> commentRequestDtoProvider;

    @Autowired
    CommentService commentService;

    @Autowired
    UserService userService;

    @Autowired
    PostService postService;


    @GetMapping("/comment/{postId}")
    public List<CommentResponseDto> comments(@PathVariable Long postId) {
//        List<Comment> comments = postService.get(postId).getComments();
        List<Comment> comments = commentService.getComments(postId);

        List<CommentResponseDto> dtos = new ArrayList<CommentResponseDto>();
        for (Comment c : comments) {
            dtos.add(new CommentResponseDto(c));
        }
        return dtos;
    }

    @PostMapping("/comment")
    public CommentResponseDto add(CommentRequestDto requestDto) {
        CommentRequestDto providedDto = commentRequestDtoProvider.get();
        providedDto.bindingRequest(requestDto);
/*
        comment.setAuthor(activeUser.getUser());
*/
        providedDto.setAuthor(userService.get(1L));
        Comment c = commentService.add(providedDto.toEntity());
        return new CommentResponseDto(c);
    }


}
