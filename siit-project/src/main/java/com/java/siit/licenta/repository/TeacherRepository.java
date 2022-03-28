package com.java.siit.licenta.repository;

import com.java.siit.licenta.domain.entity.TeacherEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TeacherRepository extends JpaRepository<TeacherEntity, Long> {
    List<TeacherEntity> findAll();

    TeacherEntity deleteTeacherEntityById(int id);

    TeacherEntity getTeacherEntityById(Long id);

    TeacherEntity getByEmail(String email);

}
