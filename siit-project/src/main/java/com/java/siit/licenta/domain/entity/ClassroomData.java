package com.java.siit.licenta.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "classroom_data_management")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ClassroomData {

    @Id
    @Column(name = "id_class_data")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long examDataId;

    @Column(name = "classroom_name")
    private String classroomDataName;

    @Column(name = "classroom_number_of_person_allowed")
    private String classroomDataNumberOfPersonAllowed;

    @OneToMany(mappedBy = "classroomData")
    private Set<DateEntity> timeSlotsSet;

}
