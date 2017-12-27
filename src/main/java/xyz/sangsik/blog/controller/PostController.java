package xyz.sangsik.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import xyz.sangsik.blog.domain.Category;
import xyz.sangsik.blog.domain.Post;
import xyz.sangsik.blog.service.PostService;
import xyz.sangsik.blog.util.CategoryPropertyEditor;
import xyz.sangsik.blog.util.PageWrapper;

import java.util.Date;

/**
 * Created by sangsik on 2017-12-14.
 */
@Controller
public class PostController {

    @Autowired
    PostService postService;

    @InitBinder
    public void initBinder(WebDataBinder webDataBinder) {
        webDataBinder.registerCustomEditor(Category.class, new CategoryPropertyEditor());
    }

    @GetMapping("/posts/{category}")
    public String list(Model model, Pageable pageable, @PathVariable Category category) {
        model.addAttribute("date", new Date());
        Page<Post> posts = postService.getAll(pageable, category);
        model.addAttribute("posts", posts.getContent());
        model.addAttribute("page", new PageWrapper<Post>(posts, "/abcd"));
        return "post/post-list";
    }

    @GetMapping("/post/{id}")
    public String view(Model model, @PathVariable Long id) {
        model.addAttribute("post", postService.get(id));
        return "post/post-view";
    }

    @GetMapping("/post/edit/{id}")
    public String edit(Model model, @PathVariable Long id) {
        model.addAttribute("post", postService.get(id));
        // todo : add model attribute
        return "post/post-edit";
    }


}
