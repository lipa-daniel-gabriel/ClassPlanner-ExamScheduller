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
@Table(name = "university_subject")
public class UniversitySubjectEntity {

    @Id
    @Column(name ="id_university_subject")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long universitySubjectId;

    @Column(name = "subject")
    private String universitySubject;

    @Column(name ="teacher_id")
    private long teacherId;

}
