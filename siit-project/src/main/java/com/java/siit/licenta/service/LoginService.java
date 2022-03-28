package com.java.siit.licenta.service;


import com.java.siit.licenta.domain.entity.LoginEntity;
import com.java.siit.licenta.mapper.LoginDTOToLoginEntity;
import com.java.siit.licenta.mapper.LoginEntityToLoginDTOWithoutPfa;
import com.java.siit.licenta.repository.LoginRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

import com.java.siit.licenta.domain.model.LoginDTO;
import org.springframework.transaction.annotation.Transactional;


@Service
@AllArgsConstructor
public class LoginService {

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private final LoginRepository loginRepository;


    private final LoginDTOToLoginEntity loginDTOToLoginEntity;

    private final LoginEntityToLoginDTOWithoutPfa loginEntityToLoginDTOWithoutPfa;


    public LoginEntity create(LoginEntity loginEntity) {
        loginEntity.setPassword(bCryptPasswordEncoder.encode(loginEntity.getPassword()));
        return loginRepository.save(loginEntity);
    }

    public void save(LoginEntity loginEntity) { loginRepository.save(loginEntity);}


    public LoginDTO findById(long id) {
        return loginEntityToLoginDTOWithoutPfa.convert(loginRepository.findById(id));}

    @Transactional
    public void update(LoginDTO loginDTO) {
        loginDTO.setPassword(bCryptPasswordEncoder.encode(loginDTO.getPassword()));
        LoginEntity convert = loginDTOToLoginEntity.convert(loginDTO);
        loginRepository.save(convert);
    }
}
