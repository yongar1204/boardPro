package com.example.boardpro.service;

import com.example.boardpro.model.dto.BoardDto;
import com.example.boardpro.model.entity.BoardEntity;
import com.example.boardpro.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;

    public void submit(BoardDto boardDto){
        BoardEntity boardEntity = BoardEntity.builder()
                .skillType(boardDto.getSkillType())
                .exYear(boardDto.getExYear())
                .name(boardDto.getName())
                .age(boardDto.getAge())
                .title(boardDto.getTitle())
                .content(boardDto.getContent())
                .build();

        boardRepository.save(boardEntity);
    }

    public Page<BoardEntity> getList(String searchText, Pageable pageable){
        Page<BoardEntity> list = boardRepository.findByTitleContainingOrContentContaining(searchText, searchText, pageable);
        return list;
    }

    public BoardDto findDevAndToDto(Long id){
        BoardEntity boardEntity = boardRepository.findById(id).orElseThrow();
        BoardDto boardDto = BoardDto.fromEntity(boardEntity);
        return boardDto;
    }
}
