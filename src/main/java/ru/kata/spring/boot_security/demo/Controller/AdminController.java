package ru.kata.spring.boot_security.demo.Controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.Model.User;
import ru.kata.spring.boot_security.demo.Service.RoleService;
import ru.kata.spring.boot_security.demo.Service.UserService;
import java.util.List;


@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;


    @GetMapping()
    public String allUsers(ModelMap model){
        List<User> userList = userService.getUsers();
        model.addAttribute("allUsers", userList);
        return "adminUsersList";

    }

    @DeleteMapping ("/delete/{id}")
    public String delete(@PathVariable("id") Long id) {
        userService.deleteUser(id);
        return "redirect:/admin";
    }


    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap modelMap) {
        modelMap.addAttribute("updatedUser", userService.findUser(id));
        modelMap.addAttribute("roles", roleService.getAllRoles());
        return "edit";
    }


    @PatchMapping("/{id}")
    public String patchUser(@ModelAttribute("updatedUser") User user){
        userService.editUser(user);
        return "redirect:/admin";
    }

    @GetMapping("/add")
    public String newUser(@ModelAttribute("user") User user, ModelMap modelMap){
        modelMap.addAttribute("roles", roleService.getAllRoles());
        return "add";
    }

    @PostMapping("")
    public String addUser(@ModelAttribute("user") User user){
        userService.addUser(user);

        return "redirect:/admin";
    }
}
