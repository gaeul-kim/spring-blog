package xyz.sangsik.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import xyz.sangsik.blog.domain.User;
import xyz.sangsik.blog.service.UserService;

import javax.validation.Valid;

@Controller
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/join")
    public String signUpForm(Model model, User user) {
        return "user/join";
    }

    @PostMapping("/join")
    public String join(Model model, @Valid User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("message", "입력값이 올바르지 않습니다.");
            return "user/join";
        }

        try {
            userService.add(user);
            return "redirect:/";
        } catch (DuplicateKeyException e) {
            model.addAttribute("message", "이미 사용중인 이름입니다.");
            return "user/join";
        }
    }


}
