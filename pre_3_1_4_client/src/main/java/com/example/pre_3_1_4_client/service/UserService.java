package com.example.pre_3_1_4_client.service;

import com.example.pre_3_1_4_client.dto.UserDto;

public interface UserService {
    void saveUser(UserDto userDto);
    void updateUser(UserDto userDto);
    UserDto findUserById(Long id);
    UserDto findUserByName(String name);
    UserDto[] findAllUsers();
    String[] findAllRoleNames();
    void deleteUserById(Long id);
}
