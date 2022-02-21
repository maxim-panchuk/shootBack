package lab.panchuk.application.service.impl;

import lab.panchuk.application.entity.User;
import lab.panchuk.application.repository.UserRepository;
import lab.panchuk.application.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl (UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    public User getUserByUsername(String username) {
        return userRepository.getByUsername(username);
    }

    @Override
    public void addUser(User user) {
        userRepository.saveAndFlush(user);
    }
}
