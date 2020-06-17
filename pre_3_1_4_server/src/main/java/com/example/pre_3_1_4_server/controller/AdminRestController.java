package com.example.pre_3_1_4_server.controller;

import com.example.pre_3_1_4_server.dto.UserDto;
import com.example.pre_3_1_4_server.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/admin")
@RequiredArgsConstructor
public class AdminRestController {

    private final UserService userService;

    @GetMapping("/getUserById/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable Long id) {
        return ResponseEntity.ok(userService.findUserById(id));
    }

    @GetMapping("/getUserByName/{name}")
    public ResponseEntity<UserDto> getUserByName(@PathVariable String name) {
        return ResponseEntity.ok(userService.findUserByName(name));
    }

    @GetMapping("/getAllUsers")
    public ResponseEntity<UserDto[]> getAllUsers() {
        return ResponseEntity.ok(userService.findAllUsers().toArray(new UserDto[0]));
    }

    @GetMapping("/getAllRoles")
    public ResponseEntity<String[]> getAllRoles() {
        return ResponseEntity.ok(userService.findAllRoleNames().toArray(new String[0]));
    }

    @PostMapping("/createUser")
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto user) {
        userService.saveUser(user);
        return ResponseEntity.ok(userService.findUserByName(user.getEmail()));
    }

    @PutMapping("/updateUser")
    public ResponseEntity<UserDto> updateUser(@RequestBody UserDto user) {
        userService.updateUser(user);
        return ResponseEntity.ok(userService.findUserByName(user.getEmail()));
    }

    @DeleteMapping("/deleteUserById/{id}")
    public ResponseEntity<String> deleteUserById(@PathVariable Long id) {
        userService.deleteUserById(id);
        return ResponseEntity.ok("success");
    }

}
