package com.smw.record.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.smw.record.domain.service.ReportService;
import com.smw.record.mapping.ReportMapper;
import com.smw.record.resource.resource.ReportResource;

@CrossOrigin(origins = "*", methods = {
        RequestMethod.GET
})
@RestController
@RequestMapping("api/v1/reports")
public class ReportController {

    @Autowired
    private ReportService service;

    @Autowired
    private ReportMapper mapper;

    @GetMapping("/budget/{budgetId}")
    public List<ReportResource> getByBudgetId(@PathVariable("budgetId") Long budgetId) {
        return mapper.modelListToResource(service.getByBudgetId(budgetId));
    }

    @GetMapping("/{id}")
    public ReportResource getById(@PathVariable("id") Long id) {
        return mapper.toResource(service.getById(id));
    }

    @GetMapping("/budget/{budgetId}/tag/{tagId}")
    public ReportResource getByBudgetIdAndTagId(@PathVariable("budgetId") Long budgetId,
            @PathVariable("tagId") Long tagId) {
        return mapper.toResource(service.getByBudgetIdAndTagId(budgetId, tagId));
    }
}
