package ru.kata.spring.boot_security.demo.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.ExceptionHandling.NoSuchUserException;
import ru.kata.spring.boot_security.demo.ExceptionHandling.WrongUserData;
import ru.kata.spring.boot_security.demo.Model.Role;
import ru.kata.spring.boot_security.demo.Model.User;
import ru.kata.spring.boot_security.demo.Service.UserService;

import java.util.List;

@RestController
@RequestMapping("/api")
public class RestAdminController {

    @Autowired
    UserService userService;

    @GetMapping("/users")
    public List<User> getUsers(){
        return userService.getUsers();
    }

    @GetMapping("/{id}")
    public User getUser(@PathVariable("id") long id) {
        User user = userService.findUser(id);
        if (user == null) {
            throw new NoSuchUserException("there is no user with id " + id);
        }
        return user;
    }

    @ExceptionHandler
    public ResponseEntity<WrongUserData> handleNoSuchUserException(NoSuchUserException exception){
        WrongUserData data = new WrongUserData();
        data.setInfo(exception.getMessage());
        return new ResponseEntity<>(data, HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<User> addUser(@RequestBody User user){
        userService.addUser(user);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<User> patchUser(@RequestBody User user){
        userService.editUser(user);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @DeleteMapping ("/{id}")
    public void delete(@PathVariable("id") Long id) {
        userService.deleteUser(id);
    }


    @GetMapping("/thisUser")
    @ResponseBody
    public ResponseEntity<User> currentClient(@AuthenticationPrincipal User user) {
        return new ResponseEntity<>(userService.findUser(user.getId()), HttpStatus.OK) ;
    }

    @GetMapping("/userInfo")
    @ResponseBody
    public String roles(@AuthenticationPrincipal User user) {
        String roles = new String();
        for(Role r: user.getRoles()){
            roles += r.toString() + " ";
        }
        return roles;
    }
}

