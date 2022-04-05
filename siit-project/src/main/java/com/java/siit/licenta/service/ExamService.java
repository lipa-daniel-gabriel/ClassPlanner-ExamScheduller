package com.java.siit.licenta.service;

import com.java.siit.licenta.domain.entity.ExamEntity;
import com.java.siit.licenta.repository.ExamRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ExamService {

    private final ExamRepository examRepository;

    public void createExam(ExamEntity examEntity, Long id) {
        examRepository.save(ExamEntity.builder()
                .examId(examEntity.getExamId())
                .examSeries(examEntity.getExamSeries())
                .examDay(examEntity.getExamDay())
                .examMonth(examEntity.getExamMonth())
                .examGroup(examEntity.getExamGroup())
                .examDiscipline(examEntity.getExamDiscipline())
                .examClassroom(examEntity.getExamClassroom())
                .examTime(examEntity.getExamTime())
                .examType(examEntity.getExamType())
                .examAllottedTime(examEntity.getExamAllottedTime())
                .teacherIdExam(id)
                .build());
    }

    public List<ExamEntity> findAll() {
        return examRepository.findAll();
    }

    public ExamEntity getById(Long id) {
        return examRepository.getById(id);
    }

    public void delete(Long id) {
        examRepository.deleteById(id);
    }
}
