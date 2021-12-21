package com.example.boardpro.controller;

import com.example.boardpro.model.dto.NoticeDto;
import com.example.boardpro.service.NoticeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/notice")
@RequiredArgsConstructor
public class NoticeController {
    private final NoticeService noticeService;
    @GetMapping("/list")
    public String getList(Model model){
        List<NoticeDto.Response> getNoticeList = noticeService.getNoticeList();
        model.addAttribute("list", getNoticeList);
        return "notice/devNoticeList";
    }

    @GetMapping("/form")
    public String form(){
        return "notice/devNoticeForm";
    }

    @GetMapping("/detail")
    public String detail(Model model, @RequestParam Long id){
        NoticeDto.Response response = noticeService.detail(id);
        model.addAttribute("response", response);
        return "notice/devNoticeDetail";
    }

    @GetMapping("/edit")
    public String editNotice(Model model, @RequestParam Long id){
        NoticeDto.Response response = noticeService.editForm(id);
        model.addAttribute("response", response);
        return "notice/devNoticeEditForm";
    }
}
