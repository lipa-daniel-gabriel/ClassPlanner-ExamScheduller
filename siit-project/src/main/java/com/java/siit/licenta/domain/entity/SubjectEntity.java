package com.java.siit.licenta.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "class_organizer")
public class SubjectEntity {

    @Id
    @Column(name = "id_class_organizer")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long subjectId;

    @Column(name = "school_subject")
    private String schoolSubject;

    @Column(name = "school_group")
    private String group;

    @Column(name = "school_year")
    private String schoolYear;

    @Column(name = "school_series")
    private String series;

    @Column(name = "classroom")
    private String classroom;

    @Column(name = "type_of_course")
    private String typeOfCourse;

    @Column(name = "time_slot")
    private String timeSlot;

    @Column(name = "calendar_date")
    private String calendarDate;

    @Column(name = "university_subject_id_university_subject")
    private long universitySubjectIdFk;

}
