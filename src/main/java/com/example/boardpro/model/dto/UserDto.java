package com.example.boardpro.model.dto;

import com.example.boardpro.model.entity.UserEntity;
import com.example.boardpro.model.entity.UserRole;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDto{
    private String userName;
    private String password;
    private String email;
}
