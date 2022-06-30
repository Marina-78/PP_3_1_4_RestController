package ru.kata.spring.boot_security.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.kata.spring.boot_security.model.User;
import ru.kata.spring.boot_security.service.UserService;

import java.util.List;


@RestController
@RequestMapping(value = "/api")
public class AdminRestController {
    private UserService userService;

    @Autowired
    public AdminRestController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/admin")
    public ResponseEntity<?> allUsers() {
        List<User> users = userService.getAllUsers();
        return users != null && !users.isEmpty()
                ? new ResponseEntity<>(users, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/admin/{id}")
    public ResponseEntity<?> selectUser(@PathVariable Long id) {
        return ResponseEntity.ok().body(userService.getUser(id));
    }


    @PostMapping("/create")
    public ResponseEntity<?> createUser(User user, @RequestParam("roles") List<String> roles) {
        userService.saveUser(user, roles);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/edit")
    public ResponseEntity<?> updateUser(User user, @RequestParam("roles") List<String> roles) {
        userService.editUser(user, roles);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable("id") Long id) {
        userService.removeUser(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
