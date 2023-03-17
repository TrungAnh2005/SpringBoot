package com.example.ontap4.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
public class HomeController {

    @GetMapping("/")
    public String showHomePage(Principal principal, Model model) {
        String loginName = (principal != null) ? principal.getName() : "";

        var authorities = SecurityContextHolder.getContext().getAuthentication().getAuthorities();

        for (var authority:authorities) {
            System.out.println(authority.getAuthority());
        }

        model.addAttribute("login_name", loginName);
        model.addAttribute("authorities", authorities);
        return "index";
    }
}
