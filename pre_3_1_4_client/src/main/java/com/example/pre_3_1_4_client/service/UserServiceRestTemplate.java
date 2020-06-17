package com.example.pre_3_1_4_client.service;

import com.example.pre_3_1_4_client.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class UserServiceRestTemplate implements UserService {

    private final RestTemplate restTemplate;
    private final String PREFIX_URL = "http://localhost:8080/api/admin";

    @Override
    public void saveUser(UserDto userDto) {
        final String CREATE_USERS_URL = PREFIX_URL + "/createUser";
        restTemplate.postForObject(CREATE_USERS_URL, userDto, UserDto.class);
    }

    @Override
    public void updateUser(UserDto userDto) {
        final String UPDATE_USERS_URL = PREFIX_URL + "/updateUser";
        restTemplate.put(UPDATE_USERS_URL, userDto);
    }

    @Override
    public UserDto findUserById(Long id) {
        Map<String, Long> params = new HashMap<>();
        params.put("id", id);
        final String GET_USERS_BY_ID_URL = PREFIX_URL + "/getUserById/{id}";
        return restTemplate.getForObject(GET_USERS_BY_ID_URL, UserDto.class, params);
    }

    @Override
    public UserDto findUserByName(String name) {
        Map<String, String> params = new HashMap<>();
        params.put("name", name);
        final String GET_USERS_BY_NAME_URL = PREFIX_URL + "/getUserByName/{name}";
        return restTemplate.getForObject(GET_USERS_BY_NAME_URL, UserDto.class, params);
    }

    @Override
    public UserDto[] findAllUsers() {
        final String GET_ALL_USERS_URL = PREFIX_URL + "/getAllUsers";
        return restTemplate.getForObject(GET_ALL_USERS_URL, UserDto[].class);
    }

    @Override
    public String[] findAllRoleNames() {
        final String GET_ALL_ROLES = PREFIX_URL + "/getAllRoles";
        return restTemplate.getForObject(GET_ALL_ROLES, String[].class);
    }

    @Override
    public void deleteUserById(Long id) {
        Map<String, Long> params = new HashMap<>();
        params.put("id", id);
        final String DELETE_USERS_URL = PREFIX_URL + "/deleteUserById/{id}";
        restTemplate.delete(DELETE_USERS_URL, params);
    }
}
