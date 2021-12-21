package com.example.boardpro.controller;

import com.example.boardpro.model.dto.NoteDto;
import com.example.boardpro.service.NoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/note")
@RequiredArgsConstructor
public class NoteApiController {
    private final NoteService noteService;

    @GetMapping("/list")
    public List<NoteDto> getNoteList(Authentication authentication){
        String userName = authentication.getName();
        return noteService.getNoteList(userName);
    }

    @PostMapping("/save")
    public NoteDto getSave(@RequestBody NoteDto noteDto, Authentication authentication){
        String userName = authentication.getName();
        return noteService.noteSave(noteDto, userName);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Long id){
        noteService.noteDelete(id);
    }
}
