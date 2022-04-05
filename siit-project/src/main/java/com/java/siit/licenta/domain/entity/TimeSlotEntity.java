package com.java.siit.licenta.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "timeslots")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TimeSlotEntity {

    @ManyToOne
    @JoinColumn(name = "classroom_date_id_date", nullable = false)
    private DateEntity dateEntity;

    @Id
    @Column(name ="id_timeslot_and_seats")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long timeSlotAndSeatId;

    @Column(name = "classroom_available_time_slot")
    private String availableTimeSlotForClassroom;

}
