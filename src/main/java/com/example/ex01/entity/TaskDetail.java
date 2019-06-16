package com.example.ex01.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class TaskDetail {
    public static final int DONE = 1;
    public static final int  LATE_DONE = 2;
    public static final int  NOT_DONE = 3;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne(fetch = FetchType.LAZY)
    private Task task;
    @ManyToOne(fetch = FetchType.LAZY)
    private User teacher;
    @Column(columnDefinition = "TEXT")
    private String solution;
    private int status = 3;
    @Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP",
            updatable = false, insertable = false)
    private LocalDateTime completeTime;

}
