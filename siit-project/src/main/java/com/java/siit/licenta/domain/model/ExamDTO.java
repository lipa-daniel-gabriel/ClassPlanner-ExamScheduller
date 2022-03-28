package com.java.siit.licenta.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ExamDTO {


    private long examId;

    private String examSeries;

    private String examDay;

    private String examMonth;

    private String examGroup;

    private String examDiscipline;

    private String examClassroom;

    private String examTime;

    private String examType;

    private String examAllottedTime;

    private long teacherIdExam;
}
