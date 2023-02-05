package com.smw.budget.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.smw.budget.domain.service.BudgetService;
import com.smw.budget.mapping.BudgetMapper;
import com.smw.budget.resource.createResources.createBudgetResource;
import com.smw.budget.resource.resource.BudgetResource;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@CrossOrigin(origins = "*", methods = {
        RequestMethod.GET,
        RequestMethod.POST,
        RequestMethod.PUT,
        RequestMethod.DELETE
})
@RestController
@RequestMapping("api/v1/budget")
public class BudgetController {

    @Autowired
    private BudgetService service;

    @Autowired
    private BudgetMapper mapper;

    @GetMapping("user/{userId}")
    public List<BudgetResource> getAll(@PathVariable("userId") Long userId) {
        return mapper.modelListToResource(service.getAll(userId));
    }

    @GetMapping("/{id}")
    public BudgetResource getById(@PathVariable("id") Long id) {
        return mapper.toResource(service.getById(id));
    }

    @PostMapping
    public BudgetResource create(@RequestBody createBudgetResource request) {
        return mapper.toResource(service.create(mapper.toModel(request)));
    }

    @PutMapping("/{id}")
    public BudgetResource update(@PathVariable("id") Long id ,@RequestBody createBudgetResource request) {
        return mapper.toResource(service.update(id,mapper.toModel(request)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id){
        return service.delete(id);
    }

}
