package com.smw.record.mapping;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.smw.record.domain.model.entity.Bill;
import com.smw.record.resource.createResource.createBillResource;
import com.smw.record.resource.resource.BillResource;
import com.smw.shared.mapping.EnhancedModelMapper;

public class BillMapper implements Serializable {

    @Autowired
    private EnhancedModelMapper mapper;

    public Bill toModel(createBillResource resource) {
        return mapper.map(resource, Bill.class);
    }

    public BillResource toResource(Bill bill) {
        return mapper.map(bill, BillResource.class);
    }

    public List<BillResource> modelListToResource(List<Bill> bills) {
        return mapper.mapList(bills, BillResource.class);
    }

}
