package com.example.ex01.controller;

import com.example.ex01.entity.Exam;
import com.example.ex01.entity.ExamDetail;
import com.example.ex01.entity.Task;
import com.example.ex01.entity.User;
import com.example.ex01.service.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import com.example.ex01.service.ExamService;
import com.example.ex01.service.UserService;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/api/admin")
public class AdminController {
    @Autowired
    private UserService userService;
    @Autowired
    private ExamService examService;
    @Autowired
    private TaskService taskService;
    @Autowired
    private ExamDetailService examDetailService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    //添加用户
    @PostMapping("/users/adduser")
    public Map postUser(@RequestBody User user){
        userService.addUser(user);
        return Map.of("users",userService.getAllUsers());
    }
    //查看所有用户信息
    @GetMapping("/users")
    public Map getUsers(){
        return Map.of("users",userService.getAllUsers());
    }
    //修改任意用户的密码
    @PostMapping("/modify")
    public void modify(@RequestBody User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userService.addUser(user);
    }
    //修改权限
    @PostMapping("/modify/authority")
    public void modifyAuthority(@RequestBody User user){
        user.setAuthority((user.getAuthority())%2+1);
        userService.addUser(user);
    }
    //查询所有未/已分配监考任务
    @GetMapping("/exams/{status}")
    public Map getExamsByStatus(@PathVariable int status){
        List<Exam> exams = new ArrayList();
        exams = examService.getExamByStatus(status);
        return Map.of("exams",exams);
    }
    //添加/修改监考信息
    @PostMapping("/exams/addexam")
    public Map setExam(Exam exam){
        examService.addExam(exam);
        return Map.of("exams",examService.getAllExams());
    }
    //修改监考分配
    @PostMapping("/examdetails/addexamdetail")
    public Map setExamDetail(ExamDetail examDetail){
        examDetailService.addExamDetail(examDetail);
        return Map.of("examDetails",examDetailService.getAllExamDetails());
    }
    //添加/修改任务
    @PostMapping("/tasks/addtask")
    public Map setTask(Task task){
        taskService.addTask(task);
        return Map.of("tasks",taskService.getAllTasks());
    }
    //查询任务
    @GetMapping("/tasks")
    public Map getTasks(){
        return Map.of("tasks",taskService.getAllTasks());
    }
    //关闭任务
    @PostMapping("/tasks/closetask")
    public void closeTask(int kid){
        taskService.closeTask(kid);
    }
    //分配教师
    @PostMapping("/examdetails/distributeteacher")
    public ExamDetail distributeTeacher(int tid,int eid){
      return  distributeTeacher(tid,eid);
    }

    //查找教师的监考次数
    @GetMapping("/countofexams")
    public Map getAllCountOfExams() {
        List<List<Integer>> list = new ArrayList<>();
        userService.getTeacher()
                .forEach(a -> {
                    list.add(userService.getCountOfExam(a.getId()));
                });
        return Map.of("teacher-count",list);
    }
}
