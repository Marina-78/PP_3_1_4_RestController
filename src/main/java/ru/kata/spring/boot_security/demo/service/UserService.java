package ru.kata.spring.boot_security.demo.service;

import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;

public interface UserService {
    List<User> getAllUsers();

    void saveUser(User user, List<String> roles);

    User getUser(Long id);

    void editUser(User user, List<String> roles);

    void removeUser(Long id);

    User getUserByEmail(String email);

    List<Role> getAllRoles();
}
