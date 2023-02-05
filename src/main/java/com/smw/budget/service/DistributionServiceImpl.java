package com.smw.budget.service;

import java.util.List;
import java.util.Set;

import javax.validation.Validator;
import javax.transaction.Transactional;
import javax.validation.ConstraintViolation;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.smw.budget.domain.model.entity.Distribution;
import com.smw.budget.domain.model.entity.Tag;
import com.smw.budget.domain.persistence.DistributionRepository;
import com.smw.budget.domain.service.DistributionService;
import com.smw.budget.domain.service.TagService;
import com.smw.security.domain.service.UserService;
import com.smw.shared.exception.ResourceNotFoundException;
import com.smw.shared.exception.ResourceValidationException;

@Service
public class DistributionServiceImpl implements DistributionService {

    private static final String ENTITY = "Distribution";
    private final DistributionRepository repository;
    private final Validator validator;
    private final TagService tagService;
    private final UserService userService;

    public DistributionServiceImpl(DistributionRepository repository, Validator validator, TagService tagService,
            UserService userService) {
        this.repository = repository;
        this.validator = validator;
        this.tagService = tagService;
        this.userService = userService;
    }

    @Override
    public List<Distribution> getAll(Long userId) {
        if (!userService.exits(userId))
            throw new ResourceNotFoundException("User", userId);
        return repository.findByUserId(userId);
    }

    @Override
    public Distribution getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(ENTITY, id));
    }

    private void createTags(List<Tag> tags, Distribution distribution) {

        for (Tag tag : tags) {
            tag.setDistribution(distribution);
            tagService.create(tag);
        }

    }

    private Boolean validateSumPercents(List<Tag> tags) {

        int sum = 0;

        for (Tag tag : tags) {
            sum += tag.getPercent();
        }

        return sum == 100;
    }

    @Override
    @Transactional
    public Distribution create(Distribution request) {

        Set<ConstraintViolation<Distribution>> violations = validator.validate(request);

        if (!violations.isEmpty())
            throw new ResourceValidationException(ENTITY, violations);

        if (!validateSumPercents(request.getTags()))
            throw new ResourceValidationException("A sum of Percents of Tags must be equal to 100");

        Distribution distribution = repository.save(request);

        createTags(request.getTags(), distribution);

        return distribution;

    }

    @Override
    public Distribution update(Long id, Distribution request) {
        Set<ConstraintViolation<Distribution>> violations = validator.validate(request);

        if (!violations.isEmpty()) {
            throw new ResourceValidationException(ENTITY, violations);
        }

        return repository.findById(id).map(
                distribution -> repository.save(distribution
                        .withName(request.getName())))
                .orElseThrow(() -> new ResourceNotFoundException(ENTITY, id));
    }

    @Override
    public ResponseEntity<?> delete(Long id) {

        return repository.findById(id).map(
                distribution -> {
                    repository.delete(distribution);
                    return ResponseEntity.ok().build();
                }).orElseThrow(() -> new ResourceNotFoundException(ENTITY, id));
    }
}
