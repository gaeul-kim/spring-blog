package xyz.sangsik.blog.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import xyz.sangsik.blog.domain.Category;
import xyz.sangsik.blog.entity.Post;
import xyz.sangsik.blog.domain.Response;
import xyz.sangsik.blog.repository.UserRepository;
import xyz.sangsik.blog.service.PostService;
import xyz.sangsik.blog.util.CategoryPropertyEditor;
import xyz.sangsik.blog.util.PageWrapper;

import javax.validation.Valid;

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
        return "/post/list";
    }

    @GetMapping("/post/{id}")
    public String view(Model model, @PathVariable Long id) {
        model.addAttribute("post", postService.get(id));
        return "/post/detail";
    }

    @GetMapping("/write")
    public String write(Model model) {
        model.addAttribute("categories", Category.values());
        model.addAttribute("active", "add");
        return "/post/write";
    }

    @ResponseBody
    @PostMapping("/write")
    public Response write(Model model, @Valid Post post, BindingResult bindingResult, Response response) {
        //TODO:응답형식 변경
        if (bindingResult.hasErrors()) {
            response.setBindingError();
            return response;
        }

        //TODO: 사용자 정보 입력
        post.setWriter(userRepository.findOne(1L));
        response.setSuccess(postService.add(post).getId());
        return response;
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
