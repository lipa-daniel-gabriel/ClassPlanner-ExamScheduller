package com.java.siit.licenta.repository;

import com.java.siit.licenta.config.UserRoles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRolesRepository extends JpaRepository<UserRoles, String> {
    UserRoles getByEmail(String email);

}

