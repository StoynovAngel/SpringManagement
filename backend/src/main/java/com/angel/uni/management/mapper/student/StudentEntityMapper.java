package com.angel.uni.management.mapper.student;

import com.angel.uni.management.dto.StudentDTO;
import com.angel.uni.management.entity.Student;
import com.angel.uni.management.mapper.grade.GradeEntityMapper;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class StudentEntityMapper implements Function<StudentDTO, Student> {

    private final GradeEntityMapper gradeEntityMapper;

    public StudentEntityMapper(GradeEntityMapper gradeEntityMapper) {
        this.gradeEntityMapper = gradeEntityMapper;
    }

    @Override
    public Student apply(StudentDTO studentDTO) {
        return Student.builder()
                .id(studentDTO.id())
                .username(studentDTO.username())
                .grades(studentDTO.grades().stream().map(gradeEntityMapper).toList())
                .averageGradeOverall(studentDTO.averageGradeOverall())
                .build();
    }
}
