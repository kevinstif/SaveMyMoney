package com.smw.budget.domain.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.smw.budget.domain.model.entity.Bill;

@Repository
public interface BillRepository extends JpaRepository<Bill, Long> {
    List<Bill> findByTagIdAndBudgetId(Long tagId, Long budgetId);
}
