package xyz.sangsik.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import xyz.sangsik.blog.domain.User;
import xyz.sangsik.blog.service.UserService;

import java.util.Date;

/**
 * Created by sangsik on 2017-12-14.
 */
@Controller
public class MainController {

    @Autowired
    UserService userService;

    @PostMapping({"", "/"})
    public String index(Model model, User user) {
        userService.add(user);
        return "redirect:/";
    }

    @GetMapping({"", "/"})
    public String index(Model model) {
        model.addAttribute("date", new Date());
        model.addAttribute("user", new User());
        model.addAttribute("list", userService.getAll());
        return "index";
    }

}
