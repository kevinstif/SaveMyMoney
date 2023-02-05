package com.smw.security.domain.service;

import com.smw.security.domain.model.entity.AuthCredentials;
import com.smw.security.domain.model.entity.User;

public interface UserService {

    User register(User user);    
    boolean exits(Long userId);
}
