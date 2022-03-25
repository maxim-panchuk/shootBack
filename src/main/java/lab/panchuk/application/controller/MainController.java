package lab.panchuk.application.controller;

import com.google.gson.Gson;
import lab.panchuk.application.entity.Dot;
import lab.panchuk.application.entity.User;
import lab.panchuk.application.pojo.RegisterRequest;
import lab.panchuk.application.pojo.ResetRequest;
import lab.panchuk.application.pojo.ShotRequest;
import lab.panchuk.application.service.impl.DotServiceImpl;
import lab.panchuk.application.service.impl.UserServiceImpl;
import lab.panchuk.application.util.DotHandler;
import lab.panchuk.application.util.RegisterUtil;
import lab.panchuk.application.util.SHA384Coder;
import org.decimal4j.util.DoubleRounder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class MainController {
    private static final Gson gson = new Gson();

    private final DotServiceImpl dotService;
    private final UserServiceImpl userService;
    private final RegisterUtil registerUtil;

    @Autowired
    public MainController (DotServiceImpl dotService, UserServiceImpl userService, RegisterUtil registerUtil) {
        this.dotService = dotService;
        this.userService = userService;
        this.registerUtil = registerUtil;
    }


//    @PostMapping("/register")
//    public boolean registerUser(@RequestBody RegisterRequest registerRequest) {
//        System.out.println(registerRequest.getPassword() + " " + registerRequest.getUsername());
//        return true;
//    }
    @PostMapping("/reset")
    public ResponseEntity<String> del (@RequestBody ResetRequest resetRequest) {
        String username = resetRequest.getUsername();
        User user = userService.getUserByUsername(username);
        long id_user = user.getId();
        ArrayList<Dot> resArr = (ArrayList<Dot>) dotService.delByUser(id_user);
        System.out.println(resArr.toString());
        return ResponseEntity.ok().body(resArr.toString());
    }

    @PostMapping("/shot")
    public ResponseEntity<String> shot(@RequestBody ShotRequest shotRequest) {
        Double x = Double.parseDouble(shotRequest.getX());
        Double y = Double.parseDouble(shotRequest.getY());
        Double r = Double.parseDouble(shotRequest.getR());
        String username = shotRequest.getUsername();
        User user = userService.getUserByUsername(username);
        long id_user = user.getId();

        Dot dot = Dot.builder().x(x).y(y).r(r).id_user(id_user).build();
        DotHandler dotHandler = new DotHandler(dot);
        dotHandler.handle();
        dotService.add(dot);

        ArrayList<Dot> resArr = (ArrayList<Dot>) dotService.getByUsername(id_user);
        //System.out.println(gson.toJson(dot, Dot.class));
        //System.out.println(gson.toJson(resArr, Dot.class));

        ArrayList<String> result = new ArrayList<>();
        for (Dot element : resArr) {
            element.setX(DoubleRounder.round(element.getX(), 3));
            element.setY(DoubleRounder.round(element.getY(), 3));
            result.add(gson.toJson(element, Dot.class));
        }
        System.out.println(result.toString());
        return ResponseEntity.ok().body(result.toString());
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody RegisterRequest registerRequest) {
        if (registerUtil.login(registerRequest)) {
            String username = registerRequest.getUsername();
            User user = userService.getUserByUsername(username);
            long id_user = user.getId();
            ArrayList<Dot> resArr = (ArrayList<Dot>) dotService.getByUsername(id_user);
            ArrayList<String> result = new ArrayList<>();
            for (Dot element : resArr) {
                element.setX(DoubleRounder.round(element.getX(), 3));
                element.setY(DoubleRounder.round(element.getY(), 3));
                result.add(gson.toJson(element, Dot.class));
            }
            return ResponseEntity.ok().body(result.toString());
        }
        ArrayList<String> res = new ArrayList<>();
        res.add("null");
        return ResponseEntity.ok().body(res.toString());
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterRequest registerRequest) {
        if (registerUtil.reg(registerRequest)) {
            User user = User
                    .builder()
                    .username(registerRequest.getUsername())
                    .password(SHA384Coder
                            .encryptPassword(registerRequest.getPassword()))
                    .build();
            userService.addUser(user);
            return ResponseEntity.ok().body("success");
        }
        else return ResponseEntity.badRequest().body("fail");
    }

    @GetMapping("/clients")
    public List<User> getUsers() {
        return userService.getAll();
    }

//    @GetMapping
//    public String index(@ModelAttribute("user") User user) {
//        return "sign_in";
//    }
//
//    @GetMapping("/sign_up")
//    public String getSignUp (@ModelAttribute("user") User user) {
//        return "sign_up";
//    }
//
//    @PostMapping("/sign_in")
//    public String signIn (@ModelAttribute("user") User user, Model model) {
//        String username = user.getUsername();
//        User dbUser = userService.getUserByUsername(username);
//
//        // username doesn't exist
//        if (dbUser == null) {
//            return "sign_in";
//        }
//        String dbPassword = dbUser.getPassword();
//        String password = user.getPassword();
//        String encryptedPassword = SHA384Coder.encryptPassword(password);
//
//        // password doesn't suit
//        if (!dbPassword.equals(encryptedPassword)) {
//            return "sign_in";
//        }
//        user.setPassword(encryptedPassword);
//        model.addAttribute("user", user);
//        return "/main";
//    }
//
//    @PostMapping("/sign_up")
//    public String singUp (@ModelAttribute User user, Model model) {
//        String username = user.getUsername();
//
//        if (userService.getUserByUsername(username) != null) {
//            return "sign_up";
//        }
//
//        String password = user.getPassword();
//        String encryptedPassword = SHA384Coder.encryptPassword(password);
//        user.setPassword(encryptedPassword);
//        model.addAttribute("user", user);
//        userService.addUser(user);
//        return "/main";
//    }
}
