package com.example.pre_3_1_4_client.controller;

import com.example.pre_3_1_4_client.dto.UserDto;
import com.example.pre_3_1_4_client.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/admin")
public class AdminRestController {

    private final UserService userService;

    @GetMapping("/getUserById/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable Long id) {
        return ResponseEntity.ok(userService.findUserById(id));
    }

    @GetMapping("/getAllUsers")
    public ResponseEntity<UserDto[]> getAllUsers() {
        return ResponseEntity.ok(userService.findAllUsers());
    }

    @GetMapping("/getAllRoles")
    public ResponseEntity<String[]> getAllRoles() {
        return ResponseEntity.ok(userService.findAllRoleNames());
    }

    @PostMapping("/createUser")
    public ResponseEntity<UserDto> addUser(@RequestBody UserDto user) {
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
