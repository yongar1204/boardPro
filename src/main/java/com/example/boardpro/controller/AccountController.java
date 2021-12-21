package com.example.boardpro.controller;

import com.example.boardpro.model.dto.UserDto;
import com.example.boardpro.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/account")
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;

    @GetMapping("/login")
    public String loginPage(){
        return "/account/login";
    }

    @GetMapping("/register")
    public String register(){
        return "/account/register";
    }

    @PostMapping("/register")
    public String register(UserDto userDto){
        accountService.save(userDto);
        return "redirect:/";
    }

//    @PostMapping("/login")
//    public String login(UserDto userDto){
//        accountService.login(userDto);
//        return "redirect:/";
//    }
}
