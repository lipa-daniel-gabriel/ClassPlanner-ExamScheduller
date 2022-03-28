package com.java.siit.licenta.service;

import com.java.siit.licenta.domain.entity.*;
import com.java.siit.licenta.domain.model.LoginDTO;
import com.java.siit.licenta.mapper.LoginDTOToLoginEntity;
import com.java.siit.licenta.repository.StudentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@AllArgsConstructor
public class StudentService {


    private final StudentRepository studentRepository;
    private final TeacherService teacherService;
    private final SearchService searchService;
    private final UniversitySubjectService universitySubjectService;
    private final SubjectService subjectService;
    private final LoginService loginService;
    private final LoginDTOToLoginEntity loginDTOToLoginEntity;


    public void createStudent(LoginEntity loginEntity) {
        studentRepository.save(StudentEntity.builder()
                .id(loginEntity.getId())
                .name(loginEntity.getFirstName() + " " + loginEntity.getLastName())
                .group(loginEntity.getLoginGroup())
                .serie(loginEntity.getLoginSeries())
                .studentEmail(loginEntity.getEmail())
                .build());
    }

    public List<StudentEntity> findAll() {
        return studentRepository.findAll();
    }

    public List<TeacherEntity> findTheacherComparingSearchedName(Long id) {

        List<TeacherEntity> teacherEntityList = teacherService.findAll();
        SearchEntity actualSearch = searchService.findById(id);
        List<TeacherEntity> filteredTeacherList = new ArrayList<>();
        for (int i = 0; i < teacherEntityList.size(); i++)
            if ((teacherEntityList.get(i).getFirstName() + " " + teacherEntityList.get(i).getLastName()).equals(actualSearch.getSearchedInfo()))
                filteredTeacherList.add(teacherEntityList.get(i));
            else
                System.out.println("nothing found");
        return filteredTeacherList;
    }

    public List<UniversitySubjectEntity> findUniversitySubjectByTeacherId() {
        List<TeacherEntity> teacherEntityList = teacherService.findAll();
        List<UniversitySubjectEntity> universitySubjectEntityList = universitySubjectService.findAll();
        List<UniversitySubjectEntity> filteredUniversitySubjectList = new ArrayList<>();
        for (int i = 0; i < universitySubjectEntityList.size(); i++)
            if (universitySubjectEntityList.get(i).getTeacherId() == teacherEntityList.get(0).getId()) {
                filteredUniversitySubjectList.add(universitySubjectEntityList.get(i));
            } else System.out.println("Nothing found in university list");
        return filteredUniversitySubjectList;
    }

    public List<SubjectEntity> findSubjects() {
        List<SubjectEntity> subjectEntityList = subjectService.findAll();
        return subjectEntityList;
    }


    public void studentValidation(Long id) {
        LoginDTO loginDTO = loginService.findById(id);
        List<StudentEntity> studentEntityList = findAll();
        if (String.valueOf(studentEntityList).contains(String.valueOf(loginDTO.getEmail()))) {
            System.out.println("Already Here");
        } else {
            createStudent(Objects.requireNonNull(loginDTOToLoginEntity.convert(loginDTO)));
        }
    }

    public void searchByName(Long id, Model model) {
        LoginDTO loginDTO = loginService.findById(id);
        long value = 0;
        List<StudentEntity> studentEntityList = findAll();
        String fullName = loginDTO.getFirstName() + " " + loginDTO.getLastName();

        for (int i = 0; i < studentEntityList.size(); i++) {
            if (studentEntityList.get(i).getName().contains(fullName)) {
                model.addAttribute("idStudent", studentEntityList.get(i).getId());
            }
        }
    }
}
