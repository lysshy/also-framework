package com.also.admin.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SecurityController {

    @GetMapping("/admin")
    public Object admin() {
        return "admin";
    }

    @GetMapping("/user")
    public Object user() {
        return "user";
    }
}
