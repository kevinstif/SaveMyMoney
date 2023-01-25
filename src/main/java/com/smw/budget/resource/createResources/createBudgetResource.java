package com.smw.budget.resource.createResources;

import java.util.Date;

import javax.persistence.Embedded;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.smw.shared.domain.model.valueObjects.Money;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class createBudgetResource {
    private Long id;

    @NotBlank
    @NotNull
    private String name;

    @NotNull
    private Date startDate;

    @NotNull
    private Date endDate;

    @NotNull
    @Embedded
    private Money income;

    @NotNull
    private Long distributionId;
}
