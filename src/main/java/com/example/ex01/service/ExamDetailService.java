package com.example.ex01.service;

import com.example.ex01.entity.Exam;
import com.example.ex01.entity.ExamDetail;
import com.example.ex01.entity.User;
import com.example.ex01.repository.ExamDetailRepository;
import com.example.ex01.repository.ExamRepository;
import com.example.ex01.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ExamDetailService {
    @Autowired
    private ExamDetailRepository examDetailRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ExamRepository examRepository;

    public ExamDetail ExamDetail(int eid,int tid) {
        return examDetailRepository.findExamDetail(eid,tid);
    }
    public ExamDetail addExamDetail(ExamDetail examDetail){
        examDetailRepository.save(examDetail);
        return examDetailRepository.refresh(examDetail);
    }
    public List<ExamDetail> getAllExamDetails(){
        return examDetailRepository.findAllExamDetails();
    }
    public ExamDetail distributeTeacher(int tid,int eid){
        User user = userRepository.findById(tid);
        Exam exam = examRepository.findById(eid);
        ExamDetail examDetail = new ExamDetail();
        examDetail.setTeacher(user);
        examDetail.setExam(exam);
        return examDetailRepository.save(examDetail);
    }
}