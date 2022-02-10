package ru.kata.spring.boot_security.demo.Controller;



import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.kata.spring.boot_security.demo.Model.User;



@Controller
public class UserController {

    @RequestMapping("/user")
    public String getUser(@AuthenticationPrincipal User user, Model model ){
        model.addAttribute("userById", user);
        return "user";
    }
}
