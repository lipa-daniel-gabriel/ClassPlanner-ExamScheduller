package com.java.siit.licenta.repository;

import com.java.siit.licenta.domain.entity.ClassroomData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClassroomDataRepository extends JpaRepository<ClassroomData, Long> {
}