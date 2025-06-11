package com.edu.chatsystem.controller;

import com.edu.chatsystem.configuration.Constants;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(Constants.API_URL)
public class HomeController {
    public String home() {
        return "Hello, World!";
    }
}