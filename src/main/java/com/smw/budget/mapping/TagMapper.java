package com.smw.budget.mapping;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.smw.budget.domain.model.entity.Tag;
import com.smw.budget.resource.createResources.CreateTagResource;
import com.smw.budget.resource.resource.TagResource;
import com.smw.shared.mapping.EnhancedModelMapper;

public class TagMapper implements Serializable {

    @Autowired
    EnhancedModelMapper mapper;

    public TagResource toResource(Tag model) {
        return mapper.map(model, TagResource.class);
    }

    public Tag toModel(CreateTagResource resource) {
        return mapper.map(resource, Tag.class);
    }

    public List<TagResource> modelListToResource(List<Tag> modelList) {
        return mapper.mapList(modelList, TagResource.class);
    }

}
