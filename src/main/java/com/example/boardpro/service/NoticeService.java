package com.example.boardpro.service;

import com.example.boardpro.model.dto.NoticeDto;
import com.example.boardpro.model.entity.NoticeEntity;
import com.example.boardpro.repository.NoticeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class NoticeService {
    private final NoticeRepository noticeRepository;

    public List<NoticeDto.Response> getNoticeList(){
        List<NoticeDto.Response> getList = noticeRepository.findAll()
                .stream()
                .map(NoticeDto.Response :: fromEntity)
                .collect(Collectors.toList());
        return getList;
    }

    public NoticeDto.Response createNotice(NoticeDto.Request request){
        NoticeEntity notice = NoticeEntity.builder()
                .title(request.getTitle())
                .content(request.getContent())
                .build();
        noticeRepository.save(notice);
        return NoticeDto.Response.fromEntity(notice);
    }

    public NoticeDto.Response detail(Long noticeId){
        NoticeEntity notice = noticeRepository.findById(noticeId).orElseThrow();
        NoticeDto.Response noticeDto = NoticeDto.Response.fromEntity(notice);
        return noticeDto;
    }

    public NoticeDto.Response editForm(Long id){
        NoticeEntity notice = noticeRepository.findById(id).orElseThrow();
        NoticeDto.Response noticeDto = NoticeDto.Response.fromEntity(notice);
        return noticeDto;
    }

    public void noticeEdit(Long id, NoticeDto.Request request){
        NoticeEntity notice = noticeRepository.findById(id).orElseThrow();
        notice.setTitle(request.getTitle());
        notice.setContent(request.getContent());
        noticeRepository.save(notice);
    }

    public void delete(Long noticeId){
        noticeRepository.deleteById(noticeId);
    }
}
