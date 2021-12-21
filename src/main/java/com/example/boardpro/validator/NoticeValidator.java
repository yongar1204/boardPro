package com.example.boardpro.validator;

import com.example.boardpro.model.dto.BoardDto;
import com.example.boardpro.model.dto.NoticeDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
@Component
@RequiredArgsConstructor
public class NoticeValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return NoticeDto.Request.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        NoticeDto.Request request = (NoticeDto.Request) target;
        if (ObjectUtils.isEmpty(request.getTitle())) {
            errors.rejectValue("title", "errorCode", "Not Null Ok?");
        }
    }
}
