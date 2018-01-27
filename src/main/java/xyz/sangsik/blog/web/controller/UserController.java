package xyz.sangsik.blog.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.util.StringUtils;
import xyz.sangsik.blog.model.entity.User;
import xyz.sangsik.blog.service.SecurityService;
import xyz.sangsik.blog.service.UserService;
import xyz.sangsik.blog.web.validator.UserValidator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    SecurityService securityService;

    @Autowired
    UserValidator userValidator;

    @GetMapping("/login")
    public String loginForm(HttpServletRequest request, HttpSession session, User user,
                            @RequestParam(value = "error", required = false) String error) {
        if (StringUtils.isEmpty(error)) {
            String referer = request.getHeader("Referer");
            request.getSession().setAttribute("prevPage", referer);
        }

        user.setName((String) session.getAttribute("name"));
        user.setPassword((String) session.getAttribute("password"));
        session.removeAttribute("name");
        session.removeAttribute("password");
        return "user/login";
    }

    @GetMapping("/join")
    public String joinForm(User user) {
        return "user/join";
    }

    @PostMapping("/join")
    public String join(@ModelAttribute User user, BindingResult bindingResult) {
        userValidator.validate(user, bindingResult);
        if (bindingResult.hasErrors()) {
            return "user/join";
        }

        String originalPassword = user.getPassword();
        userService.registration(user);
        securityService.autoLogin(user.getName(), originalPassword);
        return "redirect:/";
    }
}
