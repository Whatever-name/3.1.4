package ru.kata.spring.boot_security.demo.Model;
import javax.validation.constraints.Size;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.Set;

@Entity
@Table(name = "users")
public class User implements UserDetails {

    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;

    @Column
    private int age;

    @Column
    @Size(min = 4, max = 15)
    private String userName;

    @Column
    private String name;

    @Column
    private String department;

    @Column
    @Size(min = 5, max = 20)
    private String password;

    @Column
    @ManyToMany(cascade = {CascadeType.REFRESH,CascadeType.REMOVE})
    @JoinTable(joinColumns= {@JoinColumn(name="user_id")},
            inverseJoinColumns= {@JoinColumn(name="role_id")})
    private Set<Role> roles;

    public User() {
    }

    public User(int age, @Size(min = 4, max = 15) String userName, String name, String department, @Size(min = 5, max = 20) String password) {
        this.age = age;
        this.userName = userName;
        this.name = name;
        this.department = department;
        this.password = password;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String username) {
        this.userName = username;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public void setUsername(String userName) {
        this.userName = userName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return getRoles();
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return userName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}