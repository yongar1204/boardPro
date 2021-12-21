package com.example.boardpro.service;

import com.example.boardpro.config.UserDetailsImpl;
import com.example.boardpro.model.dto.UserDto;
import com.example.boardpro.model.entity.RoleEntity;
import com.example.boardpro.model.entity.UserEntity;
import com.example.boardpro.model.entity.UserRole;
import com.example.boardpro.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AccountService {
    private final AccountRepository accountRepository;
    private final PasswordEncoder passwordEncoder;

    public void save(UserDto userDto){
//        UserEntity user = UserEntity.builder()
//                .userName(userDto.getUserName())
//                .password(passwordEncoder.encode(userDto.getPassword()))
//                .email(userDto.getEmail())
//                .build();
        UserEntity user = new UserEntity();
        user.setUserName(userDto.getUserName());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setEmail(userDto.getEmail());
        RoleEntity role = new RoleEntity();
        role.setId(1L);
        UserRole userRole = new UserRole();
        userRole.setRole(role);
        user.getRoles().add(userRole);
        userRole.setUser(user);
        accountRepository.save(user);
        System.out.println(user.getRoles());
    }

//    public String signup(UserDto request) {
//        boolean existMember = accountRepository.existsByUserName(request.getUserName());
//
//        // 이미 회원이 존재하는 경우
//        if (existMember) return null;
//
//        UserEntity user = new UserEntity(request);
//        user.setPassword(passwordEncoder.encode(request.getPassword()));
//        accountRepository.save(user);
//        return user.getUserName();
//    }

    private final AuthenticationManager authenticationManager;

    @Transactional(readOnly = true)
    public String login(UserDto userDto){

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(userDto.getUserName(), userDto.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        UserDetailsImpl principal = (UserDetailsImpl) authentication.getPrincipal();
        return principal.getUsername();
    }
}
