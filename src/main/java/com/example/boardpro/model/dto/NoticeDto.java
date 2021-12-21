package com.example.boardpro.model.dto;

import com.example.boardpro.model.entity.NoticeEntity;
import lombok.*;
import org.apache.tomcat.jni.Local;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

public class NoticeDto {
    @Getter
    @Setter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Request{
        @NotNull(message = "Not Null Ok?!")
        @NotEmpty(message = "Title Not Null Ok?#")
        private String title;
        @NotNull(message = "Not Null Ok?!")
        @NotEmpty(message = "Content Not Null Ok?#")
        private String content;
    }

    @Getter
    @Setter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Response{
        private Long noticeId;
        private String title;
        private String content;
        private LocalDateTime createAt;

        public static Response fromEntity(NoticeEntity noticeEntity){
            return Response.builder()
                    .noticeId(noticeEntity.getNoticeId())
                    .title(noticeEntity.getTitle())
                    .content(noticeEntity.getContent())
                    .createAt(noticeEntity.getCreateAt())
                    .build();
        }
    }

}
