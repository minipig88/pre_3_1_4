package com.example.pre_3_1_4_server.controller;

import com.example.pre_3_1_4_server.dto.UserDetailsDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/login")
@RequiredArgsConstructor
public class LoginRestController {

    private final UserDetailsService userDetailsService;

    @GetMapping("/getUserDetails/{userName}")
    public ResponseEntity<UserDetailsDto> getUserDetails(@PathVariable(name = "userName") String userName) {
        UserDetails user = userDetailsService.loadUserByUsername(userName);

        Set<String> authorities = user.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority).collect(Collectors.toSet());

        UserDetailsDto userDetailsDto = new UserDetailsDto(user.getUsername(), user.getPassword(), authorities);

        return ResponseEntity.ok(userDetailsDto);
    }

}
