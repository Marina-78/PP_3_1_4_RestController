package ru.kata.spring.boot_security.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.kata.spring.boot_security.model.User;
import ru.kata.spring.boot_security.service.UserService;


@Controller
public class PageController {
    private final UserService userService;

    @Autowired
    public PageController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String loginIn() {
        return "index";
    }

    @GetMapping("/admin")
    public String getPageAdmin(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();
        model.addAttribute("user", user);
        model.addAttribute("userNew", new User());
        model.addAttribute("users", userService.getAllUsers());
        return "admin";
    }

    @GetMapping("/user")
    public String getPageUser(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();
        model.addAttribute("user", user);
        return "user";
    }
}