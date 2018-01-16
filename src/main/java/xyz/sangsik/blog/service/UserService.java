package xyz.sangsik.blog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import xyz.sangsik.blog.domain.User;
import xyz.sangsik.blog.repository.UserRepository;

import javax.transaction.Transactional;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Transactional
    public User add(User user) throws DuplicateKeyException {
        if (isDuplicateUser(user)) {
            throw new DuplicateKeyException(user.getName());
        }
        return userRepository.save(user);
    }

    private boolean isDuplicateUser(User user) {
        int count = userRepository.countByName(user.getName());
        return (count == 0) ? false : true;
    }

}
