package lab.panchuk.application.util;

import lab.panchuk.application.entity.User;
import lab.panchuk.application.pojo.RegisterRequest;
import lab.panchuk.application.service.UserService;
import lab.panchuk.application.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RegisterUtil {
    private final UserServiceImpl userService;

    @Autowired
    public RegisterUtil (UserServiceImpl userService) {
        this.userService = userService;
    }

    public boolean reg(RegisterRequest registerRequest) {
        String username = registerRequest.getUsername();
        User user = userService.getUserByUsername(username);
        return user == null;
    }

    public boolean login(RegisterRequest registerRequest) {
        String username = registerRequest.getUsername();
        User user = userService.getUserByUsername(username);
        if (user == null) return false;
        String password = SHA384Coder.encryptPassword(registerRequest.getPassword());
        return user.getPassword().equals(password);
    }
}
