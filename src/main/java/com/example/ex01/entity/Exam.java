package com.example.ex01.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Exam {
    public static final int NOT_ALLOCATED = 1;
    public static final int  ALLOCATED = 2;
    public static final int FINISHED = 3;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String examName;
    private int status = 1;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime beginTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime endTime;
    private String location;
    private int needCount;
    @OneToMany(mappedBy = "exam")
    private List<ExamDetail> examDetails;
    @Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP",
            updatable = false,
            insertable = false)
    private LocalDateTime insertTime;
    public Exam(int id) {
        this.id = id;
    }
}
