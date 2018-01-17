package xyz.sangsik.blog.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import xyz.sangsik.blog.entity.User;
import xyz.sangsik.blog.service.UserService;
import xyz.sangsik.blog.web.validator.UserValidator;

@Controller
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    UserValidator userValidator;

    @GetMapping("/login")
    public String loginForm(Model model, User user) {
        return "user/login";
    }

    @GetMapping("/join")
    public String joinForm(Model model, User user) {
        return "user/join";
    }

    @PostMapping("/join")
    public String join(Model model, @ModelAttribute User user, BindingResult bindingResult) {
        userValidator.validate(user, bindingResult);
        if (bindingResult.hasErrors()) {
            return "user/join";
        }
        userService.add(user);
        return "redirect:/";
    }


}
