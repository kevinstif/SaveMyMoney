package com.smw.record.service;

import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.springframework.stereotype.Service;

import com.smw.record.domain.model.entity.Report;
import com.smw.record.domain.persistence.ReportRepository;
import com.smw.record.domain.service.ReportService;
import com.smw.shared.exception.ResourceNotFoundException;
import com.smw.shared.exception.ResourceValidationException;

@Service
public class ReportServiceImpl implements ReportService {

    private static final String ENTITY = "report";
    private final ReportRepository repository;
    private final Validator validator;

    public ReportServiceImpl(ReportRepository repository, Validator validator) {
        this.repository = repository;
        this.validator = validator;
    }

    @Override
    public List<Report> getByBudgetId(Long budgetId) {
        return repository.findByBudgetId(budgetId);
    }

    @Override
    public Report getById(Long id) {
        return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException(ENTITY, id));
    }

    @Override
    public Report create(Report request) {

        Set<ConstraintViolation<Report>> violations = validator.validate(request);

        if ( !violations.isEmpty())
            throw new ResourceValidationException(ENTITY, violations);

        return repository.save(request);
    }

}
