package com.example.boardpro.model.entity;

import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "AttFile")
public class FileEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String origFileName;
    private String fileName;
    private String filePath;

    @JoinColumn(name = "boardId", referencedColumnName = "id")
    @ManyToOne
    private BoardEntity board;
}
