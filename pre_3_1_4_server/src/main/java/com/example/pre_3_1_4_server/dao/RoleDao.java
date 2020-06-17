package com.example.pre_3_1_4_server.dao;

import com.example.pre_3_1_4_server.model.Role;

import java.util.List;

public interface RoleDao {
    void save(Role role);
    Role findByName(String name);
    List<Role> findAll();
}
