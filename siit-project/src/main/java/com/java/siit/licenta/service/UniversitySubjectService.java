package com.java.siit.licenta.service;

import com.java.siit.licenta.domain.entity.UniversitySubjectEntity;
import com.java.siit.licenta.repository.UniversitySubjectRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UniversitySubjectService {

    @Autowired
    private final UniversitySubjectRepository universitySubjectRepository;

    public void createSubject(UniversitySubjectEntity universitySubjectEntity, Long id) {
        universitySubjectRepository.save(UniversitySubjectEntity.builder()
                .universitySubjectId(universitySubjectEntity.getUniversitySubjectId())
                .universitySubject(universitySubjectEntity.getUniversitySubject())
                .teacherId(id)
                .build());
    }

    public List<UniversitySubjectEntity> findAll() {
        return universitySubjectRepository.findAll();
    }

    public UniversitySubjectEntity getById(Long id) {
        return universitySubjectRepository.getById(id);
    }

    public void delete(long universitySubjectId) {
        universitySubjectRepository.deleteById(universitySubjectId);
    }

    public String findUniversitySubjectById(Long id) {
        UniversitySubjectEntity acutualUniversitySubject = universitySubjectRepository.getById(id);
        return acutualUniversitySubject.getUniversitySubject();
    }
}
