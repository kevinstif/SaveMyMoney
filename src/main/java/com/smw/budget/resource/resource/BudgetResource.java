package com.smw.budget.resource.resource;

import java.util.Date;

import com.smw.shared.domain.model.valueObjects.Money;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class BudgetResource {

    private Long id;

    private String name;

    private Date startDate;

    private Date endDate;

    private Money income;

    private Long distributionId;

}
