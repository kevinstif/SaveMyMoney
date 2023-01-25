package com.smw.budget.domain.service;

import java.util.List;

import com.smw.budget.domain.model.entity.Tag;

public interface TagService {

    Tag getById(Long id);

    Tag create(Tag request);

    List<Tag> getByDistributionId(Long distributionId);
}
