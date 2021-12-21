package com.example.boardpro.repository;

import com.example.boardpro.model.entity.BoardEntity;
import com.example.boardpro.model.entity.ReplyEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReplyRepository extends JpaRepository<ReplyEntity, Long> {
    List<ReplyEntity> findByBoard(BoardEntity boardEntity);
}
