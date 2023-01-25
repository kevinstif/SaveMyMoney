package com.smw.budget.service;

import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.springframework.stereotype.Service;

import com.smw.budget.domain.model.entity.Tag;
import com.smw.budget.domain.persistence.DistributionRepository;
import com.smw.budget.domain.persistence.TagRepository;
import com.smw.budget.domain.service.TagService;
import com.smw.shared.exception.ResourceNotFoundException;
import com.smw.shared.exception.ResourceValidationException;

@Service
public class TagServiceImpl implements TagService {

    private static final String ENTITY = "Target";
    private final TagRepository repository;
    private final Validator validator;
    private final DistributionRepository distributionRepository;

    public TagServiceImpl(TagRepository repository, Validator validator, DistributionRepository distributionRepository) {
        this.repository = repository;
        this.validator = validator;
        this.distributionRepository = distributionRepository;
    }

    @Override
    public Tag getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(ENTITY, id));
    }

    @Override
    public Tag create(Tag request) {
        Set<ConstraintViolation<Tag>> violations = validator.validate(request);

        if (!violations.isEmpty())
            throw new ResourceValidationException(ENTITY, violations);

        return repository.save(request);
    }

    @Override
    public List<Tag> getByDistributionId(Long distributionId) {
        
        if (!distributionRepository.existsById(distributionId)){
            throw new ResourceNotFoundException("Distribution", distributionId);
        }

        return repository.findByDistributionId(distributionId);
    }

}
