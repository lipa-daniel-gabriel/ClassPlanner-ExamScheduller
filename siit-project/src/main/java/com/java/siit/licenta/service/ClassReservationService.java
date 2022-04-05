package com.java.siit.licenta.service;


import com.java.siit.licenta.domain.entity.ClassReservationEntity;
import com.java.siit.licenta.domain.entity.ExamEntity;
import com.java.siit.licenta.domain.entity.SubjectEntity;
import com.java.siit.licenta.repository.ClassReservationRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.SQLIntegrityConstraintViolationException;

@Service
@AllArgsConstructor
public class ClassReservationService {

    private final ClassReservationRepository classReservationRepository;

    public void classRoomReservation(SubjectEntity subjectEntity,String universitySubject) throws SQLIntegrityConstraintViolationException {
        classReservationRepository.save(ClassReservationEntity.builder()
                .classReservationDate(subjectEntity.getCalendarDate())
                .classReservationTime(subjectEntity.getTimeSlot())
                .classReservationSubject(universitySubject)
                .build());
        throw new SQLIntegrityConstraintViolationException("Already reserved");
    }
    public void classRoomReservation(ExamEntity examEntity) {
        classReservationRepository.save(ClassReservationEntity.builder()
                .classReservationDate(examEntity.getExamDay())
                .classReservationTime(examEntity.getExamTime())
                .classReservationSubject(examEntity.getExamDiscipline())
                .build());
    }
}
