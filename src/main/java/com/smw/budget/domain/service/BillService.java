package com.smw.budget.domain.service;

import java.util.List;

import org.springframework.http.ResponseEntity;
import com.smw.budget.domain.model.entity.Bill;

public interface BillService {
    List<Bill> getAllByTagIdAndBudgetId(Long tagId, Long budgetId);

    Bill getById(Long id);

    Bill create(Bill bill);

    Bill update(Long id, Bill request);

    ResponseEntity<?> delete(Long id);
}
