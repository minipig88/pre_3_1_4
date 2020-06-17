package com.example.pre_3_1_4_server.service;

import com.example.pre_3_1_4_server.dao.RoleDao;
import com.example.pre_3_1_4_server.dao.UserDao;
import com.example.pre_3_1_4_server.dto.UserDto;
import com.example.pre_3_1_4_server.model.Role;
import com.example.pre_3_1_4_server.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserDao userDao;
    private final RoleDao roleDao;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void saveUser(UserDto userDto) {
        userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
        userDao.save(getUserFromDto(userDto));
    }

    @Override
    public void updateUser(UserDto userDto) {
        User userUpd = userDao.findById(userDto.getId());
        if (userDto.getPassword() != null && !userDto.getPassword().equals("")) {
            userUpd.setPassword(passwordEncoder.encode(userDto.getPassword()));
        }
        userUpd.setRoles(getRoleSet(userDto.getRoles()));
        userUpd.setUsername(userDto.getEmail());
        userUpd.setFirstName(userDto.getFirstName());
        userUpd.setLastName(userDto.getLastName());
        userUpd.setAge(userDto.getAge());
        userDao.update(userUpd);
    }

    @Override
    public UserDto findUserById(Long id) {
        return getDtoFromUser(userDao.findById(id));
    }

    @Override
    public UserDto findUserByName(String name) {
        return getDtoFromUser(userDao.findByName(name));
    }

    @Override
    public List<UserDto> findAllUsers() {
        return userDao.findAll().stream().map(this::getDtoFromUser).collect(Collectors.toList());
    }

    @Override
    public Set<String> findAllRoleNames() {
        return roleDao.findAll().stream().map(Role::getRoleName).collect(Collectors.toSet());
    }

    @Override
    public void deleteUserById(Long id) {
        userDao.deleteById(id);
    }

    private User getUserFromDto(UserDto userDto) {
        return new User(userDto.getId(), userDto.getEmail(), userDto.getPassword(),
                userDto.getFirstName(), userDto.getLastName(), userDto.getAge(),
                getRoleSet(userDto.getRoles()));
    }

    private UserDto getDtoFromUser(User user) {
        return new UserDto(user.getId(), user.getUsername(), user.getPassword(),
                user.getFirstName(), user.getLastName(), user.getAge(),
                getRoleNameSet(user.getRoles()));
    }

    private Set<String> getRoleNameSet(Set<Role> roleSet) {
        return roleSet.stream().map(Role::getRoleName).collect(Collectors.toSet());
    }

    private Set<Role> getRoleSet(Set<String> roleNameSet) {
        return roleNameSet.stream().map(roleDao::findByName).collect(Collectors.toSet());
    }
}
