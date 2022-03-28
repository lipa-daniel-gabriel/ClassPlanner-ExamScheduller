package com.java.siit.licenta.controller;

import com.java.siit.licenta.domain.entity.*;
import com.java.siit.licenta.domain.model.LoginDTO;
import com.java.siit.licenta.domain.model.TeacherDTO;
import com.java.siit.licenta.mapper.LoginDTOToLoginEntity;
import com.java.siit.licenta.repository.LoginRepository;
import com.java.siit.licenta.repository.SubjectRepository;
import com.java.siit.licenta.repository.TeacherRepository;
import com.java.siit.licenta.repository.UniversitySubjectRepository;
import com.java.siit.licenta.service.*;
import com.mysql.cj.log.Log;
import javassist.NotFoundException;
import lombok.AllArgsConstructor;
import org.dom4j.rule.Mode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.persistence.Id;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Controller
@RequestMapping("/teacher/{id}")
@AllArgsConstructor
public class TeacherController {

    private final LoginService loginService;
    private final LoginRepository loginRepository;
    private final TeacherService teacherService;
    private final LoginDTOToLoginEntity loginDTOToLoginEntity;
    private final TeacherRepository teacherRepository;
    private final ExamService examService;
    private final UniversitySubjectService universitySubjectService;
    private final SubjectService subjectService;
    List<UniversitySubjectEntity> universitySubjectEntityList;


    @PostMapping()
    public void createTeacherEntity(LoginEntity loginEntity) {
        teacherService.createTeacher(loginEntity);
    }

    @GetMapping()
    public ModelAndView teacherPage(@PathVariable("id") Long id, Model model) {
        ModelAndView modelAndView = new ModelAndView();
        LoginDTO loginDTO = loginService.findById(id);
        List<TeacherEntity> teacherEntityList = teacherRepository.findAll();
        TeacherEntity teacherEntity = new TeacherEntity();
        if (String.valueOf(teacherEntityList).contains(String.valueOf(loginDTO.getEmail()))) {
            System.out.println("Already Here");
        } else {
            teacherService.createTeacher(Objects.requireNonNull(loginDTOToLoginEntity.convert(loginDTO)));
        }
        modelAndView.addObject("teacherEntity", teacherEntity);
        modelAndView.setViewName("teacher");
        UniversitySubjectEntity universitySubjectEntity = new UniversitySubjectEntity();
        modelAndView.addObject("universityEntity", universitySubjectEntity);
        modelAndView.setViewName("teacher");
        TeacherEntity actualTeacher = teacherRepository.getByEmail(loginDTO.getEmail());
        model.addAttribute("id", actualTeacher.getId());
        List<UniversitySubjectEntity> universitySubjectEntityList = universitySubjectService.findAll();
        modelAndView.addObject("universityEntityList", universitySubjectEntityList);
        return modelAndView;
    }


    @PostMapping("/save")
    public RedirectView addSubject(@PathVariable("id") Long id, UniversitySubjectEntity universitySubjectEntity) {

        universitySubjectService.createSubject(universitySubjectEntity, id);
        LoginEntity actualLogin = loginRepository.getByEmail(teacherRepository.getTeacherEntityById(id).getEmail());
        return new RedirectView("http://localhost:8080/teacher/" + actualLogin.getId());

    }

    @RequestMapping("/universitySubjectList")
    public ModelAndView showSubjectList() {
        ModelAndView modelAndView = new ModelAndView();
        List<UniversitySubjectEntity> universitySubjectEntityList = universitySubjectService.findAll();
        modelAndView.addObject("universitySubjectList", universitySubjectEntityList);
        modelAndView.setViewName("universitySubjectList");
        return modelAndView;
    }


    @RequestMapping("/universitySubjectDelete")
    public RedirectView deleteUniversitySubject(@PathVariable(name = "id") Long id) {


        UniversitySubjectEntity universitySubjectEntity = universitySubjectService.getById(id);
        universitySubjectService.delete(universitySubjectEntity.getUniversitySubjectId());
        return new RedirectView("http://localhost:8080/teacher/" + universitySubjectEntity.getTeacherId() + "/universitySubjectList");
    }

    @RequestMapping("/universitySubjectEdit")
    public ModelAndView editUniversitySubject(@PathVariable(name = "id") Long id) {

        ModelAndView modelAndView = new ModelAndView("universitySubjectEdit");
        UniversitySubjectEntity universitySubjectEntity = universitySubjectService.getById(id);
        modelAndView.addObject("universitySubjectEntity", universitySubjectEntity);
        return modelAndView;
    }

    @PostMapping("/saveEditedUniversitySubject")
    public RedirectView saveEditedUniversitySubject(@PathVariable("id") Long id, UniversitySubjectEntity universitySubjectEntity) {
        UniversitySubjectEntity universitySubject = universitySubjectService.getById(id);
        universitySubjectService.createSubject(universitySubjectEntity, universitySubject.getTeacherId());
        return new RedirectView("http://localhost:8080/teacher/" + universitySubject.getUniversitySubjectId() + "/universitySubjectList");
    }


    @PostMapping("/saveEditedExam")
    public RedirectView saveEditedExams(@PathVariable("id") Long id, ExamEntity examEntity) {
        ExamEntity neededId = examService.getById(id);
        System.out.println(neededId);
        examService.createExam(examEntity, neededId.getTeacherIdExam());
        return new RedirectView("http://localhost:8080/teacher/" + neededId.getTeacherIdExam() + "/scheduleExam");
    }

