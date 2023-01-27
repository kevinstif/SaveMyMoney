package com.smw.record.domain.service;

import java.util.List;

import com.smw.budget.domain.model.entity.Budget;
import com.smw.record.domain.model.entity.Report;

public interface ReportService {
    List<Report> getByBudgetId(Long budgetId);
    Report getById(Long id);
    void createReports(Budget budget);
    Report create(Report request);
}
