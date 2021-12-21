package com.example.boardpro.model.dto;

import com.example.boardpro.model.entity.FileEntity;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class FileDto {
    private Long id;
    private String origFileName;
    private String fileName;
    private String filePath;

    public static FileDto fromEntity(FileEntity fileEntity){
        return FileDto.builder()
                .id(fileEntity.getId())
                .origFileName(fileEntity.getOrigFileName())
                .fileName(fileEntity.getFileName())
                .filePath(fileEntity.getFilePath())
                .build();
    }
}
