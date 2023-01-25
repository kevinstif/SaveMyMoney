package com.smw.budget.service;

import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.smw.budget.domain.model.entity.Bill;
import com.smw.budget.domain.persistence.BillRepository;
import com.smw.budget.domain.service.BillService;
import com.smw.budget.domain.service.BudgetService;
import com.smw.budget.domain.service.TagService;
import com.smw.shared.exception.ResourceNotFoundException;
import com.smw.shared.exception.ResourceValidationException;

@Service
public class BillSeviceImpl implements BillService {

    private static final String ENTITY = "Bill";
    private final BillRepository repository;
    private final Validator validator;
    private final TagService tagService;
    private final BudgetService budgetService;

    public BillSeviceImpl(BillRepository repository, Validator validator, TagService tagService,
            BudgetService budgetService) {
        this.repository = repository;
        this.validator = validator;
        this.tagService = tagService;
        this.budgetService = budgetService;
    }

    @Override
    public List<Bill> getAllByTagIdAndBudgetId(Long tagId, Long budgetId) {

        if (tagService.getById(tagId) == null)
            throw new ResourceNotFoundException("Tag", tagId);

        if (budgetService.getById(budgetId) == null)
            throw new ResourceNotFoundException("Budget", budgetId);

        return repository.findByTagIdAndBudgetId(tagId, budgetId);
    }

    @Override
    public Bill getById(Long id) {
        return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException(ENTITY, id));
    }

    @Override
    public Bill create(Bill bill) {

        Set<ConstraintViolation<Bill>> violations = validator.validate(bill);

        if (!violations.isEmpty()) {
            throw new ResourceValidationException(ENTITY, violations);
        }

        return repository.save(bill);
    }

    @Override
    public Bill update(Long id, Bill request) {

        Set<ConstraintViolation<Bill>> violations = validator.validate(request);

        if (!violations.isEmpty()) {
            throw new ResourceValidationException(ENTITY, violations);
        }

        return repository.findById(id).map(
                bill -> repository.save(bill
                        .withMoney(request.getMoney())
                        .withName(request.getName())))
                .orElseThrow(() -> new ResourceNotFoundException(ENTITY, id));
    }

    @Override
    public ResponseEntity<?> delete(Long id) {
        return repository.findById(id).map(
                bill -> {
                    repository.delete(bill);
                    return ResponseEntity.ok().build();
                }).orElseThrow(() -> new ResourceNotFoundException(ENTITY, id));
    }

}
