package com.angel.uni.management.service.impl;

import com.angel.uni.management.mapper.student.StudentMapper;
import com.angel.uni.management.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentServiceImpl {

    private final StudentMapper studentMapper;
    private final StudentRepository studentRepository;

    @Autowired
    public StudentServiceImpl(StudentMapper studentMapper, StudentRepository studentRepository) {
        this.studentMapper = studentMapper;
        this.studentRepository = studentRepository;
    }
}