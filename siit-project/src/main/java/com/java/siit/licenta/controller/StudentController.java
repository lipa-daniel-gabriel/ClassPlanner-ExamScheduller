package com.java.siit.licenta.controller;


import com.java.siit.licenta.domain.entity.*;
import com.java.siit.licenta.domain.model.LoginDTO;
import com.java.siit.licenta.mapper.LoginDTOToLoginEntity;
import com.java.siit.licenta.repository.StudentRepository;
import com.java.siit.licenta.repository.SubjectRepository;
import com.java.siit.licenta.repository.TeacherRepository;
import com.java.siit.licenta.service.*;
import lombok.AllArgsConstructor;
import org.dom4j.rule.Mode;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Controller
@RequestMapping("/student/{id}")
@AllArgsConstructor
public class StudentController {

    private final StudentService studentService;
    private final SearchService searchService;


    @GetMapping()
    public ModelAndView studentPage(@PathVariable("id") Long id, Model model) {

        ModelAndView modelAndView = new ModelAndView();
        studentService.studentValidation(id);
        studentService.searchByName(id, model);
        SearchEntity searchEntity = new SearchEntity();
        modelAndView.addObject("searchEntity", searchEntity);
        modelAndView.setViewName("searchEntity");
        modelAndView.setViewName("student");
        return modelAndView;
    }


    @PostMapping("/saveSearch")
    public RedirectView saveSearch(@PathVariable("id") Long id, SearchEntity searchEntity) {
        searchService.createSearch(searchEntity, id);
        List<SearchEntity> searchEntityList = searchService.findAll();
        SearchEntity lastElement = searchEntityList.get(searchEntityList.size() - 1);
        return new RedirectView("http://localhost:8080/student/" + lastElement.getSearchId() + "/teacherInfo");
    }

    @PostMapping("/saveSearchCourse")
    public RedirectView saveSearchCourse(@PathVariable("id") Long id, SearchEntity searchEntity) {
        searchService.createSearch(searchEntity, id);
        List<SearchEntity> searchEntityList = searchService.findAll();
        SearchEntity lastElement = searchEntityList.get(searchEntityList.size() - 1);
        return new RedirectView("http://localhost:8080/student/" + lastElement.getSearchId() + "/courseInfo");
    }

    @PostMapping("/saveSearchGroup")
    public RedirectView saveSearchGroup(@PathVariable("id") Long id, SearchEntity searchEntity) {
        searchService.createSearch(searchEntity, id);
        List<SearchEntity> searchEntityList = searchService.findAll();
        SearchEntity lastElement = searchEntityList.get(searchEntityList.size() - 1);
        return new RedirectView("http://localhost:8080/student/" + lastElement.getSearchId() + "/groupInfo");
    }

    @GetMapping("/courseInfo")
    public ModelAndView courseInfo(@PathVariable("id") Long id) {
        ModelAndView modelAndView = new ModelAndView();

        studentService.findTheacherComparingSearchedName(id);
        modelAndView.addObject("filteredTeacherList", studentService.findTheacherComparingSearchedName(id));
        modelAndView.setViewName("filteredTeacherList");

        studentService.findUniversitySubjectByTeacherId();
        modelAndView.addObject("filteredUniversitySubjectList", studentService.findUniversitySubjectByTeacherId());
        modelAndView.setViewName("filteredTeacherList");

        studentService.findSubjects();
        modelAndView.addObject("filteredSubjectList", studentService.findSubjects());
        modelAndView.setViewName("filteredTeacherList");

        return modelAndView;
    }

    @GetMapping("/groupInfo")
    public ModelAndView groupInfo(@PathVariable("id") Long id) {
        ModelAndView modelAndView = new ModelAndView();

        studentService.findTheacherComparingSearchedName(id);
        modelAndView.addObject("filteredTeacherList", studentService.findTheacherComparingSearchedName(id));
        modelAndView.setViewName("filteredTeacherList");

        studentService.findUniversitySubjectByTeacherId();
        modelAndView.addObject("filteredUniversitySubjectList", studentService.findUniversitySubjectByTeacherId());
        modelAndView.setViewName("filteredTeacherList");

        studentService.findSubjects();
        modelAndView.addObject("filteredSubjectList", studentService.findSubjects());
        modelAndView.setViewName("filteredTeacherList");

        return modelAndView;
    }

    @GetMapping("/teacherInfo")
    public ModelAndView teacherInfo(@PathVariable("id") Long id) {
        ModelAndView modelAndView = new ModelAndView();

        studentService.findTheacherComparingSearchedName(id);
        modelAndView.addObject("filteredTeacherList", studentService.findTheacherComparingSearchedName(id));
        modelAndView.setViewName("filteredTeacherList");

        studentService.findUniversitySubjectByTeacherId();
        modelAndView.addObject("filteredUniversitySubjectList", studentService.findUniversitySubjectByTeacherId());
        modelAndView.setViewName("filteredTeacherList");

        studentService.findSubjects();
        modelAndView.addObject("filteredSubjectList", studentService.findSubjects());
        modelAndView.setViewName("filteredTeacherList");

        return modelAndView;
    }
}
