package ru.kata.spring.boot_security.demo.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.Dao.RoleRepository;
import ru.kata.spring.boot_security.demo.Model.Role;
import java.util.List;

@Service
@Transactional
public class RoleService {

    @Autowired
    RoleRepository roleRepository;

    public Role getRoleById(Long id){
        return roleRepository.getById(id);
    }

    public List <Role> getAllRoles(){
        return roleRepository.findAll();
    }

    public void addRole(Role role){
       roleRepository.save(role);
    }

    public Role getRoleByName(String name){return roleRepository.getRoleByName(name);}



}
