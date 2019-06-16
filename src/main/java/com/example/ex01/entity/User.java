package com.example.ex01.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
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
public class User {
    public static final int USER_AUTHORITY = 1;
    public static final int ADMIN_AUTHORITY = 2;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String number;
    private String name;
    //职称
    private String title;
    private String tel;
    //描述
    private String detail;
    @OneToMany(mappedBy = "teacher")
    private List<ExamDetail> examDetails;
    @OneToMany(mappedBy = "teacher")
    private List<TaskDetail> taskDetails;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;
    // 在没有声明时默认为1
    private int authority = 1;
    @Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP",
            updatable = false,
            insertable = false)
    private LocalDateTime insertTime;
    public User(int id) {
        this.id = id;
    }
}
