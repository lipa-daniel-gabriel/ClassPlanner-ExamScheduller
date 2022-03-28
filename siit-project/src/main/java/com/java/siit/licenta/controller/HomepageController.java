package com.java.siit.licenta.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping
@AllArgsConstructor
public class HomepageController {

    @GetMapping("/homepage")
    public ModelAndView homepage() {
        ModelAndView modelAndView = new ModelAndView("homepage2");
        return modelAndView;
    }
}
