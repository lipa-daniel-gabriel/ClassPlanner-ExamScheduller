package com.java.siit.licenta.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class StudentDTO {

    private long id;
    private String name;
    private String group;
    private String serie;
    private String email;

}
