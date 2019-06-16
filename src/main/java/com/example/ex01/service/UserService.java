package com.example.ex01.service;

import com.example.ex01.entity.User;
import com.example.ex01.repository.ExamDetailRepository;
import com.example.ex01.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ExamDetailRepository examDetailRepository;

    public User getrById(int tid){return userRepository.findById(tid);}
    public User getUserByNumber(String number) {
        return userRepository.find(number);
    }
    public List<User> getUserByTitle(String title) {
        return userRepository.findByTitle(title);
    }
    public List<User> getAdmin() {
        return userRepository.findListAdmin();
    }
    public List<User> getTeacher() {
        return userRepository.findListTeacher();
    }
    public List<User> getListOfTeacher(int eid){
        return examDetailRepository.listOfTeacher(eid);
    }
    //新建，修改
    public User addUser(User user){
        userRepository.save(user);
        return userRepository.refresh(user);
    }
    public List<User> getAllUsers(){return userRepository.findAllUsers();}
    public List<Integer> getCountOfExam(int tid){
        return List.of(tid,examDetailRepository.countOfExam(tid));
    }
}
