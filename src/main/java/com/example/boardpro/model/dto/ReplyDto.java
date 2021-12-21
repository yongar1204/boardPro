package com.example.boardpro.model.dto;

import com.example.boardpro.model.entity.ReplyEntity;
import lombok.*;

import java.io.File;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReplyDto {
    private Long id;
    private String name;
    private String content;

    public static ReplyDto fromEntity(ReplyEntity replyEntity){
        return ReplyDto.builder()
                .id(replyEntity.getId())
                .name(replyEntity.getName())
                .content(replyEntity.getContent())
                .build();
    }
}
