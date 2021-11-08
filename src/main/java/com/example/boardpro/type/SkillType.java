package com.example.boardpro.type;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum SkillType {

    BACK_END("백앤드"),
    FRONT_END("프론트"),
    FULL_STACK("풀스택");


    private final String description;
}
