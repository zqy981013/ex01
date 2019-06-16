package com.example.ex01.repository;

import com.example.ex01.entity.Exam;
import com.example.ex01.repository.CustomizedRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.List;

@Repository
public interface ExamRepository extends CustomizedRepository<Exam,Integer> {
    //查询所有考试信息
    @Query("SELECT e FROM Exam e ")
    List<Exam> findAllExams();
    //根据id查询考试
    @Query("SELECT e FROM Exam e WHERE e.id=:eid")
    Exam findById(@Param("eid") int eid);
    //根据状态查询考试
    @Query("FROM Exam e WHERE e.status=:es")
    List<Exam> findByStatus(@Param("es") int es);
    //根据名称查询考试
    @Query("FROM Exam e WHERE e.examName=:examName")
    Exam findByName(@Param("examName") String examName);
    //查询第二日考试
    Calendar now = Calendar.getInstance();
    int year = now.get(Calendar.YEAR);
    int month = now.get(Calendar.MONTH);
    int day = now.get(Calendar.DAY_OF_MONTH);
    String today = String.valueOf(year) + "-" +String.valueOf(month)+"-"+String.valueOf(day);
    String tomorrow =String.valueOf(year)+"-"+String.valueOf(month)+"-"+String.valueOf(day+1);
    @Query("SELECT e FROM Exam e WHERE e.beginTime between today and tomorrow")
    List<Exam> tomorrowExam();

}
