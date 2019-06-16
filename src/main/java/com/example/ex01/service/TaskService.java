package com.example.ex01.service;

import com.example.ex01.entity.Task;
import com.example.ex01.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;
    public List<Task> getAllTasks(){
        return taskRepository.findAllTasks();
    }
    //添加任务
    public Task addTask(Task task){
        taskRepository.save(task);
        return taskRepository.refresh(task);
    }
    //关闭任务
    public void closeTask(int kid){
        Task t = taskRepository.findTaskById(kid);
        t.setStatus(2);
        taskRepository.save(t);
    }
}
