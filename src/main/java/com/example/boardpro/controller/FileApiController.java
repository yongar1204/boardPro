package com.example.boardpro.controller;

import com.example.boardpro.service.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/file")
@RequiredArgsConstructor
public class FileApiController {
    private final FileService fileService;

    @DeleteMapping("/delete/{id}")
    public void deleteFile(@PathVariable Long id){
        fileService.deleteFile(id);
    }
}
