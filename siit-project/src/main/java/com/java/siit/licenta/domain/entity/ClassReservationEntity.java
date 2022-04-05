package com.java.siit.licenta.domain.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "class_reservation")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ClassReservationEntity {
    @Id
    @Column(name = "id_class_reservation")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idClassReservation;

    @Column(name = "class_reservation_date")
    private String classReservationDate;

    @Column(name = "class_reservation_time")
    private String classReservationTime;

    @Column(name = "class_reservation_subject")
    private String classReservationSubject;
}
