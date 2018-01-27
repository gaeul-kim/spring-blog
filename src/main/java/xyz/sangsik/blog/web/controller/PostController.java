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
import xyz.sangsik.blog.model.entity.Post;
import xyz.sangsik.blog.model.ResponseObject.HttpResponse;
import xyz.sangsik.blog.model.ResponseObject.PostResponse;
import xyz.sangsik.blog.web.security.CustomUserDetails;
import xyz.sangsik.blog.service.CategoryService;
import xyz.sangsik.blog.service.PostService;
import xyz.sangsik.blog.util.PageWrapper;
import xyz.sangsik.blog.web.validator.PostValidator;

@Controller
public class PostController {

    @Autowired
    PostService postService;

    @Autowired
    CategoryService categoryService;

    @Autowired
    PostValidator postValidator;

    @GetMapping({"/posts", "/posts/"})
    public String posts(Model model, String category, Pageable pageable) {
        Page<PostResponse> posts = postService.getPosts(category, pageable)
                .map(new Converter<Post, PostResponse>() {
                    @Override
                    public PostResponse convert(Post post) {
                        return new PostResponse(post);
                    }
                });
        // TODO : 카테고리를 메모리에 가지고 있을 방법
        model.addAttribute("categories", categoryService.getCategories());
        model.addAttribute("page", new PageWrapper<PostResponse>(posts));
        return "post/list";
    }

    @GetMapping("/posts/{category}")
    public String category(Model model, @PathVariable String category, Pageable pageable) {
        return posts(model, category, pageable);
    }

    @GetMapping("/post/{id}")
    public String view(Model model, @PathVariable Long id) {
        model.addAttribute("categories", categoryService.getCategories());
        model.addAttribute("post", new PostResponse(postService.get(id)));
        return "post/detail";
    }

    @GetMapping("/write")
    public String write(Model model) {
        model.addAttribute("categories", categoryService.getCategories());
        return "post/write";
    }

    @ResponseBody
    @PostMapping("/write")
    public HttpResponse write(HttpResponse httpResponse, Post post, BindingResult bindingResult, @AuthenticationPrincipal CustomUserDetails activeUser) {
        postValidator.validate(post, bindingResult);
        if (bindingResult.hasErrors() || activeUser == null) {
            return httpResponse.fail();
        }

        post.setAuthor(activeUser.getEntity());
        return httpResponse.success(postService.add(post).getId());
    }
}
