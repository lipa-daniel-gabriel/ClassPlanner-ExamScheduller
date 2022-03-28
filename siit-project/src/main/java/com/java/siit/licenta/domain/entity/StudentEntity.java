package com.java.siit.licenta.domain.entity;

import com.java.siit.licenta.domain.model.TeacherDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.core.convert.converter.Converter;

import javax.persistence.*;
import javax.validation.constraints.Email;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "student")

public class StudentEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "student_name")
    private String name;

    @Column(name = "student_group")
    private String group;

    @Column(name= "student_series")
    private String serie;

    @Email
    @Column(name ="student_email")
    private String studentEmail;


}
