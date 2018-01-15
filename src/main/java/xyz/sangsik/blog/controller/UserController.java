package xyz.sangsik.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import xyz.sangsik.blog.domain.PostResponse;
import xyz.sangsik.blog.domain.User;
import xyz.sangsik.blog.domain.UserResponse;
import xyz.sangsik.blog.service.UserService;

@Controller
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/join")
    public String signUpForm(Model model) {
        return "user/join";
    }

    @ResponseBody
    @PostMapping("/join")
    public PostResponse join(User user, UserResponse response) {
        if (userService.isDuplicateName(user.getName())) {

        }
        response.setSuccess(userService.add(user).getId());
        return null;
    }


}
