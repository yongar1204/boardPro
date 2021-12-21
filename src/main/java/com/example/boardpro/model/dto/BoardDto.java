package com.example.boardpro.model.dto;

import com.example.boardpro.model.entity.BoardEntity;
import com.example.boardpro.type.SkillType;
import lombok.*;

import javax.validation.constraints.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BoardDto {
        private SkillType skillType;
        @Min(0)
        @Max(20)
        @NotNull(message = "Not Null Ok?")
        private Integer exYear;
        private String name;
        @Min(value = 18, message = "Invalid(age > 18)")
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
