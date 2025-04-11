package com.angel.uni.management.mapper.student;

import com.angel.uni.management.dto.StudentDTO;
import com.angel.uni.management.entity.Student;
import com.angel.uni.management.mapper.grade.GradeDTOMapper;
import org.springframework.stereotype.Service;

import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class StudentDTOMapper implements Function<Student, StudentDTO> {

    private final GradeDTOMapper gradeDTOMapper;

    public StudentDTOMapper(GradeDTOMapper gradeDTOMapper) {
        this.gradeDTOMapper = gradeDTOMapper;
    }

    @Override
    public StudentDTO apply(Student student) {
        return StudentDTO.builder()
                .id(student.getId())
                .username(student.getUsername())
                .averageGradeOverall(student.getAverageGradeOverall())
                .grades(student.getGrades().stream().map(gradeDTOMapper).collect(Collectors.toList())
                )
                .build();
    }
}
