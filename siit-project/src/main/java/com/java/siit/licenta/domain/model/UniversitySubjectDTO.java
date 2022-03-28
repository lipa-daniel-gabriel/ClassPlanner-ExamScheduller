package com.java.siit.licenta.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UniversitySubjectDTO {
    private long id;
    private String universitySubject;
    private long teacherId;
}
