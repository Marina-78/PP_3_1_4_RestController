package ru.kata.spring.boot_security.demo.UserDao;

import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;

public interface UserDao {

    List<User> getAllUsers();

    void saveUser(User user);

    User getUser(Long id);

    void editUser(User user);

    void removeUser(Long id);

    User getUserByEmail(String email);

    List<Role> getAllRoles();

}
