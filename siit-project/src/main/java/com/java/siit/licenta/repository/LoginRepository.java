package com.java.siit.licenta.repository;


import com.java.siit.licenta.domain.entity.LoginEntity;
import com.java.siit.licenta.domain.entity.TeacherEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import org.springframework.data.jpa.repository.Query;

import java.util.List;


@Repository
public interface LoginRepository extends JpaRepository<LoginEntity, Long> {

    LoginEntity getByEmail(String email);

    @Query("SELECT l FROM LoginEntity l WHERE l.id = :id")
    LoginEntity findById(long id);


    @Query("SELECT l from LoginEntity l")
    List<LoginEntity> getAll();

}
