package com.example.boardpro.repository;

import com.example.boardpro.model.entity.BoardEntity;
import com.example.boardpro.model.entity.FileEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FileRepository extends JpaRepository<FileEntity, Long> {
    List<FileEntity> findByBoard(BoardEntity board);
}
