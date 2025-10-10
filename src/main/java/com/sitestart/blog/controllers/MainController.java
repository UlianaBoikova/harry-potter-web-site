package com.sitestart.blog.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 The main controller that runs the site
 */
@Controller
public class MainController {

    /**
     Returns main page of the site
     @return "harry-potter-main.html"
     */
    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("title", "Main List");
        return "harry-potter-main";
    }

}