package com.example.ex01.repository;

import com.example.ex01.entity.User;
import com.example.ex01.repository.CustomizedRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public interface UserRepository extends CustomizedRepository<User,Integer> {
    @Query("SELECT u FROM User u WHERE u.id=:tid")
    User findById(@Param("tid") int tid);
    //根据id查找用户
    @Query("SELECT u FROM User u WHERE u.number=:number")
    User find(@Param("number") String number);
    @Query("SELECT u FROM User u WHERE u.title=:title")
    List<User> findByTitle(@Param("title") String title);
    @Query("SELECT u FROM User u WHERE u.authority=2")
    List<User> findListAdmin();
    @Query("SELECT u FROM User u WHERE u.authority=1")
    List<User> findListTeacher();
    @Query("SELECT u FROM User u ")
    List<User> findAllUsers();

}
