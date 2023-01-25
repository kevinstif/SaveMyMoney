package com.smw.budget.resource.resource;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TagResource {
    private Long id;
    private String name;
    private String description;
    private Double percent;
    private Long distributionId;
}
