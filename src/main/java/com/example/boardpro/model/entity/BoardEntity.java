package com.example.boardpro.model.entity;

import com.example.boardpro.type.SkillType;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "BoardPro")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BoardEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Enumerated(EnumType.STRING)
    private SkillType skillType;
    private Integer exYear;
    private String name;
    private Integer age;
    private String title;
    private String content;

    @OneToMany(mappedBy = "board", cascade = CascadeType.ALL)
    private List<FileEntity> files = new ArrayList<>();

    @OneToMany(mappedBy = "board", cascade = CascadeType.ALL)
    private List<ReplyEntity> reply = new ArrayList<>();
    @CreatedDate
    private LocalDateTime createdAt;
    @LastModifiedDate
    private LocalDateTime updatedAt;

    @JoinColumn(name = "userId", referencedColumnName = "id")
    @ManyToOne()
    private UserEntity user;

}
