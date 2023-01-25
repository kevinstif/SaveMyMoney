package com.smw.record.domain.service;

import java.util.List;

import com.smw.record.domain.model.entity.Report;

public interface ReportService {
    List<Report> getByBudgetId(Long budgetId);
    Report getById(Long id);
    Report create(Report request);
}
