package com.example.pre_3_1_4_client.service;

import com.example.pre_3_1_4_client.dto.UserDto;
import org.springframework.http.client.support.BasicAuthorizationInterceptor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
public class UserServiceRestTemplate implements UserService {

    private final String PREFIX_URL = "http://localhost:8080/api/admin";
    private final String REST_LOGIN = "admin";
    private final String REST_PASSWORD = "1";

    @Override
    public void saveUser(UserDto userDto) {
        final RestTemplate restTemplate = new RestTemplate();
        final String CREATE_USERS_URL = PREFIX_URL + "/createUser";
        restTemplate.getInterceptors().add(
                new BasicAuthorizationInterceptor(REST_LOGIN, REST_PASSWORD));
        restTemplate.postForObject(CREATE_USERS_URL, userDto, UserDto.class);
    }

    @Override
    public void updateUser(UserDto userDto) {
        final RestTemplate restTemplate = new RestTemplate();
        final String UPDATE_USERS_URL = PREFIX_URL + "/updateUser";
        restTemplate.getInterceptors().add(
                new BasicAuthorizationInterceptor(REST_LOGIN, REST_PASSWORD));
        restTemplate.put(UPDATE_USERS_URL, userDto);
    }

    @Override
    public UserDto findUserById(Long id) {
        final RestTemplate restTemplate = new RestTemplate();
        Map<String, Long> params = new HashMap<>();
        params.put("id", id);
        final String GET_USERS_BY_ID_URL = PREFIX_URL + "/getUserById/{id}";
        restTemplate.getInterceptors().add(
                new BasicAuthorizationInterceptor(REST_LOGIN, REST_PASSWORD));
        return restTemplate.getForObject(GET_USERS_BY_ID_URL, UserDto.class, params);
    }

    @Override
    public UserDto findUserByName(String name) {
        final RestTemplate restTemplate = new RestTemplate();
        Map<String, String> params = new HashMap<>();
        params.put("name", name);
        final String GET_USERS_BY_NAME_URL = PREFIX_URL + "/getUserByName/{name}";
        restTemplate.getInterceptors().add(
                new BasicAuthorizationInterceptor(REST_LOGIN, REST_PASSWORD));
        return restTemplate.getForObject(GET_USERS_BY_NAME_URL, UserDto.class, params);
    }

    @Override
    public UserDto[] findAllUsers() {
        final RestTemplate restTemplate = new RestTemplate();
        final String GET_ALL_USERS_URL = PREFIX_URL + "/getAllUsers";
        restTemplate.getInterceptors().add(
                new BasicAuthorizationInterceptor(REST_LOGIN, REST_PASSWORD));
        return restTemplate.getForObject(GET_ALL_USERS_URL, UserDto[].class);
    }

    @Override
    public String[] findAllRoleNames() {
        final RestTemplate restTemplate = new RestTemplate();
        final String GET_ALL_ROLES = PREFIX_URL + "/getAllRoles";
        restTemplate.getInterceptors().add(
                new BasicAuthorizationInterceptor(REST_LOGIN, REST_PASSWORD));
        return restTemplate.getForObject(GET_ALL_ROLES, String[].class);
    }

    @Override
    public void deleteUserById(Long id) {
        final RestTemplate restTemplate = new RestTemplate();
        Map<String, Long> params = new HashMap<>();
        params.put("id", id);
        final String DELETE_USERS_URL = PREFIX_URL + "/deleteUserById/{id}";
        restTemplate.getInterceptors().add(
                new BasicAuthorizationInterceptor(REST_LOGIN, REST_PASSWORD));
        restTemplate.delete(DELETE_USERS_URL, params);
    }
}
