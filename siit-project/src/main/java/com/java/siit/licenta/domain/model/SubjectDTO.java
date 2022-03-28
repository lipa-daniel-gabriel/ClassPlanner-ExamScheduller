package com.java.siit.licenta.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SubjectDTO {

    private long subjectId;

    private String group;

    private String schoolYear;

    private String series;

    private String classroom;

    private String typeOfCourse;

    private String timeSlot;

    private String calendarDate;

    private Long universitySubjectIdFk;
}
