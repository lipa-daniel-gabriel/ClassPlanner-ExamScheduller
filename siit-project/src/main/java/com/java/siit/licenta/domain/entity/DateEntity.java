package com.java.siit.licenta.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "classroom_date")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DateEntity {

    @ManyToOne
    @JoinColumn(name = "classroom_data_management_id_classrom_data", nullable = false)
    private ClassroomData classroomData;

    @Id
    @Column(name ="id_date")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long dateId;

    @Column(name = "date_for_classroom")
    private String availableDateForClassroom;

    @OneToMany(mappedBy = "dateEntity")
    private Set<TimeSlotEntity> timeSlotsSet;
}
