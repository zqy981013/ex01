package com.example.ex01;

import com.example.ex01.controller.UserController;
import com.example.ex01.entity.Exam;
import com.example.ex01.entity.User;
import com.example.ex01.service.ExamService;
import com.example.ex01.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class addUserTest {
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserService userService;
    @Autowired
    private ExamService examService;
    @Test
    public void addUser(){
        User user = new User();
        user.setAuthority(User.USER_AUTHORITY);
        user.setNumber("ZCN");
        user.setPassword(passwordEncoder.encode("123456"));
        user.setName("ZCN");
        userService.addUser(user);
    }

}
