package com.java.siit.licenta.service;

import com.java.siit.licenta.config.UserRoles;
import com.java.siit.licenta.domain.entity.LoginEntity;
import com.java.siit.licenta.repository.UserRolesRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserRolesService {
    @Autowired
    private final UserRolesRepository userRoleRepository;

    public UserRoles create(LoginEntity loginEntity){
        UserRoles userRole = new UserRoles(loginEntity.getEmail(), loginEntity.getTypeOfUser());
        return userRoleRepository.save(userRole);
    }
}
