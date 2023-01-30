package com.smw.record.service;

import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.springframework.stereotype.Service;

import com.smw.budget.domain.model.entity.Budget;
import com.smw.budget.domain.model.entity.Distribution;
import com.smw.budget.domain.model.entity.Tag;
import com.smw.budget.domain.service.TagService;
import com.smw.record.domain.model.entity.Report;
import com.smw.record.domain.persistence.ReportRepository;
import com.smw.record.domain.service.ReportService;
import com.smw.shared.exception.ResourceNotFoundException;
import com.smw.shared.exception.ResourceValidationException;

@Service
public class ReportServiceImpl implements ReportService {

    private static final String ENTITY = "report";
    private static final Double INITIAL_VALUE = 0.0;
    private final ReportRepository repository;
    private final TagService tagService;
    private final Validator validator;

    public ReportServiceImpl(ReportRepository repository, Validator validator, TagService tagService) {
        this.repository = repository;
        this.validator = validator;
        this.tagService = tagService;
    }

    @Override
    public List<Report> getByBudgetId(Long budgetId) {
        return repository.findByBudgetId(budgetId);
    }

    @Override
    public Report getById(Long id) {
        return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException(ENTITY, id));
    }

    private Double calculateReportIncome(Double incomeTotal, Double percent) {
        return incomeTotal * percent / 100;
    }

    private String generateName(String tagName) {
        return tagName + " report";
    }

    // This function generates a report based on a tag and a budget
    private Report generateReport(Tag tag, Budget budget) {

        String name = generateName(tag.getName());
        Double income = calculateReportIncome(budget.getIncome().getMount(), tag.getPercent());
        Double initialExpendidure = INITIAL_VALUE;
        Double saving = income;

        Report report = new Report();

        report.setName(name);
        report.setMonthlyExpenditure(initialExpendidure);
        report.setMonthlyBudget(income);
        report.setMonthlySaving(saving);
        report.setTag(tag);
        report.setBudget(budget);

        return report;

    }

    // It creates a report for each tag in a budget
    @Override
    public void createReports(Budget budget) {

        Distribution distribution = budget.getDistribution();

        List<Tag> tags = tagService.getByDistributionId(distribution.getId());

        for (Tag tag : tags) {
            Report report = generateReport(tag, budget);
            create(report);
        }

    }

    @Override
    public Report create(Report request) {

        Set<ConstraintViolation<Report>> violations = validator.validate(request);

        if (!violations.isEmpty())
            throw new ResourceValidationException(ENTITY, violations);

        return repository.save(request);
    }

    @Override
    public Report getByBudgetIdAndTagId(Long budgetId, Long tagId) {
        return repository.findByBudgetIdAndTagId(budgetId, tagId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        ENTITY + "with budget id: " + budgetId + "and tagId: " + tagId + " not found"));
    }

    @Override
    public Report update(Report request){
        Set<ConstraintViolation<Report>> violations = validator.validate(request);

        if (!violations.isEmpty())
            throw new ResourceValidationException(ENTITY, violations);
        
        return  repository.save(request);
    }
}
