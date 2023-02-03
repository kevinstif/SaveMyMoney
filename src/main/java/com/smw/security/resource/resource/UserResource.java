package com.smw.security.resource.resource;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserResource {
    private Long id;
    private String username;
    private String email;
    private String firstName;
    private String lastName;
}
