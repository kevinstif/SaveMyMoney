package com.smw.budget.service;

import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.smw.budget.domain.model.entity.Budget;
import com.smw.budget.domain.persistence.BudgetRepository;
import com.smw.budget.domain.service.BudgetService;
import com.smw.shared.exception.ResourceNotFoundException;
import com.smw.shared.exception.ResourceValidationException;

@Service
public class BudgetServiceImpl implements BudgetService {

    private static final String ENTITY = "Budget";
    private final BudgetRepository repository;
    private final Validator validator;

    public BudgetServiceImpl(BudgetRepository repository, Validator validator) {
        this.repository = repository;
        this.validator = validator;
    }

    @Override
    public List<Budget> getAll() {
        return repository.findAll();
    }

    @Override
    public Budget getById(Long id) {
        return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException(ENTITY, id));
    }

    @Override
    public Budget create(Budget request) {

        Set<ConstraintViolation<Budget>> violations = validator.validate(request);

        if (!violations.isEmpty())
            throw new ResourceValidationException(ENTITY, violations);

        return repository.save(request);
    }

    @Override
    public Budget update(Long id, Budget request) {

        Set<ConstraintViolation<Budget>> violations = validator.validate(request);

        if (!violations.isEmpty())
            throw new ResourceValidationException(ENTITY, violations);

        return repository.findById(id).map(
                budget -> repository.save(budget
                        .withName(request.getName())
                        .withEndDate(request.getEndDate())
                        .withIncome(request.getIncome())
                        .withStartDate(request.getStartDate())))
                .orElseThrow(() -> new ResourceNotFoundException(ENTITY, id));
    }

    @Override
    public ResponseEntity<?> delete(Long id) {
        return repository.findById(id).map(
                budget -> {
                    repository.delete(budget);
                    return ResponseEntity.ok().build();
                }).orElseThrow(() -> new ResourceNotFoundException(ENTITY, id));
    }

}
