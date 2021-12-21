package com.example.boardpro.controller;

import com.example.boardpro.model.dto.NoticeDto;
import com.example.boardpro.service.NoticeService;
import com.example.boardpro.validator.NoticeValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/notice")
@RequiredArgsConstructor
public class NoticeApiController {
    private final NoticeService noticeService;
    private final NoticeValidator noticeValidator;

    @PostMapping("/create")
    public ResponseEntity<String> create(@RequestBody @Valid NoticeDto.Request request, BindingResult bindingResult){
        noticeValidator.validate(request, bindingResult);
        if (bindingResult.hasErrors()){
            ObjectError objectError = bindingResult.getAllErrors().stream().findFirst().get();
            return ResponseEntity.badRequest().body(objectError.getDefaultMessage());
        }
        NoticeDto.Response notice = noticeService.createNotice(request);
        return null;
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Long id){
        noticeService.delete(id);
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<String> edit(@PathVariable Long id, @RequestBody @Valid NoticeDto.Request request, BindingResult bindingResult){
        noticeValidator.validate(request, bindingResult);
        if(bindingResult.hasErrors()) {
//            bindingResult.getAllErrors() .forEach(objectError->{
//                System.err.println("code : " + objectError.getCode());
//                System.err.println("defaultMessage : " + objectError.getDefaultMessage());
//                System.err.println("objectName : " + objectError.getObjectName());
//            });
            ObjectError objectError = bindingResult.getAllErrors().stream().findFirst().get();
            return ResponseEntity.badRequest().body(objectError.getDefaultMessage());
        }

        noticeService.noticeEdit(id, request);
        return ResponseEntity.ok("notice save");
    }
}
