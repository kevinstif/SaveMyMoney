package com.smw.budget.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMethod;

import com.smw.budget.domain.service.BillService;
import com.smw.budget.mapping.BillMapper;
import com.smw.budget.resource.createResources.createBillResource;
import com.smw.budget.resource.resource.BillResource;

@CrossOrigin(origins = "*", methods = {
        RequestMethod.GET,
        RequestMethod.POST,
        RequestMethod.PUT,
        RequestMethod.DELETE
})
@RestController
@RequestMapping("api/v1/bill")
public class BillController {

    @Autowired
    private BillService service;

    @Autowired
    private BillMapper mapper;

    @GetMapping("/tag/{tagId}/budget/{budgetId}")
    public List<BillResource> getByTagId(@PathVariable("tagId") Long tagId, @PathVariable("budgetId") Long budgetId) {
        return mapper.modelListToResource(service.getAllByTagIdAndBudgetId(tagId, budgetId));
    }

    @GetMapping("/{id}")
    public BillResource getById(@PathVariable("id") Long id) {
        return mapper.toResource(service.getById(id));
    }

    @PostMapping
    public BillResource create(@RequestBody createBillResource resource) {
        return mapper.toResource(service.create(mapper.toModel(resource)));
    }

    @PutMapping("/{id}")
    public BillResource update(@PathVariable("id") Long id, @RequestBody createBillResource resource) {
        return mapper.toResource(service.update(id, mapper.toModel(resource)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        return service.delete(id);
    }
}
