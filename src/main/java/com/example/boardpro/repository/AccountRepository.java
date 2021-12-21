package com.example.boardpro.repository;

import com.example.boardpro.model.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<UserEntity, Long> {
    UserEntity findByUserName(String userName);
    boolean existsByUserName(String userName);
}
