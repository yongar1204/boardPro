package com.example.boardpro.service;

import com.example.boardpro.model.dto.FileDto;
import com.example.boardpro.model.entity.BoardEntity;
import com.example.boardpro.model.entity.FileEntity;
import com.example.boardpro.repository.BoardRepository;
import com.example.boardpro.repository.FileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class FileService {
    private final FileRepository fileRepository;
    private final BoardRepository boardRepository;

    public FileDto fileSave(MultipartFile file, BoardEntity board) throws IOException {
//        11.25 if문 추가
        if (!file.isEmpty()){
            String projectPath = System.getProperty("user.dir") + "/files";
            UUID uuid = UUID.randomUUID();
            String fileName = uuid + "_" + file.getOriginalFilename();
            File saveFile = new File(projectPath, fileName);
            file.transferTo(saveFile);

            FileEntity fileEntity = FileEntity.builder()
                    .filePath(projectPath)
                    .fileName(fileName)
                    .origFileName(file.getOriginalFilename())
                    .board(board)
                    .build();
            fileRepository.save(fileEntity);
            return FileDto.fromEntity(fileEntity);
        } else return new FileDto();
    }

    public List<FileDto> getFileDetail(Long boardId){
        BoardEntity board = boardRepository.findById(boardId).orElseThrow();
//        FileEntity fileEntity = fileRepository.findByBoard(board);
//        FileDto fileDto = FileDto.fromEntity(fileEntity);
        List<FileDto> fileList = fileRepository.findByBoard(board)
                .stream().map(FileDto::fromEntity).collect(Collectors.toList());
        return fileList;
    }

    public FileDto getFileDto(Long fileId){
        FileEntity fileEntity = fileRepository.findById(fileId).orElseThrow();
        return FileDto.fromEntity(fileEntity);
    }

    public void deleteFile(Long id){
        fileRepository.deleteById(id);
    }
}

