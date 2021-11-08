package com.example.boardpro.model.dto;

import com.example.boardpro.model.entity.BoardEntity;
import com.example.boardpro.type.SkillType;
import com.sun.istack.NotNull;
import lombok.*;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BoardDto {
        private SkillType skillType;
        private Integer exYear;
        private String name;
        private Integer age;
        private String title;
        private String content;

        public static BoardDto fromEntity(BoardEntity boardEntity){
                return BoardDto.builder()
                        .skillType(boardEntity.getSkillType())
                        .exYear(boardEntity.getExYear())
                        .name(boardEntity.getName())
                        .age(boardEntity.getAge())
                        .title(boardEntity.getTitle())
                        .content(boardEntity.getContent())
                        .build();
        }
}
