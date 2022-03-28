package com.java.siit.licenta.mapper;

import com.java.siit.licenta.domain.entity.TeacherEntity;
import com.java.siit.licenta.domain.model.TeacherDTO;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class TeacherDTOToTeacherEntity implements Converter<TeacherDTO, TeacherEntity> {

    public TeacherEntity convert(TeacherDTO teacherDTO) {
        return TeacherEntity.builder()
                .id(teacherDTO.getId())
                .email(teacherDTO.getEmail())
                .firstName(teacherDTO.getFirstName())
                .lastName(teacherDTO.getLastName())
                .build();
    }
}
