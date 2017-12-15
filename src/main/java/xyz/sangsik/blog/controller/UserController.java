package xyz.sangsik.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import xyz.sangsik.blog.domain.User;
import xyz.sangsik.blog.service.UserService;

import java.util.List;

/**
 * Created by sangsik on 2017-12-14.
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/")
    public List<User> getUserList() {
        return userService.getAll();
    }

    @GetMapping("/{id}")
    public User getUser(@PathVariable Long id) {
        return userService.get(id);
    }

    @PostMapping("/")
    public User addUser(User user) {
        return userService.add(user);
    }
}
