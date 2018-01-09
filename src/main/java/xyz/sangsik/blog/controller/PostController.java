package xyz.sangsik.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import xyz.sangsik.blog.domain.Category;
import xyz.sangsik.blog.domain.Post;
import xyz.sangsik.blog.domain.ResponseDTO;
import xyz.sangsik.blog.domain.User;
import xyz.sangsik.blog.repository.UserRepository;
import xyz.sangsik.blog.service.PostService;
import xyz.sangsik.blog.util.CategoryPropertyEditor;
import xyz.sangsik.blog.util.PageWrapper;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
public class PostController {

    @Autowired
    PostService postService;

    @Autowired
    UserRepository userRepository;

    @InitBinder
    public void initBinder(WebDataBinder webDataBinder) {
        webDataBinder.registerCustomEditor(Category.class, new CategoryPropertyEditor());
    }

    @GetMapping("/post")
    public String home(Model model, @RequestParam(value = "category", required = false, defaultValue = "ALL") Category category, String writer, Pageable pageable) {
        Page<Post> posts = postService.search(category, writer, pageable);

        model.addAttribute("category", category);
        model.addAttribute("writer", writer);
        model.addAttribute("posts", posts.getContent());
        model.addAttribute("page", new PageWrapper<Post>(posts));
        model.addAttribute("active", "home");
        return "/post/home";
    }

    @GetMapping("/post/{id}")
    public String view(Model model, @PathVariable Long id) {
        model.addAttribute("post", postService.get(id));
        return "/post/post";
    }

    @GetMapping("/post/add")
    public String add(Model model) {
        model.addAttribute("categories", Category.values());
        model.addAttribute("active", "add");
        return "/post/add";
    }

    @ResponseBody
    @PostMapping("/post/add")
    public ResponseDTO add(Model model, @Valid Post post, BindingResult bindingResult, ResponseDTO dto) {
        if (bindingResult.hasErrors()) {
            dto.setBindingError();
            return dto;
        }

        // todo: 사용자 정보 입력
        post.setWriter(userRepository.findOne(1L));

        dto.setSuccess(postService.add(post));
        return dto;
    }

    @GetMapping("/post/edit/{id}")
    public String edit(Model model, @PathVariable Long id) {
        model.addAttribute("post", postService.get(id));

        return "edit";
    }

    @PostMapping("/post/edit")
    public String edit(Model model, Post post) {
        return "redirect:/post/" + post.getId();
    }


}
