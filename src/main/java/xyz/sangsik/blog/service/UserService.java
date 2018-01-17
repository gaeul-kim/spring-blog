package xyz.sangsik.blog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import xyz.sangsik.blog.entity.User;
import xyz.sangsik.blog.repository.RoleRepository;
import xyz.sangsik.blog.repository.UserRepository;

import javax.transaction.Transactional;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Transactional
    public User add(User user) throws DuplicateKeyException {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.getRoles().add(roleRepository.findByName("USER"));
        return userRepository.save(user);
    }

    public User get(String name) {
        return userRepository.findByName(name);
    }

}
