package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.kata.spring.boot_security.demo.repositories.UserRepository;

import java.security.Principal;

@Controller
@RequestMapping("/user")
public class UserController {

    private final UserRepository userRepository;

    @Autowired
    public UserController(UserRepository userRepository) {

        this.userRepository = userRepository;
    }

    @GetMapping
    public String showUserPage(@AuthenticationPrincipal UserDetails userDetails, Model model, Principal principal) {
        model.addAttribute("currentUser", userRepository.findByUsername(principal.getName()).get());
        model.addAttribute("userDetails", userDetails);
        return "user_pages/user_page";
    }
}
