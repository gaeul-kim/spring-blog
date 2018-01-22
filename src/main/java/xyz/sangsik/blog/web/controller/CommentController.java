package xyz.sangsik.blog.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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
import xyz.sangsik.blog.web.dto.post.PostResponseDto;
import xyz.sangsik.blog.web.security.UserPrincipal;

import javax.inject.Provider;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@RestController
public class CommentController {

    @Autowired
    Provider<CommentRequestDto> commentRequestDtoProvider;

    @Autowired
    CommentService commentService;

    @Autowired
    UserService userService;

    @GetMapping("/comment/{postId}")
    public List<CommentResponseDto> comments(@PathVariable Long postId) {
        List<CommentResponseDto> comments = commentService.getComments(postId)
                .stream()
                .map(CommentResponseDto::new)
                .collect(Collectors.toList());
        return comments;
    }

    @PostMapping("/comment")
    public CommentResponseDto add(CommentRequestDto requestDto, @AuthenticationPrincipal UserPrincipal activeUser) {
        CommentRequestDto providedDto = commentRequestDtoProvider.get();
        providedDto.bindingRequest(requestDto);
        providedDto.setAuthor(activeUser.getUser());
        Comment c = commentService.add(providedDto.toEntity());
        return new CommentResponseDto(c);
    }


}
