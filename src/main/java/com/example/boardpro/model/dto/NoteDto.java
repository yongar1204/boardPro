package com.example.boardpro.model.dto;

import com.example.boardpro.model.entity.NoteEntity;
import com.example.boardpro.model.entity.UserEntity;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class NoteDto {

    private Long id;
    private String title;
    private String content;
    private LocalDateTime createAt;

    public static NoteDto fromEntity(NoteEntity noteEntity){
        return NoteDto.builder()
                .id(noteEntity.getId())
                .title(noteEntity.getTitle())
                .content(noteEntity.getContent())
                .createAt(noteEntity.getCreateAt())
                .build();
    }
}
