package com.example.pre_3_1_4_server.util;

import com.example.pre_3_1_4_server.dao.RoleDao;
import com.example.pre_3_1_4_server.dao.UserDao;
import com.example.pre_3_1_4_server.model.Role;
import com.example.pre_3_1_4_server.model.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class DbInitializer {

    private final UserDao userDao;
    private final RoleDao roleDao;
    private final PasswordEncoder passwordEncoder;

    public DbInitializer(UserDao userDao, RoleDao roleDao, PasswordEncoder passwordEncoder) {
        this.userDao = userDao;
        this.roleDao = roleDao;
        this.passwordEncoder = passwordEncoder;
        initRepo();
    }

    public void initRepo() {
        roleDao.save(new Role("ADMIN"));
        roleDao.save(new Role("USER"));

        Set<Role> rolesAdmin = new HashSet<>();
        rolesAdmin.add(roleDao.findByName("ADMIN"));
        rolesAdmin.add(roleDao.findByName("USER"));

        Set<Role> rolesUser = new HashSet<>();
        rolesUser.add(roleDao.findByName("USER"));

        userDao.save(new User("admin@gmail.com", passwordEncoder.encode("1"),
                "admin", "admin", 90, rolesAdmin));
        userDao.save(new User("user@gmail.com", passwordEncoder.encode("1"),
                "user", "user", 50, rolesUser));
    }
}
