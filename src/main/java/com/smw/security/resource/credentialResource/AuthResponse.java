package com.smw.security.resource.credentialResource;

import com.smw.security.resource.resource.UserResource;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

@Getter
@Setter
@NoArgsConstructor
public class AuthResponse {
    private UserResource user;
    private String message;
}
