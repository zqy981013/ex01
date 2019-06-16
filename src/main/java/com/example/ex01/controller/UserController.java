package com.example.ex01.controller;

import com.example.ex01.entity.Exam;
import com.example.ex01.entity.TaskDetail;
import com.example.ex01.entity.User;
import com.example.ex01.service.ExamService;
import com.example.ex01.service.TaskDetailService;
import com.example.ex01.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/api")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private ExamService examService;
    @Autowired
    private TaskDetailService taskDetailService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    /**
     * 仅主页面时，教师加载自己的监考任务，管理员加载所有
     * @param uid
     * @param aid
     * @return
     */
    @GetMapping("/main")
    public Map getExams(@RequestAttribute int uid,@RequestAttribute int aid){
        List<Exam> exams = null;
        if(aid == User.ADMIN_AUTHORITY){
            exams = examService.getAllExams();
        }
        if(aid == User.USER_AUTHORITY) {
            exams = examService.getExamByTeacher(uid);
        }
        return Map.of("exams", exams);
    }
    //根据名称查询考试
    @GetMapping("/exams/{examname}")
    public Map getExamByName(@PathVariable String examname){
        Exam exam = null;
        exam = examService.getExamByName(examname);
        return Map.of("exam",exam);
    }
    //加载全部任务信息
    @GetMapping("/alltaskdetail")
    public Map getAllTaskDetail(){
        List<TaskDetail> taskDetails = null;
        taskDetails = taskDetailService.getAllTaskDetail();
        return Map.of("taskDetails",taskDetails);
    }
    //根据任务完成情况加载自己的任务信息
    @GetMapping("/mytaskdetail/{status}")
    public Map getMyTaskDetail(@RequestAttribute int tid,@PathVariable int status){
        List<TaskDetail> taskDetails = null;
        taskDetails = taskDetailService.getMyTaskDetail(tid,status);
        return Map.of("taskDetails",taskDetails);
    }
    //加载自己所有任务信息
    @GetMapping("/myalltaskdetail")
    public Map getAthorsTaskDetail(@RequestAttribute int tid){
        List<TaskDetail> taskDetails = null;
        taskDetails = taskDetailService.getMyAllTaskDetail(tid);
        return Map.of("taskDetails",taskDetails);
    }
    //修改密码
    @PostMapping("/modify")
    public void modify(@RequestBody User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userService.addUser(user);
    }
    //回复任务
    @PostMapping("/reply")
    public void replyTask(@RequestBody TaskDetail taskDetail,@RequestAttribute int kid){
        taskDetailService.replyTask(taskDetail);
    }

}
