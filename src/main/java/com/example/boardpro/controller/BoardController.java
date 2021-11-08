package com.example.boardpro.controller;

import com.example.boardpro.model.dto.BoardDto;
import com.example.boardpro.model.entity.BoardEntity;
import com.example.boardpro.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
@RequiredArgsConstructor
@Controller
@RequestMapping("/board")
public class BoardController {

    private final BoardService boardService;

    @GetMapping("/list")
    public String list(@PageableDefault(size = 3) Pageable pageable,
                       @RequestParam(required = false, defaultValue = "") String searchText,
                       Model model){
        Page<BoardEntity> searchList = boardService.getList(searchText, pageable);
        int startPage = Math.max(1,searchList.getPageable().getPageNumber() - 4);
        int endPage = Math.min(searchList.getTotalPages(), searchList.getPageable().getPageNumber() + 4);
        model.addAttribute("searchList", searchList);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        return "board/devList";
    }

    @GetMapping("/form")
    public String form(Model model, @RequestParam(required = false) Long id){

        if (id == null) {
            model.addAttribute("boardDto", new BoardDto());
        } else {
            BoardDto boardDto = boardService.findDevAndToDto(id);
            model.addAttribute("boardDto",boardDto);
        }
        return "board/devForm";
    }

    @GetMapping("/detail")
    public String detail(Model model, @RequestParam Long id){
        BoardDto detailDev = boardService.findDevAndToDto(id);
        model.addAttribute("devDetail",detailDev);
        return "board/devDetail";
    }

    @PostMapping(value = "/create")
    public String createDev(BoardDto boardDto){
        boardService.submit(boardDto);
        return "redirect:/board/list";
    }

}
