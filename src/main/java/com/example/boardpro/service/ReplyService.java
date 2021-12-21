package com.example.boardpro.service;

import com.example.boardpro.model.dto.BoardDto;
import com.example.boardpro.model.dto.ReplyDto;
import com.example.boardpro.model.entity.BoardEntity;
import com.example.boardpro.model.entity.ReplyEntity;
import com.example.boardpro.repository.BoardRepository;
import com.example.boardpro.repository.ReplyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReplyService {

    private final ReplyRepository replyRepository;
    private final BoardRepository boardRepository;

    public void submit(ReplyDto replyDto, Long id){
        ReplyEntity replyEntity = ReplyEntity.builder()
                .name(replyDto.getName())
                .content(replyDto.getContent())
                .build();
        replyEntity.setBoard(boardRepository.findById(id).orElseThrow());
        replyRepository.save(replyEntity);
    }

    public List<ReplyDto> getReply(Long boardId){
        BoardEntity board = boardRepository.findById(boardId).orElseThrow();
        List<ReplyDto> replyList = replyRepository.findByBoard(board)
                .stream().map(ReplyDto :: fromEntity)
                .collect(Collectors.toList());

        return replyList;
    }

    public void deleteReply(Long replyId){
        replyRepository.deleteById(replyId);
    }
}
