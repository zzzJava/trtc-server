package com.zzz.videoserver.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    @GetMapping(path = "cors")
    public String testCorsGet() {
        return "Cors Get Test Success!";
    }

    @PostMapping(path = "cors")
    public String testCorsPost() {
        return "Cors Post Test Success!";
    }
}
