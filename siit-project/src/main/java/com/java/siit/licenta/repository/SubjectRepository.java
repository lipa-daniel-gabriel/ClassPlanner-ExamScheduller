package com.java.siit.licenta.repository;

import com.java.siit.licenta.domain.entity.SubjectEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface SubjectRepository extends JpaRepository<SubjectEntity, Long> {

    List<SubjectEntity> getAllByTypeOfCourse(String typeOfCourse);

    SubjectEntity getById(Long id);

    @Query("SELECT l FROM SubjectEntity l WHERE l.universitySubjectIdFk = :universitySubjectIdFk")
    List<SubjectEntity> findByUnivestitySubejctId(long universitySubjectIdFk);
}
