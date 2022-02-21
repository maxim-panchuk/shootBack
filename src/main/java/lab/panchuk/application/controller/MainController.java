package lab.panchuk.application.controller;

import lab.panchuk.application.entity.User;
import lab.panchuk.application.service.impl.DotServiceImpl;
import lab.panchuk.application.service.impl.UserServiceImpl;
import lab.panchuk.application.util.SHA384Coder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping()
public class MainController {

    private final DotServiceImpl dotService;
    private final UserServiceImpl userService;

    @Autowired
    public MainController (DotServiceImpl dotService, UserServiceImpl userService) {
        this.dotService = dotService;
        this.userService = userService;
    }

    @GetMapping
    public String index(@ModelAttribute("user") User user) {
        return "sign_in";
    }

    @GetMapping("/sign_up")
    public String getSignUp (@ModelAttribute("user") User user) {
        return "sign_up";
    }

    @PostMapping("/sign_in")
    public String signIn (@ModelAttribute("user") User user, Model model) {
        String username = user.getUsername();
        User dbUser = userService.getUserByUsername(username);

        // username doesn't exist
        if (dbUser == null) {
            return "sign_in";
        }
        String dbPassword = dbUser.getPassword();
        String password = user.getPassword();
        String encryptedPassword = SHA384Coder.encryptPassword(password);

        // password doesn't suit
        if (!dbPassword.equals(encryptedPassword)) {
            return "sign_in";
        }
        user.setPassword(encryptedPassword);
        model.addAttribute("user", user);
        return "/main";
    }

    @PostMapping("/sign_up")
    public String singUp (@ModelAttribute User user, Model model) {
        String username = user.getUsername();

        if (userService.getUserByUsername(username) != null) {
            return "sign_up";
        }

        String password = user.getPassword();
        String encryptedPassword = SHA384Coder.encryptPassword(password);
        user.setPassword(encryptedPassword);
        model.addAttribute("user", user);
        userService.addUser(user);
        return "/main";
    }
}
