package com.example.boardpro.service;

import com.example.boardpro.model.dto.BoardDto;
import com.example.boardpro.model.entity.BoardEntity;
import com.example.boardpro.repository.AccountRepository;
import com.example.boardpro.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;
    private final AccountRepository accountRepository;
    private final FileService fileService;

    public void editBoard(Long id ,BoardDto boardDto, MultipartFile[] files) throws IOException {
        BoardEntity boardEntity =boardRepository.findById(id).orElseThrow();
        boardEntity.setSkillType(boardDto.getSkillType());
        boardEntity.setExYear(boardDto.getExYear());
        boardEntity.setName(boardDto.getName());
        boardEntity.setAge(boardDto.getAge());
        boardEntity.setTitle(boardDto.getTitle());
        boardEntity.setContent(boardDto.getContent());
        boardRepository.save(boardEntity);
        for (MultipartFile f : files){
            fileService.fileSave(f, boardEntity);
        }
    }

    public void submit(BoardDto boardDto, MultipartFile[] file, String userName) throws IOException {
        BoardEntity boardEntity = BoardEntity.builder()
                .skillType(boardDto.getSkillType())
                .exYear(boardDto.getExYear())
                .name(boardDto.getName())
                .age(boardDto.getAge())
                .title(boardDto.getTitle())
                .content(boardDto.getContent())
                .user(accountRepository.findByUserName(userName))
                .build();
        boardRepository.save(boardEntity);
        for (MultipartFile f : file){
            fileService.fileSave(f, boardEntity);
        }
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

    public BoardDto getEditForm(Long id) {
        BoardEntity boardEntity = boardRepository.findById(id).orElseThrow();
        BoardDto boardDto = BoardDto.fromEntity(boardEntity);
        return boardDto;
    }

    public void deleteBoard(Long id) {
        boardRepository.deleteById(id);
    }
}
