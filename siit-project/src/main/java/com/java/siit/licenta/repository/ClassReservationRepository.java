package com.java.siit.licenta.repository;

import com.java.siit.licenta.domain.entity.ClassReservationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClassReservationRepository extends JpaRepository<ClassReservationEntity, Long> {

}
