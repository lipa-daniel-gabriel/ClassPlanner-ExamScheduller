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

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private final UserRolesService userRolesService;




    @GetMapping("/login")
    public String login() {
        return "/login";
    }



    @RequestMapping("/teacher/edit/{id}")
    public ModelAndView editTeacher(@PathVariable(name = "id") int id) throws NotFoundException {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String emailTemp = service.findById(id).getEmail();
        String email;
        if (principal instanceof UserDetails) {
            email = ((UserDetails) principal).getUsername();
        } else {
            email = principal.toString();

        }

        if (email.equals(emailTemp)) {

            ModelAndView mav = new ModelAndView("edit_user");
            LoginDTO login = service.findById(id);
            String a = login.getPassword();
            login.setPassword(bCryptPasswordEncoder.encode(a));
            mav.addObject("login", login);

            return mav;
        } else {
            ModelAndView modelAndView = new ModelAndView("blank");
            TeacherEntity teacherEntity = new TeacherEntity();
            modelAndView.addObject("teacherEntity", teacherEntity);
            return modelAndView;
        }
    }

    @RequestMapping("/student/edit/{id}")
    public ModelAndView editStudent(@PathVariable(name = "id") int id) throws NotFoundException {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String emailTemp = service.findById(id).getEmail();
        String email;
        if (principal instanceof UserDetails) {
            email = ((UserDetails) principal).getUsername();
        } else {
            email = principal.toString();

        }

        if (email.equals(emailTemp)) {

            ModelAndView mav = new ModelAndView("edit_user");
            LoginDTO login = service.findById(id);
            String a = login.getPassword();
            login.setPassword(bCryptPasswordEncoder.encode(a));
            mav.addObject("login", login);

            return mav;
        } else {
            ModelAndView modelAndView = new ModelAndView("blank");
            TeacherEntity teacherEntity = new TeacherEntity();
            modelAndView.addObject("teacherEntity", teacherEntity);
            return modelAndView;
        }
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
    public ModelAndView create(LoginEntity loginEntity, BindingResult bindingResult, ModelMap modelMap) {

        loginEntity.setEnabled(true);

        ModelAndView modelAndView = new ModelAndView();
        if (bindingResult.hasErrors()) {
            modelAndView.addObject("successMessage", "Please correct the errors in form!");
            modelMap.addAttribute("bindingResult", bindingResult);
        } else {

            LoginEntity loginEntity1 = service.create(loginEntity);


            modelAndView.addObject("successMessage", "User is registered successfully!");
        }
        modelAndView.addObject("loginEntity", new LoginEntity());
        userRolesService.create(loginEntity);
        modelAndView.setViewName("register");
        return modelAndView;
    }
}
