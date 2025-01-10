package com.Pharma.PharmaDelivery.model;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserLogin {
    private Long id;
    private String name;
    private String email;
    private String password;
    private String token;
    private Role role;
}

