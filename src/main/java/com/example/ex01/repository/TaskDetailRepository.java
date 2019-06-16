package com.example.ex01.repository;

import com.example.ex01.entity.TaskDetail;
import com.example.ex01.repository.CustomizedRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface TaskDetailRepository extends CustomizedRepository<TaskDetail, Integer> {
    //查询所有任务信息
    @Query("SELECT td FROM TaskDetail td")
    List<TaskDetail> findAllTaskDetail();
    //根据任务完成情况查询
    @Query("SELECT td FROM TaskDetail td WHERE td.teacher.id =:tid AND td.status =:status")
    List<TaskDetail> findMyTaskDetail(@Param("tid") int tid,@Param("status") int status);
    //根据任务id查询任务情况
    @Query("SELECT td FROM TaskDetail td WHERE td.task.id =:tid")
    List<TaskDetail> findMyAllTaskDetail(@Param("tid") int tid);
}
