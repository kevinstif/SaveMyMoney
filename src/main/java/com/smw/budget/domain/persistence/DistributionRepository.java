package com.smw.budget.domain.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.smw.budget.domain.model.entity.Distribution;

@Repository
public interface DistributionRepository extends JpaRepository<Distribution, Long> {

}
