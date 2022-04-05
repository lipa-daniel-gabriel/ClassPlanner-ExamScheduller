package com.java.siit.licenta.service;

import com.java.siit.licenta.domain.entity.LoginEntity;
import com.java.siit.licenta.domain.entity.SubjectEntity;
import com.java.siit.licenta.domain.entity.TeacherEntity;
import com.java.siit.licenta.mapper.LoginDTOToLoginEntity;
import com.java.siit.licenta.repository.TeacherRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class TeacherService {

    private final TeacherRepository teacherRepository;
    private final SubjectService subjectService;
    private final LoginService loginService;
    private final LoginDTOToLoginEntity loginDTOToLoginEntity;
    private final UniversitySubjectService universitySubjectService;


    public void createTeacher(LoginEntity loginEntity) {
        teacherRepository.save(TeacherEntity.builder()
                .id(loginEntity.getId())
                .email(loginEntity.getEmail())
                .firstName(loginEntity.getFirstName())
                .lastName(loginEntity.getLastName())
                .typeOfUser(loginEntity.getTypeOfUser())
                .build());
    }


    public TeacherEntity get(Long id) {
        return teacherRepository.findById(id).orElse(null);
    }

    public List<TeacherEntity> findAll() {
        return teacherRepository.findAll();
    }

    public TeacherEntity deleteTeacherEntityById(Long id) {
        return teacherRepository.deleteTeacherEntityById(Math.toIntExact(id));
    }


    public TeacherEntity getTeacherEntityById(Long id) {
        return teacherRepository.getById(id);
    }

    public void saveEditedPlans(Long id, SubjectEntity subjectEntity) {
        List<SubjectEntity> subjectSeminaryList = subjectService.findAllByTypeOfCours("Seminar");
        List<SubjectEntity> subjectLaboratoryList = subjectService.findAllByTypeOfCours("Laborator");
        List<SubjectEntity> subjectCoursList = subjectService.findAllByTypeOfCours("Curs");

        SubjectEntity neededId = subjectService.getById(id);

        subjectService.createSubject(subjectEntity, neededId.getUniversitySubjectIdFk());
        if (subjectEntity.getTypeOfCourse().contains("Seminar")) {
            subjectSeminaryList.add(subjectEntity);
        } else if (subjectEntity.getTypeOfCourse().contains("Laborator")) {
            subjectLaboratoryList.add(subjectEntity);
        } else if (subjectEntity.getTypeOfCourse().contains("Curs")) {
            subjectCoursList.add(subjectEntity);
        }
    }

    public void savePlans(Long id, SubjectEntity subjectEntity) {
        List<SubjectEntity> subjectSeminaryList = subjectService.findAllByTypeOfCours("Seminar");
        List<SubjectEntity> subjectLaboratoryList = subjectService.findAllByTypeOfCours("Laborator");
        List<SubjectEntity> subjectCoursList = subjectService.findAllByTypeOfCours("Curs");

        subjectService.createSubject(subjectEntity, id);
        if (subjectEntity.getTypeOfCourse().contains("Seminar")) {
            subjectSeminaryList.add(subjectEntity);
        } else if (subjectEntity.getTypeOfCourse().contains("Laborator")) {
            subjectLaboratoryList.add(subjectEntity);
        } else if (subjectEntity.getTypeOfCourse().contains("Curs")) {
            subjectCoursList.add(subjectEntity);
        }
    }

}
