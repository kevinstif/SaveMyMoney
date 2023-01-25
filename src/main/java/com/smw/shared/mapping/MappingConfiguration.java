package com.smw.shared.mapping;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.smw.budget.mapping.BillMapper;
import com.smw.budget.mapping.BudgetMapper;
import com.smw.budget.mapping.DistributionMapper;
import com.smw.budget.mapping.TagMapper;

@Configuration("Configuration")
public class MappingConfiguration {

    @Bean
    public EnhancedModelMapper modelMapper(){
        return new EnhancedModelMapper();
    }

    @Bean
    public TagMapper tagMapper(){
        return new TagMapper();
    }

    @Bean DistributionMapper distributionMapper(){
        return new DistributionMapper();
    }

    @Bean BillMapper billMapper(){
        return new BillMapper();
    }

    @Bean BudgetMapper budgetMapper(){
        return new BudgetMapper();
    }
}
