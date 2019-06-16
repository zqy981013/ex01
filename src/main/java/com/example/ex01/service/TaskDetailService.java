package com.example.ex01.service;

import com.example.ex01.entity.TaskDetail;
import com.example.ex01.repository.TaskDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class TaskDetailService {
    @Autowired
    private TaskDetailRepository taskDetailRepository;
    public List<TaskDetail> getAllTaskDetail(){
        return taskDetailRepository.findAllTaskDetail();
    }
    public List<TaskDetail> getMyTaskDetail(int tid,int status){
        return taskDetailRepository.findMyTaskDetail(tid,status);
    }
    public List<TaskDetail> getMyAllTaskDetail(int tid){
        return taskDetailRepository.findMyAllTaskDetail(tid);
    }
    public TaskDetail replyTask(TaskDetail taskDetail){
        taskDetailRepository.save(taskDetail);
        return taskDetailRepository.refresh(taskDetail);
    }
}
