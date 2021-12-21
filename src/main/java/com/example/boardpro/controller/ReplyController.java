package com.example.boardpro.controller;

import com.example.boardpro.model.dto.ReplyDto;
import com.example.boardpro.repository.ReplyRepository;
import com.example.boardpro.service.ReplyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/reply")
@RequiredArgsConstructor
public class ReplyController {
    private final ReplyService replyService;
    private final ReplyRepository replyRepository;

    @PostMapping("/create")
    public String createReply(ReplyDto replyDto, @RequestParam Long id, RedirectAttributes re){
        replyService.submit(replyDto, id);
        re.addAttribute("id", id);
        return "redirect:/board/detail";
    }

    @GetMapping("/delete/{replyId}")
    public String deleteReply(@PathVariable Long replyId, RedirectAttributes re){
        Long boardId = replyRepository.findById(replyId).orElseThrow().getBoard().getId();
        re.addAttribute("id",boardId);
        replyService.deleteReply(replyId);
        return "redirect:/board/detail";
    }
}
