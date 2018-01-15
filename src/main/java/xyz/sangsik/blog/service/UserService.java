package xyz.sangsik.blog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.sangsik.blog.domain.User;
import xyz.sangsik.blog.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public User add(User user) {
        return userRepository.save(user);
    }

    public boolean isDuplicateName(String name) {
        int count = userRepository.countByName(name);
        return (count == 0) ? false : true;
    }

}
