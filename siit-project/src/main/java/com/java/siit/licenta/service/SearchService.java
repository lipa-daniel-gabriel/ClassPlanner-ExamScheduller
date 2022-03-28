package com.java.siit.licenta.service;

import com.java.siit.licenta.domain.entity.SearchEntity;
import com.java.siit.licenta.repository.SearchRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class SearchService {
    private final SearchRepository searchRepository;

    public void createSearch(SearchEntity searchEntity, Long id) {
        searchRepository.save(SearchEntity.builder()
                .searchId(searchEntity.getSearchId())
                .searchedInfo(searchEntity.getSearchedInfo())
                .studentIdFk(id)
                .build());
    }

    public SearchEntity findById(Long id) {
        return searchRepository.getById(id);
    }

    public List<SearchEntity> findAll() {
        return searchRepository.findAll();
    }
}
