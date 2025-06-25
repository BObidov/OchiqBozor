package com.company.uzmart.uzMart_backend.controller;

import com.company.uzmart.uzMart_backend.dto.UserDto;
import com.company.uzmart.uzMart_backend.entity.User;
import com.company.uzmart.uzMart_backend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/create")
    public ResponseEntity<User> create(@RequestBody UserDto dto) {
        return ResponseEntity.ok(userService.create(dto));
    }

    public ResponseEntity<User> getById(@RequestParam Long id) {
        return ResponseEntity.ok(userService.getById(id));
    }


    @PutMapping("/update")
    public ResponseEntity<User> update(@RequestParam Long id, @RequestBody UserDto dto) {
        return ResponseEntity.ok(userService.update(id, dto));
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> delete(@RequestParam Long id) {
        userService.delete(id);
        return ResponseEntity.ok("User deleted");
    }

    public ResponseEntity<List<User>> getAll() {
        return ResponseEntity.ok(userService.getAll());
    }
}