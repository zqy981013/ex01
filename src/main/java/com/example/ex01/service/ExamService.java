package com.example.ex01.service;

import com.example.ex01.entity.Exam;
import com.example.ex01.repository.ExamDetailRepository;
import com.example.ex01.repository.ExamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ExamService {
    @Autowired
    private ExamRepository examRepository;
    @Autowired
    private ExamDetailRepository examDetailRepository;

    public List<Exam> getExamByStatus(int es) {
        return examRepository.findByStatus(es);
    }
    public List<Exam> getAllExams(){return examRepository.findAllExams();}
    public Exam getExamByName(String examName) {
        return examRepository.findByName(examName);
    }
    public List<Exam> getTomorrowExam() {
        return examRepository.tomorrowExam();
    }
    public List<Exam> getExamByTeacher(int tid) {
        return examDetailRepository.listOfExam(tid);
    }
    public Exam addExam(Exam exam){
        examRepository.save(exam);
        return examRepository.refresh(exam);
    }
    public Exam getExamById(int eid){
        return examRepository.findById(eid);
    }

}
