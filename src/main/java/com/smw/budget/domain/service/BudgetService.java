package com.smw.budget.domain.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.smw.budget.domain.model.entity.Budget;

public interface BudgetService {
    List<Budget> getAll(Long userId);
    Budget getById(Long id);
    Budget create(Budget request);
    Budget update(Long id, Budget request);
    ResponseEntity<?> delete(Long id);
}
