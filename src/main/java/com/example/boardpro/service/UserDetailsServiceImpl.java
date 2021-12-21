package com.example.boardpro.service;

import com.example.boardpro.config.UserDetailsImpl;
import com.example.boardpro.model.entity.UserEntity;
import com.example.boardpro.model.entity.UserRole;
import com.example.boardpro.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserDetailsServiceImpl implements UserDetailsService {
    private final AccountRepository accountRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = accountRepository.findByUserName(username);
        return new UserDetailsImpl(user);
    }
}
