package ru.kata.spring.boot_security.demo.Service;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.Dao.RoleRepository;
import ru.kata.spring.boot_security.demo.Dao.UserRepository;
import ru.kata.spring.boot_security.demo.Model.Role;
import ru.kata.spring.boot_security.demo.Model.User;

import javax.annotation.PostConstruct;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Service
@Transactional
public class UserService {

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    UserRepository userRepository;

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

        User admin = new User(26, "admin", "Vitalya", "IT", "admin");
        admin.setRoles(adminSet);
        User normalUser = new User(33, "user", "Vasiliy", "HR", "user");
        normalUser.setRoles(userSet);
        addUser(admin);
        addUser(normalUser);
    }


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