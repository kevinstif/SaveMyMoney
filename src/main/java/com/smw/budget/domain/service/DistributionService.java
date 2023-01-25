package com.smw.budget.domain.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.smw.budget.domain.model.entity.Distribution;

public interface DistributionService {
    List<Distribution> getAll();

    Distribution getById(Long id);

    Distribution create(Distribution request);

    Distribution update(Long id, Distribution request);

    ResponseEntity<?> delete(Long id);

}
