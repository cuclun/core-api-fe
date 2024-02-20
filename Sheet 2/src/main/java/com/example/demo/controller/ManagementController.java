package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ManagementController {

    @GetMapping({"/admin"})
    public String admin() {
        return "Admin";
    }

    @GetMapping({"/user"})
    public String user() {
        return "User";
    }

}
