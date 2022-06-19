package ru.kata.spring.boot_security.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.kata.spring.boot_security.model.User;
import ru.kata.spring.boot_security.service.UserService;

import java.util.List;


@Controller
@RequestMapping(value = "/admin")
public class AdminController {
    private final UserService userService;

    @Autowired
    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String allUsers(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();
        model.addAttribute("user", user);
        model.addAttribute("users", userService.getAllUsers());
        return "admin";
    }

    @GetMapping("/{id}")
    public String getUser(@PathVariable("id") Long id, Model model) {
        model.addAttribute("user", userService.getUser(id));
        return "redirect:/admin";
    }
    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") Long id) {
        userService.removeUser(id);
        return "redirect:/admin";
    }

    @PostMapping
    public String createUser(@ModelAttribute("user") User user,
                             @RequestParam("roles") List<String> roles) {
        userService.saveUser(user, roles);
        return "redirect:/admin";
    }

    @PostMapping("/edit/{id}")
    public String updateUser(@ModelAttribute("user") User user,
                             @RequestParam("roles") List<String> roles) {
        userService.editUser(user, roles);
        return "redirect:/admin";
    }
}