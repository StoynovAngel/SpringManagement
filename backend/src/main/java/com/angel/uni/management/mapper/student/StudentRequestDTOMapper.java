package com.angel.uni.management.mapper.student;

import com.angel.uni.management.dto.student.StudentRequestDTO;
import com.angel.uni.management.entity.Grade;
import com.angel.uni.management.entity.Student;
import org.springframework.stereotype.Service;

import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class StudentRequestDTOMapper implements Function<Student, StudentRequestDTO> {

    @Override
    public StudentRequestDTO apply(Student student) {
        return StudentRequestDTO.builder()
                .username(student.getUsername())
                .gradeIds(student.getGrades().stream().map(Grade::getId).collect(Collectors.toList()))
                .build();
    }
}
