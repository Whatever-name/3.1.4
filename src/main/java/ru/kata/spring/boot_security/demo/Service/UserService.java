package ru.kata.spring.boot_security.demo.Service;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.Dao.UserRepository;
import ru.kata.spring.boot_security.demo.Model.User;
import java.util.List;


@Service
@Transactional
public class UserService {

    @Autowired
    PasswordEncoder passwordEncoder;


    @Autowired
    UserRepository userRepository;


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