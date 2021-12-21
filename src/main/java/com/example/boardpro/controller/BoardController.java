package com.example.boardpro.controller;

import com.example.boardpro.model.dto.BoardDto;
import com.example.boardpro.model.dto.FileDto;
import com.example.boardpro.model.dto.ReplyDto;
import com.example.boardpro.model.entity.BoardEntity;
import com.example.boardpro.service.BoardService;
import com.example.boardpro.service.FileService;
import com.example.boardpro.service.ReplyService;
import com.example.boardpro.validator.BoardValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.*;
import java.net.URLEncoder;
import java.util.List;

@RequiredArgsConstructor
@Controller
@RequestMapping("/board")
public class BoardController {

    private final BoardService boardService;
    private final ReplyService replyService;
    private final FileService fileService;
    private final BoardValidator boardValidator;

    @GetMapping("/list")
    public String list(@PageableDefault(size = 3) Pageable pageable,
                       @RequestParam(required = false, defaultValue = "") String searchText,
                       Model model){
        Page<BoardEntity> searchList = boardService.getList(searchText, pageable);
        int startPage = Math.max(1,searchList.getPageable().getPageNumber() - 5);
        int endPage = Math.min(searchList.getTotalPages(), searchList.getPageable().getPageNumber() + 5);
        model.addAttribute("searchList", searchList);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        return "board/devList";
    }

    @GetMapping("/form")
    public String form(Model model, @RequestParam(required = false) Long id){

        model.addAttribute("boardDto", new BoardDto());
//        if (id == null) {
//            model.addAttribute("boardDto", new BoardDto());
//        } else {
//            BoardDto boardDto = boardService.findDevAndToDto(id);
//            model.addAttribute("boardDto",boardDto);
//        }
        return "board/devForm";
    }

    @GetMapping("/detail")
    public String detail(Model model, @RequestParam Long id){
        BoardDto detailDev = boardService.findDevAndToDto(id);
        List<ReplyDto> replyList = replyService.getReply(id);
        List<FileDto> fileList = fileService.getFileDetail(id);
        model.addAttribute("fileList",  fileList);
        model.addAttribute("devDetail",detailDev);
        model.addAttribute("replyList",replyList);
        model.addAttribute("replyDto", new ReplyDto());
        return "board/devDetail";
    }

//    @PostMapping(value = "/create")
//    public String create(BoardDto boardDto){
//        boardService.submit(boardDto);
//        return "redirect:/board/list";
//    }

    @PostMapping(value = "/create2")
    public String create2(@Valid BoardDto boardDto, BindingResult bindingResult, MultipartFile[] img, Authentication authentication) throws IOException {
        boardValidator.validate(boardDto, bindingResult);
        String userName = authentication.getName();
        if (bindingResult.hasErrors()){
            return "/board/devForm";
        }
        boardService.submit(boardDto, img, userName);
        return "redirect:/board/list";
    }

    @GetMapping("/edit")
    public String editForm(Model model, @RequestParam Long id){
        BoardDto editBoard = boardService.getEditForm(id);
        List<FileDto> fileList = fileService.getFileDetail(id);
        model.addAttribute("fileList", fileList);
        model.addAttribute("editBoardData",editBoard);
        return "board/devEditForm";
    }

    @PostMapping("/edit")
    public String edit(@RequestParam Long id, Model model, @Valid BoardDto boardDto, BindingResult bindingResult, MultipartFile[] img, RedirectAttributes re) throws IOException {
        re.addAttribute("id", id);
        boardValidator.validate(boardDto, bindingResult);
        if (bindingResult.hasErrors()){
//            return "redirect:/board/edit?id={id}";
            return editForm(model, id);
//            return "forward:/board/edit?id={id}";
        }
        boardService.editBoard(id, boardDto, img);
        return "redirect:/board/list";
    }

    @PostMapping("/delete")
    public String delete(@RequestParam Long id){
        boardService.deleteBoard(id);
        return "redirect:/board/list";
    }

    @GetMapping("/downLoad1/{id}")
    public void fileDownload(@PathVariable Long id, HttpServletResponse response) throws UnsupportedEncodingException {
        FileDto fileInfo = fileService.getFileDto(id);
        String fileName = fileInfo.getFileName();

        response.setContentType("application/octet-stream");
        response.setHeader("Content-Transfer-Encoding", "binary");
        response.setHeader("Content-Disposition", "attachment; filename=\"" + URLEncoder.encode(fileInfo.getOrigFileName(), "UTF-8") + "\";");
        try {
            OutputStream os = response.getOutputStream();
            FileInputStream fis = new FileInputStream(fileInfo.getFilePath() +"/"+ fileName);

            int count = 0;
            byte[] bytes = new byte[1024];

            while ((count = fis.read(bytes)) != -1 ) {
                os.write(bytes, 0, count);
            }

            fis.close();
            os.close();
        } catch (FileNotFoundException ex) {
            System.out.println("FileNotFoundException");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
