package com.angel.uni.management.mapper.student;

import com.angel.uni.management.dto.student.StudentResponseDTO;
import com.angel.uni.management.entity.Student;
import com.angel.uni.management.mapper.grade.GradeEntityMapper;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class StudentResponseEntityMapper implements Function<StudentResponseDTO, Student> {

    private final GradeEntityMapper gradeEntityMapper;

    public StudentResponseEntityMapper(GradeEntityMapper gradeEntityMapper) {
        this.gradeEntityMapper = gradeEntityMapper;
    }

    @Override
    public Student apply(StudentResponseDTO studentResponseDTO) {
        return Student.builder()
                .id(studentResponseDTO.id())
                .username(studentResponseDTO.username())
                .grades(studentResponseDTO.grades().stream().map(gradeEntityMapper).toList())
                .averageGradeOverall(studentResponseDTO.averageGradeOverall())
                .build();
    }
}
