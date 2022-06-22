package ru.kata.spring.boot_security.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.kata.spring.boot_security.model.User;
import ru.kata.spring.boot_security.service.UserService;

import java.util.List;


@RestController
@RequestMapping(value = "/rest")
public class PageRestController {
    private UserService userService;

    @Autowired
    public PageRestController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/user")
    public User getUserInfo() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return (User) authentication.getPrincipal();
    }

    @GetMapping("/admin")
    public ResponseEntity<?> allUsers() {
        List<User> users = userService.getAllUsers();
        return users != null && !users.isEmpty()
                ? new ResponseEntity<>(users, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/admin/{id}")
    public User selectUser(@PathVariable Long id) {
        return userService.getUser(id);
    }

    @PostMapping("/create")
    public void createUser(User user, @RequestParam("roles") List<String> roles) {
        userService.saveUser(user, roles);
    }

    @PostMapping("/edit")
    public void updateUser(User user, @RequestParam("roles") List<String> roles) {
        userService.editUser(user, roles);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteUser(@PathVariable("id") Long id) {
        userService.removeUser(id);
    }
}
