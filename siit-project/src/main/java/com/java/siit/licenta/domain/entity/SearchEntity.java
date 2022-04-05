package com.java.siit.licenta.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Entity
@Table(name = "student_search")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SearchEntity {

    @Id
    @Column(name ="id_student_search")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long searchId;

    @Column(name = "searched_info")
    private String searchedInfo;

    @Column(name = "student_id")
    private long studentIdFk;
}
