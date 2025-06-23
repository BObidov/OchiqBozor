package com.company.uzmart.uzMart_backend.dto;

import com.company.uzmart.uzMart_backend.model.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDto {
    private String name;
    private String surname;
    private String login;
    private String password;
    private Role role;
    private String status;
}
