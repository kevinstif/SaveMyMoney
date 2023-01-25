package com.smw.budget.mapping;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.smw.budget.domain.model.entity.Budget;
import com.smw.budget.resource.createResources.createBudgetResource;
import com.smw.budget.resource.resource.BudgetResource;
import com.smw.shared.mapping.EnhancedModelMapper;

public class BudgetMapper implements Serializable{
    @Autowired
    EnhancedModelMapper mapper;

    public Budget toModel(createBudgetResource resource) {
        return mapper.map(resource, Budget.class);
    }

    public BudgetResource toResource(Budget model){
        return mapper.map(model, BudgetResource.class);
    }

    public List<BudgetResource> modelListToResource(List<Budget> modelList){
        return mapper.mapList(modelList, BudgetResource.class);
    }
}
