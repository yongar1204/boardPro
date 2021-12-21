package com.example.boardpro.controller;

import com.example.boardpro.model.dto.NoteDto;
import com.example.boardpro.service.NoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/note")
@RequiredArgsConstructor
public class NoteController {
    private final NoteService noteService;

    @GetMapping("/index")
    public String getNote(Model model, Authentication authentication){
        String userName = authentication.getName();
        List<NoteDto> noteDtoList = noteService.getNoteList(userName);
        model.addAttribute("noteList", noteDtoList);
        return "note/devNote";
    }

    @GetMapping("/form")
    public String noteForm(){
        return "note/devNoteForm";
    }

}
