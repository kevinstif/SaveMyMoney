package com.smw.budget.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.smw.budget.domain.service.TagService;
import com.smw.budget.mapping.TagMapper;
import com.smw.budget.resource.resource.TagResource;

@CrossOrigin(origins = "*", methods = {
        RequestMethod.GET,
})
@RestController
@RequestMapping("api/v1/targets")
public class TagController {

    @Autowired
    private TagService tagService;

    @Autowired
    private TagMapper mapper;

    @GetMapping("{id}")
    public TagResource getById(@PathVariable("id") Long id) {
        return mapper.toResource(tagService.getById(id));
    }

    @GetMapping("distribution/{distributionId}")
    public List<TagResource> getByDistributionId(@PathVariable("distributionId") Long distributionId) {
        return mapper.modelListToResource(tagService.getByDistributionId(distributionId));
    }

}
