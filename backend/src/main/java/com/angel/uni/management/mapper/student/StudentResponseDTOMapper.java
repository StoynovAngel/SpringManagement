package com.angel.uni.management.mapper.student;

import com.angel.uni.management.dto.student.StudentResponseDTO;
import com.angel.uni.management.entity.Student;
import com.angel.uni.management.mapper.grade.GradeDTOMapper;
import org.springframework.stereotype.Service;

import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class StudentResponseDTOMapper implements Function<Student, StudentResponseDTO> {

    private final GradeDTOMapper gradeDTOMapper;

    public StudentResponseDTOMapper(GradeDTOMapper gradeDTOMapper) {
        this.gradeDTOMapper = gradeDTOMapper;
    }

    @Override
    public StudentResponseDTO apply(Student student) {
        return StudentResponseDTO.builder()
                .id(student.getId())
                .username(student.getUsername())
                .averageGradeOverall(student.getAverageGradeOverall())
                .grades(student.getGrades().stream().map(gradeDTOMapper).collect(Collectors.toList()))
                .build();
    }
}
