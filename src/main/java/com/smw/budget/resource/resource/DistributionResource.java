package com.smw.budget.resource.resource;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DistributionResource {
    private Long id;
    private String name;
    List<TagResource> tags;
    private Long userId;
}
