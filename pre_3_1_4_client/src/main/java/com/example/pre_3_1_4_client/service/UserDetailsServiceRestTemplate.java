package com.example.pre_3_1_4_client.service;

import com.example.pre_3_1_4_client.dto.UserDetailsDto;
import org.springframework.http.client.support.BasicAuthorizationInterceptor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class UserDetailsServiceRestTemplate implements UserDetailsService {

    private final String PREFIX_URL = "http://localhost:8080";
    private final String LOGIN = "USER";
    private final String PASSWORD = "1";

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        final RestTemplate restTemplate = new RestTemplate();
        Map<String, String> params = new HashMap<>();
        params.put("username", username);
        final String GET_USER_DETAILS_URL = PREFIX_URL + "/login/getUserDetails/{username}";
        restTemplate.getInterceptors().add(
                new BasicAuthorizationInterceptor(LOGIN, PASSWORD));
        UserDetailsDto userDetailsDto = restTemplate.getForObject(GET_USER_DETAILS_URL, UserDetailsDto.class, params);

        if (userDetailsDto != null) {
            return new UserDetails() {
                @Override
                public Collection<? extends GrantedAuthority> getAuthorities() {
                    return userDetailsDto.getAuthorities().stream()
                            .map(s -> (GrantedAuthority) () -> s).collect(Collectors.toSet());
                }

                @Override
                public String getPassword() {
                    return userDetailsDto.getPassword();
                }

                @Override
                public String getUsername() {
                    return userDetailsDto.getUserName();
                }

                @Override
                public boolean isAccountNonExpired() {
                    return true;
                }

                @Override
                public boolean isAccountNonLocked() {
                    return true;
                }

                @Override
                public boolean isCredentialsNonExpired() {
                    return true;
                }

                @Override
                public boolean isEnabled() {
                    return true;
                }
            };
        }
        return null;
    }

}
