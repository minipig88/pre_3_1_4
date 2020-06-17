package com.example.pre_3_1_4_client.service;

import com.example.pre_3_1_4_client.dto.UserDetailsDto;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserDetailsServiceRestTemplate implements UserDetailsService {

    private final RestTemplate restTemplate;
    private final String PREFIX_URL = "http://localhost:8080";

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Map<String, String> params = new HashMap<>();
        params.put("username", username);
        final String GET_USER_DETAILS_URL = PREFIX_URL + "/login/getUserDetails/{username}";
        UserDetailsDto userDetailsDto = restTemplate.getForObject(GET_USER_DETAILS_URL, UserDetailsDto.class, params);

        if (userDetailsDto != null) {
            Set<? extends GrantedAuthority> authorities = userDetailsDto.getAuthorities().stream()
                    .map(s -> (GrantedAuthority) () -> s).collect(Collectors.toSet());
            return new User(userDetailsDto.getUserName(), userDetailsDto.getPassword(), authorities);
        }
        return null;
    }
}
