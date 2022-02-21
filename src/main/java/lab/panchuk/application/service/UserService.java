package lab.panchuk.application.service;

import lab.panchuk.application.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    List<User> getAll();
    User getUserByUsername(String username);
    void addUser (User user);
}
