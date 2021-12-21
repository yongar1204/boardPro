package com.example.boardpro.repository;

import com.example.boardpro.model.entity.NoteEntity;
import com.example.boardpro.model.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NoteRepository extends JpaRepository<NoteEntity, Long> {
    List<NoteEntity> findByUser(UserEntity user);
}
