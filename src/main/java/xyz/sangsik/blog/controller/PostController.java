package xyz.sangsik.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import xyz.sangsik.blog.domain.Category;
import xyz.sangsik.blog.domain.Post;
import xyz.sangsik.blog.service.PostService;
import xyz.sangsik.blog.util.CategoryPropertyEditor;
import xyz.sangsik.blog.util.PageWrapper;

@Controller
public class PostController {

    @Autowired
    PostService postService;

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
        return "/post/home";
    }

    @GetMapping("/post/{id}")
    public String view(Model model, @PathVariable Long id) {
        model.addAttribute("post", postService.get(id));
        return "/post/post";
    }

    @GetMapping("/add")
    public String add(Model model) {
        return "/post/add";
    }

    @PostMapping("/add")
    public String add(Model model, Post post) {
        return "redirect:/post/" + post.getId();
    }

    @GetMapping("/edit/{id}")
    public String edit(Model model, @PathVariable Long id) {
        model.addAttribute("post", postService.get(id));
        return "edit";
    }

    @PostMapping("/edit")
    public String edit(Model model, Post post) {
        return "redirect:/post/" + post.getId();
    }


}
