package com.smw.budget.domain.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.smw.budget.domain.model.entity.Tag;

@Repository
public interface TagRepository extends JpaRepository<Tag, Long>{
    List<Tag> findByDistributionId(Long id);
}
