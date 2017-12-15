package xyz.sangsik.blog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.sangsik.blog.domain.User;
import xyz.sangsik.blog.repository.UserRepository;

import java.util.List;

/**
 * Created by sangsik on 2017-12-14.
 */
@Service
public class UserServiceJpa implements UserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public User get(Long id) {
        return userRepository.findOne(id);
    }

    @Override
    public User add(User user) {
        return userRepository.save(user);
    }

    @Override
    public User update(User user) {
        return null;
    }

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }
    
    @Override
    public void delete(Long id) {
        userRepository.delete(id);
    }


}
