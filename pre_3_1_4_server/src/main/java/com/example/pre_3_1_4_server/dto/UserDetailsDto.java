package com.example.pre_3_1_4_server.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Set;

@Data
@AllArgsConstructor
public class UserDetailsDto {
    private String userName;
    private String password;
    private Set<String> authorities;
}
