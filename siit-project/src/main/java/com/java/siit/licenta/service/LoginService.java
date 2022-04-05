package com.java.siit.licenta.service;


import com.java.siit.licenta.domain.entity.LoginEntity;
import com.java.siit.licenta.domain.entity.StudentEntity;
import com.java.siit.licenta.domain.entity.TeacherEntity;
import com.java.siit.licenta.domain.model.LoginDTO;
import com.java.siit.licenta.mapper.LoginDTOToLoginEntity;
import com.java.siit.licenta.mapper.LoginEntityToLoginDTOWithoutPfa;
import com.java.siit.licenta.repository.LoginRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;


@Service
@AllArgsConstructor
public class LoginService {

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private final LoginRepository loginRepository;
    private final UserRolesService userRolesService;
    private final LoginDTOToLoginEntity loginDTOToLoginEntity;
    private final LoginEntityToLoginDTOWithoutPfa loginEntityToLoginDTOWithoutPfa;


    public LoginEntity create(LoginEntity loginEntity) {
        loginEntity.setPassword(bCryptPasswordEncoder.encode(loginEntity.getPassword()));

        return loginRepository.save(loginEntity);

    }

    public List<LoginEntity> listAll() {
        return loginRepository.findAll();
    }

    public void save(LoginEntity loginEntity) {
        loginRepository.save(loginEntity);
    }

    public LoginEntity get(Long id) {
        return loginRepository.findById(id).get();
    }

    public void delete(Long id) {
        loginRepository.deleteById(id);
    }


    public List<LoginEntity> getAllUsersWithBusiness() {
        return loginRepository.getAll();
    }


    public LoginDTO findById(long id) {

        return loginEntityToLoginDTOWithoutPfa.convert(loginRepository.findById(id));

    }

    @Transactional
    public void update(LoginDTO loginDTO) {
        loginDTO.setPassword(bCryptPasswordEncoder.encode(loginDTO.getPassword()));
        LoginEntity convert = loginDTOToLoginEntity.convert(loginDTO);
        loginRepository.save(convert);
    }


    public ModelAndView createLoginEntity(LoginEntity loginEntity, BindingResult bindingResult, ModelMap modelMap) {
        loginEntity.setEnabled(true);

        ModelAndView modelAndView = new ModelAndView();
        if (bindingResult.hasErrors()) {
            modelAndView.addObject("successMessage", "Please correct the errors in form!");
            modelMap.addAttribute("bindingResult", bindingResult);
        } else {
            LoginEntity loginEntity1 = create(loginEntity);
            modelAndView.addObject("successMessage", "User is registered successfully!");
        }
        modelAndView.addObject("loginEntity", new LoginEntity());
        userRolesService.create(loginEntity);
        modelAndView.setViewName("register");
        return modelAndView;
    }

    public ModelAndView emailValidatorAndEditorForTeacher(int id) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String emailTemp = findById(id).getEmail();
        String email;
        if (principal instanceof UserDetails) {
            email = ((UserDetails) principal).getUsername();
        } else {
            email = principal.toString();
        }
        if (email.equals(emailTemp)) {

            ModelAndView mav = new ModelAndView("edit_user");
            LoginDTO login = findById(id);
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

    public ModelAndView emailValidatorAndEditorForStudent(int id) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String emailTemp = findById(id).getEmail();
        String email;
        if (principal instanceof UserDetails) {
            email = ((UserDetails) principal).getUsername();
        } else {
            email = principal.toString();

        }

        if (email.equals(emailTemp)) {

            ModelAndView mav = new ModelAndView("edit_user");
            LoginDTO login = findById(id);
            String a = login.getPassword();
            login.setPassword(bCryptPasswordEncoder.encode(a));
            mav.addObject("login", login);

            return mav;
        } else {
            ModelAndView modelAndView = new ModelAndView("blank");
            StudentEntity studentEntity = new StudentEntity();
            modelAndView.addObject("studentEntity", studentEntity);
            return modelAndView;
        }
    }
}
