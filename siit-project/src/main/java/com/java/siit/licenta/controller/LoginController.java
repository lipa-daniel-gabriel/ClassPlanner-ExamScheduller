package com.java.siit.licenta.controller;


import com.java.siit.licenta.domain.entity.LoginEntity;
import com.java.siit.licenta.domain.entity.TeacherEntity;
import com.java.siit.licenta.service.LoginService;
import com.java.siit.licenta.service.UserRolesService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import com.java.siit.licenta.domain.model.LoginDTO;
import javassist.NotFoundException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;


@Controller
@RequestMapping
@AllArgsConstructor
public class LoginController {

    @Autowired
    private final LoginService service;
    private final LoginService loginService;


    @GetMapping("/login")
    public String login() {
        return "/login";
    }


    @RequestMapping("/teacher/edit/{id}")
    public void editTeacher(@PathVariable(name = "id") int id) throws NotFoundException {
        loginService.emailValidatorAndEditorForTeacher(id);
    }

    @RequestMapping("/student/edit/{id}")
    public void editStudent(@PathVariable(name = "id") int id) throws NotFoundException {
        loginService.emailValidatorAndEditorForStudent(id);
    }

    @GetMapping("/register")
    public ModelAndView register() {

        ModelAndView modelAndView = new ModelAndView();
        LoginEntity loginEntity = new LoginEntity();
        modelAndView.addObject("loginEntity", loginEntity);
        modelAndView.setViewName("register");
        return modelAndView;
    }


    @PutMapping(value = "/teacher/update")
    public RedirectView editUser(@ModelAttribute("login") LoginDTO loginDTO) throws NotFoundException {

        service.update(loginDTO);
        return new RedirectView("http://localhost:8080/teacher/edit/" + loginDTO.getId());

    }

    @PostMapping("/register")
    public void create(LoginEntity loginEntity, BindingResult bindingResult, ModelMap modelMap) {
        loginService.createLoginEntity(loginEntity, bindingResult, modelMap);
    }
}
