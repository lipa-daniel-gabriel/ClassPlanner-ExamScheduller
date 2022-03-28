package com.java.siit.licenta.mapper;

import com.java.siit.licenta.domain.entity.LoginEntity;
import com.java.siit.licenta.domain.model.LoginDTO;
import lombok.AllArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;


@Component
@AllArgsConstructor
public class LoginEntityToLoginDTOWithoutPfa implements Converter<LoginEntity, LoginDTO> {
    @Override
    public LoginDTO convert(LoginEntity loginEntity) {
        return LoginDTO.builder()
                .email(loginEntity.getEmail())
                .lastName(loginEntity.getLastName())
                .firstName(loginEntity.getFirstName())
                .enabled(loginEntity.getEnabled())
                .id(loginEntity.getId())
                .password(loginEntity.getPassword())
                .typeOfUser(loginEntity.getTypeOfUser())
                .build();
    }


}


