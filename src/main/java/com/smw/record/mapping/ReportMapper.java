package com.smw.record.mapping;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.smw.record.domain.model.entity.Report;
import com.smw.record.resource.resource.ReportResource;
import com.smw.shared.mapping.EnhancedModelMapper;

public class ReportMapper {
    
    @Autowired
    private EnhancedModelMapper mapper;

    public ReportResource toResource(Report model){
        return mapper.map(model, ReportResource.class);
    }

    public List<ReportResource> modelListToResource(List<Report> modelList){
        return mapper.mapList(modelList,ReportResource.class);
    }
}
