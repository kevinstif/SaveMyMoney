package com.smw.budget.resource.resource;

import com.smw.shared.domain.model.valueObjects.Money;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class BillResource {
    private Long id;
    private String name;
    private Money money;
    private Long tagId;
    private Long budgetId;
    
}
