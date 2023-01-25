package com.smw.budget.mapping;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.smw.budget.domain.model.entity.Distribution;
import com.smw.budget.resource.createResources.CreateDistributionResource;
import com.smw.budget.resource.resource.DistributionResource;
import com.smw.shared.mapping.EnhancedModelMapper;

public class DistributionMapper implements Serializable {

    @Autowired
    EnhancedModelMapper mapper;

    public Distribution toModel(CreateDistributionResource dto) {
        return mapper.map(dto, Distribution.class);
    }

    public DistributionResource toResource(Distribution model) {
        return mapper.map(model, DistributionResource.class);
    }

    public List<DistributionResource> modelListToResource(List<Distribution> modelList) {
        return mapper.mapList(modelList, DistributionResource.class);
    }

}
