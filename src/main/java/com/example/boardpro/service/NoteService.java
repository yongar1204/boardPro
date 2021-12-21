package com.example.boardpro.service;

import com.example.boardpro.model.dto.NoteDto;
import com.example.boardpro.model.entity.NoteEntity;
import com.example.boardpro.model.entity.UserEntity;
import com.example.boardpro.repository.AccountRepository;
import com.example.boardpro.repository.NoteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class NoteService {
    private final NoteRepository noteRepository;
    private final AccountRepository accountRepository;

    public List<NoteDto> getNoteList(String username){
        UserEntity user = accountRepository.findByUserName(username);
        List<NoteDto> noteDtoList = noteRepository.findByUser(user)
                .stream().map(NoteDto::fromEntity).collect(Collectors.toList());
        return noteDtoList;
    }

    public NoteDto noteSave(NoteDto noteDto, String username){
        NoteEntity noteEntity = NoteEntity.builder()
                .title(noteDto.getTitle())
                .content(noteDto.getContent())
                .user(accountRepository.findByUserName(username))
                .build();
        noteRepository.save(noteEntity);
        return NoteDto.fromEntity(noteEntity);
    }

    public void noteDelete(Long id){
        noteRepository.deleteById(id);
    }
}
