package com.java.siit.licenta.mapper;


import com.java.siit.licenta.domain.entity.LoginEntity;
import com.java.siit.licenta.domain.model.LoginDTO;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;


@Component
public class LoginDTOToLoginEntity implements Converter<LoginDTO, LoginEntity> {
    @Override
    public LoginEntity convert(LoginDTO loginDTO) {
        return LoginEntity.builder()
                .id(loginDTO.getId())
                .lastName(loginDTO.getLastName())
                .firstName(loginDTO.getFirstName())
                .email(loginDTO.getEmail())
                .password(loginDTO.getPassword())
                .enabled(loginDTO.getEnabled())
                .typeOfUser(loginDTO.getTypeOfUser())
                .build();
    }
}

