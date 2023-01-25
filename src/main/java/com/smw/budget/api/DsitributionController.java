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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.smw.budget.domain.service.DistributionService;
import com.smw.budget.mapping.DistributionMapper;
import com.smw.budget.resource.createResources.CreateDistributionResource;
import com.smw.budget.resource.resource.DistributionResource;



@CrossOrigin(origins = "*", methods = {
    RequestMethod.GET,
    RequestMethod.POST,
    RequestMethod.PUT,
    RequestMethod.DELETE
})
@RestController
@RequestMapping("api/v1/distribution")
public class DsitributionController {

    @Autowired
    DistributionService service;

    @Autowired
    DistributionMapper mapper;

    @GetMapping
    public List<DistributionResource> getAll(){
        return mapper.modelListToResource(service.getAll());
    }

    @GetMapping("{id}")
    public DistributionResource getById(@PathVariable("id") Long id){
        return mapper.toResource(service.getById(id));
    }

    @PostMapping
    public DistributionResource create(@RequestBody CreateDistributionResource resource){    
        
        return mapper.toResource(service.create(mapper.toModel(resource)));
    }

    @PutMapping("{id}")
    public DistributionResource update(@PathVariable("id") Long id, CreateDistributionResource resource){
        return mapper.toResource(service.update(id, mapper.toModel(resource)));
    }

    @DeleteMapping({"id"})
    public ResponseEntity<?> delete(@PathVariable("id") Long id){
        return service.delete(id);
    }
    
}
