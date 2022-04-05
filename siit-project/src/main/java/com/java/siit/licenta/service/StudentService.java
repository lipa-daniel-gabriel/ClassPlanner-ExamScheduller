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

    public void updateStudent(LoginEntity loginEntity, Long neededId, StudentEntity studentEntity) {
        studentRepository.save(StudentEntity.builder()
                .id(neededId)
                .name(loginEntity.getFirstName() + " " + loginEntity.getLastName())
                .group(studentEntity.getGroup())
                .serie(studentEntity.getSerie())
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
            if ((teacherEntityList.get(i).getFirstName() + " " + teacherEntityList.get(i).getLastName() + ",").contains(actualSearch.getSearchedInfo())) {
                filteredTeacherList.add(teacherEntityList.get(i));
            }
        return filteredTeacherList;
    }

    public List<String> findUniversitySubjectByTeacherId(Long id) {

        List<String> filteredUniversitySubjectList = new ArrayList<>();
        List<UniversitySubjectEntity> universitySubjectEntityList = universitySubjectService.findAll();
        SearchEntity lastElement = searchService.findById(id);
        List<TeacherEntity> teacherEntityList = teacherService.findAll();

        for (int i = 0; i < teacherEntityList.size(); i++) {
            String fullName = teacherEntityList.get(i).getFirstName() + " " + teacherEntityList.get(i).getLastName() + ",";
            if (lastElement.getSearchedInfo().contains(fullName)) {
                for (int j = 0; j < universitySubjectEntityList.size(); j++) {
                    if (teacherEntityList.get(i).getId() == universitySubjectEntityList.get(j).getTeacherId()) {
                        filteredUniversitySubjectList.add(universitySubjectEntityList.get(j).getUniversitySubject());
                    }
                }
            } else System.out.println("Nothing here");
        }
        return filteredUniversitySubjectList;
    }

    public List<SubjectEntity> findSubjects(Long id) {

        List<SubjectEntity> subjectEntityList = new ArrayList<>();
        List<SubjectEntity> actualsubjectEntityList = new ArrayList<>();
        SearchEntity lastSearchedEntity = searchService.findById(id);
        List<UniversitySubjectEntity> universitySubjectEntityList = universitySubjectService.findAll();
        List<TeacherEntity> teacherEntityList = teacherService.findAll();

        for (int i = 0; i < teacherEntityList.size(); i++) {
            String fullName = teacherEntityList.get(i).getFirstName() + " " + teacherEntityList.get(i).getLastName() + ",";
            System.out.println(fullName);
            if (lastSearchedEntity.getSearchedInfo().contains(fullName)) {
                for (int j = 0; j < universitySubjectEntityList.size(); j++) {
                    if (universitySubjectEntityList.get(j).getTeacherId() == teacherEntityList.get(i).getId()) {
                        subjectEntityList = subjectService.getByUniversitySubjectId(universitySubjectEntityList.get(j).getUniversitySubjectId());
                        for (int v = 0; v < subjectEntityList.size(); v++) {
                            actualsubjectEntityList.add(subjectEntityList.get(v));
                        }
                    }
                }
            }
        }
        return actualsubjectEntityList;
    }

    public StudentEntity getById(Long id) {
        return studentRepository.getById(id);
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
        List<StudentEntity> studentEntityList = findAll();
        String fullName = loginDTO.getFirstName() + " " + loginDTO.getLastName();
        for (int i = 0; i < studentEntityList.size(); i++) {
            if (studentEntityList.get(i).getName().contains(fullName)) {
                model.addAttribute("idStudent", studentEntityList.get(i).getId());
            }
        }
    }

    public String searchForExistingData(Long id) {
        String redirectUrlByCondition = null;
        LoginDTO loginDTO = loginService.findById(id);
        List<StudentEntity> studentEntityList = findAll();
        String fullName = loginDTO.getFirstName() + " " + loginDTO.getLastName();
        for (int i = 0; i < studentEntityList.size(); i++) {
            boolean configurated;
            boolean notConfigurated;
            if (studentEntityList.get(i).getName().contains(fullName)) {
                if (studentEntityList.get(i).getGroup() == null && studentEntityList.get(i).getSerie() == null) {
                    configurated = false;
                    notConfigurated = true;
                    redirectUrlByCondition = returnUrlByBoolean(configurated, notConfigurated, redirectUrlByCondition, id);
                    break;
                } else notConfigurated = false;
                configurated = true;
                redirectUrlByCondition = returnUrlByBoolean(configurated, notConfigurated, redirectUrlByCondition, id);
                break;
            }
        }
        return redirectUrlByCondition;
    }

    private String returnUrlByBoolean(boolean configurated, boolean notConfigurated, String redirectUrlByCondition, Long id) {
        LoginDTO loginDTO = loginService.findById(id);
        long value = 0;
        List<StudentEntity> studentEntityList = findAll();
        String fullName = loginDTO.getFirstName() + " " + loginDTO.getLastName();
        for (int i = 0; i < studentEntityList.size(); i++) {
            if (studentEntityList.get(i).getName().contains(fullName)) {
                if (configurated) {
                    redirectUrlByCondition = "http://localhost:8080/student/" + studentEntityList.get(i).getId();
                } else if (notConfigurated) {
                    redirectUrlByCondition = "http://localhost:8080/student/" + studentEntityList.get(i).getId() + "/studentEdit";
                }
            }
        }
        return redirectUrlByCondition;
    }

    public void updateStudentLoginAcc(LoginEntity loginEntity, StudentEntity studentEntity) {
        loginEntity.setLoginSeries(studentEntity.getSerie());
        loginEntity.setLoginGroup(studentEntity.getGroup());
    }

    public List<SubjectEntity> findCourseByName(Long id) {
        SearchEntity actualSearch = searchService.findById(id);
        List<SubjectEntity> subjectEntityListSortdedByCourse = new ArrayList<>();
        List<SubjectEntity> subjectEntityList = subjectService.findAll();
        for (int i = 0; i < subjectEntityList.size(); i++) {
            if (subjectEntityList.get(i).getSchoolSubject().contains(actualSearch.getSearchedInfo())) {
                subjectEntityListSortdedByCourse.add(subjectEntityList.get(i));
            }
        }
        return subjectEntityListSortdedByCourse;
    }

    public List<SubjectEntity> findGroupByName(Long id) {
        SearchEntity actualSearch = searchService.findById(id);
        List<SubjectEntity> subjectEntityListSortdedByGroup = new ArrayList<>();
        List<SubjectEntity> subjectEntityList = subjectService.findAll();
        for (int i = 0; i < subjectEntityList.size(); i++) {
            if (subjectEntityList.get(i).getGroup().contains(actualSearch.getSearchedInfo())) {
                subjectEntityListSortdedByGroup.add(subjectEntityList.get(i));
            }
        }
        return subjectEntityListSortdedByGroup;
    }
}
