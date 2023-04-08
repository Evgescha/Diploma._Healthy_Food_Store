package com.hescha.healthystore.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class IndexController {
    @GetMapping
    public String getPage() {
        return "index";
    }

    @GetMapping("/about")
    public String getPageabout() {
        return "about";
    }

    @GetMapping("/404")
    public String getPage404() {
        return "404";
    }

    @GetMapping("/feature")
    public String getPagefeature() {
        return "feature";
    }

    @GetMapping("/testimonial")
    public String getPagetestimonial() {
        return "testimonial";
    }
}
