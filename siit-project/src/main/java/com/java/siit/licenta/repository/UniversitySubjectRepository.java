package com.java.siit.licenta.repository;

import com.java.siit.licenta.domain.entity.TeacherEntity;
import com.java.siit.licenta.domain.entity.UniversitySubjectEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UniversitySubjectRepository extends JpaRepository<UniversitySubjectEntity, Long> {

}
