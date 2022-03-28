package com.java.siit.licenta.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SearchDTO {

    private long searchId;
    private String searchedInfo;
    private long studentIdFk;
}
