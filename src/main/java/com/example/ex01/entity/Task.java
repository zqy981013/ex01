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
public class Task {
    public static final int ON = 1;
    public static final int OFF = 2;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String taskName;
    @Column(columnDefinition = "TEXT")
    private String taskContent;
    @OneToMany(mappedBy = "task")
    private List<TaskDetail> taskDetails;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime deadLineTime;
    private int status = ON;
}
