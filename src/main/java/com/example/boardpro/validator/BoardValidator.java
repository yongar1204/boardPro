package com.example.boardpro.validator;

import com.example.boardpro.model.dto.BoardDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.regex.Matcher;

@Component
@RequiredArgsConstructor
public class BoardValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return BoardDto.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        BoardDto boardDto = (BoardDto) target;
        if (ObjectUtils.isEmpty(boardDto.getName())) {
            errors.rejectValue("name", "errorCode", "Not Null Ok?");
        }
        if (ObjectUtils.isEmpty(boardDto.getTitle())){
            errors.rejectValue("title", "errorCode", "Not Null Ok?");
        }
        if (ObjectUtils.isEmpty(boardDto.getAge())){
            errors.rejectValue("age", "error", "Invalid(age > 18) and Not Null");
        }
    }
}
