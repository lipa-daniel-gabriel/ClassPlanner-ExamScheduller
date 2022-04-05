package com.java.siit.licenta.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "exams")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ExamEntity {

    @Id
    @Column(name = "id_Exams")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long examId;

    @Column(name = "exam_series")
    private String examSeries;

    @Column(name = "exam_day")
    private String examDay;

    @Column(name = "exam_month")
    private String examMonth;

    @Column(name = "exam_group")
    private String examGroup;

    @Column(name = "exam_discipline")
    private String examDiscipline;

    @Column(name = "exam_classroom")
    private String examClassroom;

    @Column(name = "exam_time")
    private String examTime;

    @Column(name = "exam_type")
    private String examType;

    @Column(name = "exam_allotted_time")
    private String examAllottedTime;

    @Column(name = "teacher_id")
    private Long teacherIdExam;
}
