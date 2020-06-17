package com.example.pre_3_1_4_client.dto;

import lombok.*;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@AllArgsConstructor
public class UserDetailsDto {
    private String userName;
    private String password;
    private Set<String> authorities;
}
