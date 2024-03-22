package com.pi.config;


import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
public class AuthData {
    private String token;

    public void setToken(String jwt) {
        this.token = jwt;
    }
}
