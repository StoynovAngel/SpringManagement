package com.angel.uni.management.mapper.student;

import com.angel.uni.management.dto.student.StudentRequestDTO;
import com.angel.uni.management.entity.Student;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class StudentRequestEntityMapper implements Function<StudentRequestDTO, Student> {

    @Override
    public Student apply(StudentRequestDTO studentRequestDTO) {
        return Student.builder()
                .username(studentRequestDTO.username())
                .grades(null) // set in service
                .build();
    }
}
