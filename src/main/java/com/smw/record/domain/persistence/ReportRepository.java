package com.smw.record.domain.persistence;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.smw.record.domain.model.entity.Report;

@Repository
public interface ReportRepository extends JpaRepository<Report, Long>{

    List<Report> findByBudgetId(Long budgetId);
    Optional<Report> findByBudgetIdAndTagId(Long budgetId, Long tagId);
    
}
