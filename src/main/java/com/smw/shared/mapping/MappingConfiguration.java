package com.smw.shared.mapping;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.smw.budget.mapping.BudgetMapper;
import com.smw.budget.mapping.DistributionMapper;
import com.smw.budget.mapping.TagMapper;
import com.smw.record.domain.model.entity.Report;
import com.smw.record.mapping.BillMapper;
import com.smw.record.mapping.ReportMapper;
import com.smw.security.mapping.UserMapper;

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

    @Bean ReportMapper reportMapper(){
        return new ReportMapper();
    }

    @Bean UserMapper userMapper(){
        return new UserMapper();
    }
}
