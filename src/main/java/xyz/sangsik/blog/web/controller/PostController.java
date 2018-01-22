package xyz.sangsik.blog.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import xyz.sangsik.blog.domain.Post;
import xyz.sangsik.blog.web.dto.AjaxResponse;
import xyz.sangsik.blog.web.dto.post.PostRequestDto;
import xyz.sangsik.blog.web.dto.post.PostResponseDto;
import xyz.sangsik.blog.web.security.UserPrincipal;
import xyz.sangsik.blog.service.CategoryService;
import xyz.sangsik.blog.service.PostService;
import xyz.sangsik.blog.util.PageWrapper;
import xyz.sangsik.blog.web.validator.PostValidator;

import javax.inject.Inject;
import javax.inject.Provider;
import java.util.HashMap;

@Controller
public class PostController {

    @Autowired
    PostService postService;

    @Autowired
    CategoryService categoryService;

    @Autowired
    PostValidator postValidator;

    @Autowired
    Provider<PostRequestDto> postRequestDtoProvider;

    @GetMapping({"/posts", "/posts/"})
    public String posts(Model model, String category, Pageable pageable) {
        Page<PostResponseDto> posts = postService.getPosts(category, pageable).map(new Converter<Post, PostResponseDto>() {
            @Override
            public PostResponseDto convert(Post post) {
                return new PostResponseDto(post);
            }
        }); // TODO : 람다로 변경

        model.addAttribute("categories", categoryService.getCategories()); // TODO : 카테고리를 메모리에 가지고 있을 방법

        model.addAttribute("posts", posts.getContent());
        model.addAttribute("page", new PageWrapper<PostResponseDto>(posts));
        return "/post/list";
    }

    @GetMapping("/posts/{category}")
    public String category(Model model, @PathVariable String category, Pageable pageable) {
        return posts(model, category, pageable);
    }

    @GetMapping("/post/{id}")
    public String view(Model model, @PathVariable Long id) {
        model.addAttribute("post", postService.get(id));
        return "/post/detail";
    }

    @GetMapping("/write")
    public String write(Model model) {
        model.addAttribute("categories", categoryService.getCategories());
        return "/post/write";
    }

    @ResponseBody
    @PostMapping("/write")
    public AjaxResponse write(PostRequestDto requestDto, BindingResult bindingResult, AjaxResponse ajaxResponse, @AuthenticationPrincipal UserPrincipal activeUser) {
        PostRequestDto providedDto = postRequestDtoProvider.get();
        providedDto.bindingRequest(requestDto);

        postValidator.validate(providedDto, bindingResult);
        if (bindingResult.hasErrors()) {
            ajaxResponse.setBindingError();
            return ajaxResponse;
        }

        Post post = providedDto.toEntity();
        post.setAuthor(activeUser.getUser());
        ajaxResponse.makeResponse(postService.add(post));
        return ajaxResponse;
    }
}
