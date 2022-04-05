package com.java.siit.licenta.controller;


import com.java.siit.licenta.domain.entity.*;
import com.java.siit.licenta.mapper.LoginDTOToLoginEntity;
import com.java.siit.licenta.repository.SubjectRepository;
import com.java.siit.licenta.service.*;
import lombok.AllArgsConstructor;
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

@Controller
@RequestMapping("/student/{id}")
@AllArgsConstructor
public class StudentController {

    private final LoginService loginService;
    private final StudentService studentService;
    private final SearchService searchService;
    private final ExamService examService;


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


    @GetMapping("/studentEdit")
    public ModelAndView finalTouches(@PathVariable("id") Long id, Model model) {

        studentService.studentValidation(id);
        studentService.searchForExistingData(id);
        ModelAndView modelAndView = new ModelAndView();
        StudentEntity studentEntity = new StudentEntity();
        modelAndView.setViewName("finalTouches");
        studentService.searchByName(id, model);
        modelAndView.addObject("studentService", studentEntity);
        return modelAndView;
    }

    @PostMapping("/saveStudent")
    public RedirectView saveStudent(@PathVariable("id") Long neededId, StudentEntity studentEntity) {
        StudentEntity studentEntity1 = studentService.getById(neededId);
        List<LoginEntity> loginEntities = loginService.listAll();
        for (int i = 0; i < loginEntities.size(); i++) {
            if ((loginEntities.get(i).getFirstName() + " " + loginEntities.get(i).getLastName()).contains(studentEntity1.getName())) {
                System.out.println(loginEntities.get(i));

                studentService.updateStudent(loginEntities.get(i), neededId, studentEntity);
                studentService.updateStudentLoginAcc(loginEntities.get(i), studentEntity);
            }
        }
        return new RedirectView("http://localhost:8080/student/" + neededId + "/studentEdit");
    }

    @PostMapping("/saveSearch")
    public RedirectView saveSearch(@PathVariable("id") Long id, SearchEntity searchEntity) {
        searchService.createSearch(searchEntity, id);
        List<SearchEntity> searchEntityList = searchService.findAll();
        SearchEntity lastElement = searchEntityList.get(searchEntityList.size() - 1);
        return new RedirectView("http://localhost:8080/student/" + lastElement.getSearchId() + "/teacherInfo");
    }


    @GetMapping("/finalSetup")
    public ModelAndView finalSetupInput(@PathVariable("id") Long id) {
        ModelAndView modelAndView = new ModelAndView();
        studentService.studentValidation(id);
        studentService.searchForExistingData(id);
        return modelAndView;
    }

    @GetMapping("/allExamsPlanning")
    public ModelAndView allExamsSeenByStudents() {
        List<ExamEntity> examEntityList = new ArrayList<>();
        ModelAndView modelAndView = new ModelAndView();
        examEntityList = examService.findAll();
        modelAndView.addObject("examEntityList",examEntityList);
        modelAndView.setViewName("allExamsPlanningForStudents");

        return modelAndView;
    }


    /**
     * Inceput cautare curs STUDENT in functie de GRUPA
     */


    @GetMapping("/searchGroup")
    public ModelAndView searchCourse(@PathVariable("id") Long id, Model model) {
        ModelAndView modelAndView = new ModelAndView();
        SearchEntity searchEntity = new SearchEntity();
        modelAndView.addObject("searchEntity", searchEntity);
        modelAndView.setViewName("studentSearchGroup");
        model.addAttribute("idStudent", id);
        return modelAndView;
    }

    @PostMapping("/saveSearchedGroup")
    public RedirectView saveSearchGroup(@PathVariable("id") Long id, SearchEntity searchEntity) {
        searchService.createSearch(searchEntity, id);
        List<SearchEntity> searchEntityList = searchService.findAll();
        SearchEntity lastElement = searchEntityList.get(searchEntityList.size() - 1);
        return new RedirectView("http://localhost:8080/student/" + lastElement.getSearchId() + "/groupInfo");
    }

    @GetMapping("/groupInfo")
    public ModelAndView groupInfo(@PathVariable("id") Long id) {
        ModelAndView modelAndView = new ModelAndView();
        studentService.findGroupByName(id);
        modelAndView.addObject("coursesByGroup", studentService.findGroupByName(id));
        modelAndView.setViewName("coursesByGroup");
        return modelAndView;
    }

    /**
     * Finalizare cautare CURS in functie de numele GRUPEI
     */


    /**
     * Inceput cautare curs STUDENT in functie de MATERIE
     */

    @GetMapping("/searchCourse")
    public ModelAndView searchGroup(@PathVariable("id") Long id, Model model) {
        ModelAndView modelAndView = new ModelAndView();
        SearchEntity searchEntity = new SearchEntity();
        modelAndView.addObject("searchEntity", searchEntity);
        modelAndView.setViewName("studentSearchCourse");
        model.addAttribute("idStudent", id);
        return modelAndView;
    }

    @PostMapping("/saveSearchCourse")
    public RedirectView saveSearchCourse(@PathVariable("id") Long id, SearchEntity searchEntity) {
        searchService.createSearch(searchEntity, id);
        List<SearchEntity> searchEntityList = searchService.findAll();
        SearchEntity lastElement = searchEntityList.get(searchEntityList.size() - 1);
        return new RedirectView("http://localhost:8080/student/" + lastElement.getSearchId() + "/courseInfo");
    }

    @GetMapping("/courseInfo")
    public ModelAndView courseInfo(@PathVariable("id") Long id) {
        ModelAndView modelAndView = new ModelAndView();
        studentService.findCourseByName(id);
        modelAndView.addObject("coursesByName", studentService.findCourseByName(id));
        modelAndView.setViewName("coursesByName");
        return modelAndView;
    }

    /**
     * Finalizare cautare CURS in functie de numele materiei
     */


    @PostMapping("/saveSearchSortedByGroups")
    public RedirectView saveSearchSortedByGroups(@PathVariable("id") Long id, SearchEntity searchEntity) {
        searchService.createSearch(searchEntity, id);
        List<SearchEntity> searchEntityList = searchService.findAll();
        SearchEntity lastElement = searchEntityList.get(searchEntityList.size() - 1);
        return new RedirectView("http://localhost:8080/student/" + lastElement.getSearchId() + "/groupInfo");
    }

    @GetMapping("/teacherInfo")
    public ModelAndView teacherInfo(@PathVariable("id") Long id) {
        ModelAndView modelAndView = new ModelAndView();

        studentService.findTheacherComparingSearchedName(id);
        modelAndView.addObject("filteredTeacherList", studentService.findTheacherComparingSearchedName(id));
        modelAndView.setViewName("filteredTeacherList");

        studentService.findUniversitySubjectByTeacherId(id);
        modelAndView.addObject("filteredUniversitySubjectList", studentService.findUniversitySubjectByTeacherId(id));
        modelAndView.setViewName("filteredTeacherList");

        studentService.findSubjects(id);
        modelAndView.addObject("filteredSubjectList", studentService.findSubjects(id));
        modelAndView.setViewName("filteredTeacherList");

        return modelAndView;
    }

}