    @PostMapping("/savePlans")
    public RedirectView savePlans(@PathVariable("id") Long id, SubjectEntity subjectEntity) {
        teacherService.savePlans(id,subjectEntity);
        return new RedirectView("http://localhost:8080/teacher/" + id + "/classesPlanner");
    }


    @PostMapping("/saveEditedPlans")
    public RedirectView saveEditedPlans(@PathVariable("id") Long id, SubjectEntity subjectEntity) {

        teacherService.saveEditedPlans(id,subjectEntity);
        return new RedirectView("http://localhost:8080/teacher/" + id + "/seminarySchedule");
    }


    @GetMapping("/examPlanner")
    public ModelAndView examPlanner(@PathVariable("id") Long id, Model model) {
        ModelAndView modelAndView = new ModelAndView();
        ExamEntity examEntity = new ExamEntity();
        modelAndView.addObject("examEntity", examEntity);
        model.addAttribute("id", id);
        modelAndView.setViewName("examPlanner");
        return modelAndView;
    }

    @PostMapping("/saveExam")
    public RedirectView createExam(@PathVariable("id") Long id, ExamEntity examEntity) {
        examService.createExam(examEntity, id);
        return new RedirectView("http://localhost:8080/teacher/" + id + "/examPlanner");

    }

    @GetMapping("/scheduleExam")
    public ModelAndView scheduleExams() {
        ModelAndView modelAndView = new ModelAndView();
        List<ExamEntity> examEntityList = examService.findAll();
        modelAndView.addObject("examEntityList", examEntityList);
        modelAndView.setViewName("examEntityList");
        return modelAndView;
    }


    @RequestMapping("/scheduleExamEdit")
    public ModelAndView scheduleExamEdit(@PathVariable(name = "id") Long id) {
        ModelAndView modelAndView = new ModelAndView("examScheduleEdit");

        ExamEntity examEntity = examService.getById(id);
        modelAndView.addObject("examEntity", examEntity);
        return modelAndView;
    }


    @RequestMapping("/scheduleExamDelete")
    public RedirectView scheduleExamDelete(@PathVariable(name = "id") Long id) {

        examService.delete(id);
        return new RedirectView("http://localhost:8080/teacher/" + id + "/scheduleExam");
    }


    @GetMapping("/classesPlanner")
    public ModelAndView classPlanner(@PathVariable("id") Long id, Model model) {
        ModelAndView modelAndView = new ModelAndView();
        SubjectEntity subjectEntity = new SubjectEntity();
        modelAndView.addObject("subjectEntity", subjectEntity);
        model.addAttribute("id", id);
        modelAndView.setViewName("classesPlanner");
        List<SubjectEntity> subjectSeminaryList = subjectService.findAllByTypeOfCours("Seminar");
        List<SubjectEntity> subjectLaboratoryList = subjectService.findAllByTypeOfCours("Laborator");
        List<SubjectEntity> subjectCoursList = subjectService.findAllByTypeOfCours("Curs");
        modelAndView.addObject("subjectLaboratoryList", subjectLaboratoryList);
        modelAndView.addObject("subjectSeminaryList", subjectSeminaryList);
        modelAndView.addObject("subjectCoursList", subjectCoursList);
        return modelAndView;
    }


    @GetMapping("/examsSchedule")
    public ModelAndView viewExamsSchedule() {

        ModelAndView modelAndView = new ModelAndView();
        List<ExamEntity> examEntityList = examService.findAll();
        modelAndView.addObject("examEntityList", examEntityList);
        modelAndView.setViewName("examEntityList");
        return modelAndView;
    }

    @GetMapping("/coursSchedule")
    public ModelAndView viewCoursSchedule() {

        ModelAndView modelAndView = new ModelAndView();
        List<SubjectEntity> subjectCoursList = subjectService.findAllByTypeOfCours("Curs");
        modelAndView.addObject("subjectCoursList", subjectCoursList);
        modelAndView.setViewName("subjectCoursList");
        return modelAndView;
    }

    @GetMapping("/seminarySchedule")
    public ModelAndView viewSeminarySchedule() {

        ModelAndView modelAndView = new ModelAndView();
        List<SubjectEntity> subjectSeminaryList = subjectService.findAllByTypeOfCours("Seminar");
        modelAndView.addObject("subjectSeminaryList", subjectSeminaryList);
        modelAndView.setViewName("subjectSeminaryList");
        return modelAndView;
    }


    @GetMapping("/laboratorySchedule")
    public ModelAndView viewLaboratorySchedule() {

        ModelAndView modelAndView = new ModelAndView();
        List<SubjectEntity> subjectLaboratoryList = subjectService.findAllByTypeOfCours("Laborator");
        modelAndView.addObject("subjectLaboratoryList", subjectLaboratoryList);
        modelAndView.setViewName("subjectLaboratoryList");
        return modelAndView;
    }

    @RequestMapping("/scheduleEdit")
    public ModelAndView scheduleEdit(@PathVariable(name = "id") Long id) {
        ModelAndView modelAndView = new ModelAndView("editSubject");

        SubjectEntity subjectEntity = subjectService.getById(id);
        modelAndView.addObject("subjectEntity", subjectEntity);
        return modelAndView;
    }


    @RequestMapping("/scheduleDelete")
    public RedirectView scheduleDelete(@PathVariable(name = "id") Long id) {

        SubjectEntity subjectEntity = subjectService.getById(id);
        subjectService.delete(id);
        return new RedirectView("http://localhost:8080/teacher/" + id + "/seminarySchedule");
    }


}

