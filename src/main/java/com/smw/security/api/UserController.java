package com.smw.security.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.smw.security.domain.service.UserService;
import com.smw.security.mapping.UserMapper;
import com.smw.security.resource.createResource.CreateUserResource;
import com.smw.security.resource.credentialResource.AuthCredentials;
import com.smw.security.resource.resource.UserResource;

@CrossOrigin(origins = "*", methods = {
        RequestMethod.POST
})
@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserMapper mapper;

    @PostMapping("/auth/register")
    public UserResource register(@RequestBody CreateUserResource request) {
        return mapper.toResource(userService.register(mapper.toModel(request)));
    }

    @PostMapping("/auth/login")
    public ResponseEntity<?> login(@RequestBody AuthCredentials credentials) {

        return userService.login(credentials);

    }

}
