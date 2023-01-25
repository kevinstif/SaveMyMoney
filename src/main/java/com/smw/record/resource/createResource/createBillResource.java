package com.smw.record.resource.createResource;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.smw.shared.domain.model.valueObjects.Money;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class createBillResource {

    private Long id;

    @NotBlank
    @NotNull
    private String name;

    @NotNull
    private Money money;

    @NotNull
    private Long tagId;

    @NotNull
    private Long budgetId;
    
}
