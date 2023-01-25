package com.smw.budget.domain.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.smw.budget.domain.model.entity.Budget;

@Repository
public interface BudgetRepository extends JpaRepository<Budget, Long> {
    
}
