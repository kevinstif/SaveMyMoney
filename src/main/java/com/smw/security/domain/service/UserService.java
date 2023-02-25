package com.smw.security.domain.service;

import org.springframework.http.ResponseEntity;

import com.smw.security.domain.model.entity.User;
import com.smw.security.resource.credentialResource.AuthCredentials;

public interface UserService {

    User register(User user);
    ResponseEntity<?>  login(AuthCredentials credentials);
    User getByusername(String username);   
    boolean exits(Long userId);
}
