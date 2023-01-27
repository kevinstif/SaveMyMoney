package com.smw.record.resource.resource;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReportResource {
    private Long id;
    private String name;
    private Double monthlyExpenditure;
    private Double monthlyBudget;
    private Double monthlySaving;
    private Long tagId;
    private Long budgetId;
}
