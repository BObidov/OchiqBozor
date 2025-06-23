package com.company.uzmart.uzMart_backend.service;

import com.company.uzmart.uzMart_backend.dto.UserDto;
import com.company.uzmart.uzMart_backend.entity.User;
import com.company.uzmart.uzMart_backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public User create(UserDto dto) {
        if (userRepository.existsByLogin(dto.getLogin())) {
            throw new RuntimeException("Login already exists");
        }
        User user = User.builder()
                .name(dto.getName())
                .surname(dto.getSurname())
                .login(dto.getLogin())
                .password(dto.getPassword())
                .role(dto.getRole())
                .status(dto.getStatus())
                .build();
        return userRepository.save(user);
    }

    public User getById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }


    public User update(Long id, UserDto dto) {
        User user = getById(id);
        user.setName(dto.getName());
        user.setSurname(dto.getSurname());
        user.setLogin(dto.getLogin());
        user.setPassword(dto.getPassword());
        user.setRole(dto.getRole());
        user.setStatus(dto.getStatus());
        return userRepository.save(user);
    }

    public void delete(Long id) {
        userRepository.deleteById(id);
    }

    public List<User> getAll() {
        return userRepository.findAll();
    }
}
