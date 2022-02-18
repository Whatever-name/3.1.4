package ru.kata.spring.boot_security.demo.Dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_security.demo.Model.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    public Role getRoleByName(String name);
}
