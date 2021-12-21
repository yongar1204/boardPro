package com.example.boardpro.config;

import com.example.boardpro.model.entity.RoleEntity;
import com.example.boardpro.model.entity.UserEntity;
import com.example.boardpro.model.entity.UserRole;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
public class UserDetailsImpl implements UserDetails {
    private final UserEntity user;

    @Override
    @Transactional(readOnly = true)
    public Collection<? extends GrantedAuthority> getAuthorities() {
        String userRoles[] = convert(user.getRoles());
        Collection<GrantedAuthority> authorities = AuthorityUtils.createAuthorityList(userRoles);
        return authorities;
//        List<RoleEntity> role = user.getRoles().stream().map(UserRole::getRole).collect(Collectors.toList());

//        role.stream().map(RoleEntity::getRoleName).collect(Collectors.toList())
//        SimpleGrantedAuthority authority = new SimpleGrantedAuthority();
//        Collection<GrantedAuthority> authorities = new ArrayList<>(); //List인 이유 : 여러개의 권한을 가질 수 있다
//        authorities.add(authority);
//
//        return authorities;

//        List<RoleEntity> role = user.getRoles().stream().map(UserRole::getRole).collect(Collectors.toList());
//        Collection<GrantedAuthority> authorities = new ArrayList<>();
//        for (RoleEntity auth: role){
//            SimpleGrantedAuthority authority = new SimpleGrantedAuthority(auth.getRoleName());
//            authorities.add(authority);
//        }
    }
    public String[] convert(List<UserRole> userRolesList){
        String[] arrList = new String[userRolesList.size()];
        int index = 0;
        for (UserRole userRole : userRolesList){
            arrList[index++] = userRole.getRole().getRoleName();
        }
        return arrList;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUserName();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
