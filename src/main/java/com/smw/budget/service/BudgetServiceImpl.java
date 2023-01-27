package com.smw.budget.service;

import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.smw.budget.domain.model.entity.Budget;
import com.smw.budget.domain.model.entity.Distribution;
import com.smw.budget.domain.model.entity.Tag;
import com.smw.budget.domain.persistence.BudgetRepository;
import com.smw.budget.domain.service.BudgetService;
import com.smw.budget.domain.service.DistributionService;
import com.smw.budget.domain.service.TagService;
import com.smw.record.domain.model.entity.Report;
import com.smw.record.domain.service.ReportService;
import com.smw.shared.exception.ResourceNotFoundException;
import com.smw.shared.exception.ResourceValidationException;

import io.swagger.v3.oas.annotations.tags.Tags;

@Service
public class BudgetServiceImpl implements BudgetService {

    private static final String ENTITY = "Budget";
    private final BudgetRepository repository;
    private final Validator validator;
    private final DistributionService distributionService;
    private final TagService tagService;
    private final ReportService reportService;

    public BudgetServiceImpl(BudgetRepository repository, Validator validator, DistributionService distributionService,
            TagService tagService, ReportService reportService) {
        this.repository = repository;
        this.validator = validator;
        this.distributionService = distributionService;
        this.tagService = tagService;
        this.reportService = reportService;
    }

    @Override
    public List<Budget> getAll() {
        return repository.findAll();
    }

    @Override
    public Budget getById(Long id) {
        return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException(ENTITY, id));
    }

    private Double calculateReportIncome(Double incomeTotal, Double percent) {
        return incomeTotal * percent / 100;
    }

    private Report generateReport(Tag tag, Budget budget) {

        String name = tag.getName() + " report";
        Double income = calculateReportIncome(budget.getIncome().getMount(), tag.getPercent());
        Double initialExpendidure = 0.0;
        Double saving = income - initialExpendidure;

        Report report = new Report();

        report.setName(name);
        report.setMonthlyExpenditure(initialExpendidure);
        report.setMonthlyBudget(income);
        report.setMonthlySaving(saving);
        report.setTag(tag);
        report.setBudget(budget);

        return report;

    }

    private void createReports(Budget budget) {

        Distribution distribution = budget.getDistribution();

        List<Tag> tags = tagService.getByDistributionId(distribution.getId());

        for (Tag tag : tags) {
            Report report = generateReport(tag, budget);
            reportService.create(report);
        }

    }

    // TODO: Test Create Budget and Reports
    @Override
    @Transactional
    public Budget create(Budget request) {

        Set<ConstraintViolation<Budget>> violations = validator.validate(request);

        if (!violations.isEmpty())
            throw new ResourceValidationException(ENTITY, violations);

        if (distributionService.getById(request.getDistribution().getId()) == null)
            throw new ResourceNotFoundException(ENTITY, request.getDistribution().getId());

        Budget budget = repository.save(request);

        createReports(budget);

        return budget;
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
