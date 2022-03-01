package ru.kata.spring.boot_security.demo.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.Dao.RoleRepository;
import ru.kata.spring.boot_security.demo.Dao.UserRepository;
import ru.kata.spring.boot_security.demo.Model.User;
import java.util.List;



@Service
@Transactional
public class UserService {

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    UserRepository userRepository;
/*
    @PostConstruct
    private void postConstruct() {
        Role adminRole = new Role("ROLE_ADMIN");
        Role userRole = new Role("ROLE_USER");
        roleRepository.save(adminRole);
        roleRepository.save(userRole);
        HashSet<Role> adminSet= new HashSet<>();
        adminSet.add(adminRole);
        HashSet<Role> userSet = new HashSet<>();
        userSet.add(userRole);

        User admin = new User("Viktor", "Gromyako", 45, "viktor@gmail.com", "admin");
        admin.setRoles(adminSet);
        User normalUser = new User("Vasiliy", "Mutniy", 33, "vasiliy@gmail.com", "user");
        normalUser.setRoles(userSet);
        addUser(admin);
        addUser(normalUser);
        }
        */



    public List<User> getUsers() {
        return userRepository.findAll();
    }


    public void addUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }


    public User findUser(Long id) {
        return userRepository.findById(id).get();
    }


    public void editUser(User updatedUser) {
        updatedUser.setPassword(passwordEncoder.encode(updatedUser.getPassword()));
        userRepository.save(updatedUser);
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

}