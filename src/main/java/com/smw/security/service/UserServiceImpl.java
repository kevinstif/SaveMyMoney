package com.smw.security.service;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import com.smw.security.domain.model.entity.User;
import com.smw.security.domain.persistence.UserRepository;
import com.smw.security.domain.service.UserService;
import com.smw.security.mapping.UserMapper;
import com.smw.security.resource.credentialResource.AuthCredentials;
import com.smw.security.resource.credentialResource.AuthResponse;
import com.smw.security.security.middleware.TokenUtils;
import com.smw.shared.exception.ResourceValidationException;
import com.smw.shared.exception.ResourceNotFoundException;

@Service
public class UserServiceImpl implements UserService {

    private static final String ENTITY = "user";
    private final UserRepository repository;
    private final Validator validator;
    private final AuthenticationManager authenticationManager;
    private final UserMapper mapper;

    public UserServiceImpl(UserRepository repository, Validator validator, AuthenticationManager authenticationManager, UserMapper mapper) {
        this.repository = repository;
        this.validator = validator;
        this.authenticationManager = authenticationManager;
        this.mapper = mapper;
    }

    @Override
    public User register(User user) {
        Set<ConstraintViolation<User>> violations = validator.validate(user);

        if (!violations.isEmpty())
            throw new ResourceValidationException(ENTITY,violations);

        String passwordEncoded = new BCryptPasswordEncoder().encode(user.getPassword());

        return repository.save(user.withPassword(passwordEncoded));
    }

    @Override
    public ResponseEntity<?> login(AuthCredentials credentials){

        User user = this.getByusername(credentials.getUsername());
        AuthResponse response = new AuthResponse();

        try {

            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(credentials.getUsername(), credentials.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(authentication);

            String token = TokenUtils.createToken(user.getUsername(), user.getEmail());

            response.setMessage(token);
            response.setUser(mapper.toResource(user));

            return ResponseEntity.ok().body(response);

        } catch (Exception e) {
            response.setMessage(e.getMessage());
            response.setUser(null);
            return ResponseEntity.badRequest().body(response);
        }
    }

    @Override
    public boolean exits(Long userId) {
        return repository.existsById(userId);
    }

    @Override
    public User getByusername(String username) {
        return this.repository.findOneByUsername(username)
        .orElseThrow(()-> new ResourceNotFoundException("the user with username " + username + " not found"));
    }

}
