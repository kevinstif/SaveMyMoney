package com.smw.security.service;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.smw.security.domain.model.entity.User;
import com.smw.security.domain.persistence.UserRepository;
import com.smw.security.domain.service.UserService;
import com.smw.shared.exception.ResourceValidationException;

@Service
public class UserServiceImpl implements UserService {

    private static final String ENTITY = "user";
    private final UserRepository repository;
    private final Validator validator;

    public UserServiceImpl(UserRepository repository, Validator validator) {
        this.repository = repository;
        this.validator = validator;
    }

    @Override
    public User register(User user) {
        Set<ConstraintViolation<User>> violations = validator.validate(user);

        if (!violations.isEmpty())
            throw new ResourceValidationException(ENTITY,violations);

        String passwordEncoded = new BCryptPasswordEncoder().encode(user.getPassword());

        return repository.save(user.withPassword(passwordEncoded));
    }

}
