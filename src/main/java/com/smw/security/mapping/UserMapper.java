package com.smw.security.mapping;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;

import com.smw.security.domain.model.entity.User;
import com.smw.security.resource.createResource.CreateUserResource;
import com.smw.security.resource.resource.UserResource;
import com.smw.shared.mapping.EnhancedModelMapper;

public class UserMapper implements Serializable{

    @Autowired
    EnhancedModelMapper mapper;

    public UserResource toResource(User user){
        return mapper.map(user, UserResource.class);
    }

    public User toModel(CreateUserResource createUserResource){
        return mapper.map(createUserResource, User.class);
    }
    
}
