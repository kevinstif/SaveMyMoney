package com.smw.budget.resource.createResources;

import java.util.List;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateDistributionResource {
    
    private Long id;

    @NotNull
    private String name;

    @NotNull
    private Long userId;

    List<CreateTagResource> tags;
}
