package com.java.siit.licenta.mapper;

import com.java.siit.licenta.domain.entity.TeacherEntity;
import com.java.siit.licenta.domain.model.TeacherDTO;
import org.springframework.core.convert.converter.Converter;

public class TeacherEntityToTeacherDTO implements Converter<TeacherEntity, TeacherDTO> {

    @Override
    public TeacherDTO convert(TeacherEntity teacherEntity) {
        return TeacherDTO.builder()
                .id(teacherEntity.getId())
                .email(teacherEntity.getEmail())
                .firstName(teacherEntity.getFirstName())
                .lastName(teacherEntity.getLastName())
                .build();
    }
}
