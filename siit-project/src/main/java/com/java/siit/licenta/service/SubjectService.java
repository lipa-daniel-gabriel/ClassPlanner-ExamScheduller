package com.java.siit.licenta.service;

import com.java.siit.licenta.domain.entity.SubjectEntity;
import com.java.siit.licenta.repository.SubjectRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class SubjectService {

    private final SubjectRepository subjectRepository;
    private final UniversitySubjectService universitySubjectService;


    public void createSubject(SubjectEntity subjectEntity, Long id) {
        subjectRepository.save(SubjectEntity.builder()
                .schoolSubject(universitySubjectService.findUniversitySubjectById(id))
                .subjectId(subjectEntity.getSubjectId())
                .group(subjectEntity.getGroup())
                .schoolYear(subjectEntity.getSchoolYear())
                .series(subjectEntity.getSeries())
                .classroom(subjectEntity.getClassroom())
                .typeOfCourse(subjectEntity.getTypeOfCourse())
                .timeSlot(subjectEntity.getTimeSlot())
                .calendarDate(subjectEntity.getCalendarDate())
                .universitySubjectIdFk(id)
                .build());
    }

    public List<SubjectEntity> findAllByTypeOfCours(String typeOfCourse) {
        return subjectRepository.getAllByTypeOfCourse(typeOfCourse);
    }


    public SubjectEntity getById(Long id) {
        return subjectRepository.getById(id);
    }

    public void delete(Long id) {
        subjectRepository.deleteById(id);
    }

    public List<SubjectEntity> findAll() {
        return subjectRepository.findAll();
    }

    public List<SubjectEntity> getByUniversitySubjectId(long universitySubjectId) {
        return subjectRepository.findByUnivestitySubejctId(universitySubjectId);
    }
}
