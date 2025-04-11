package com.angel.uni.management.mapper.grade;

import com.angel.uni.management.dto.GradeDTO;
import com.angel.uni.management.entity.Grade;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class GradeDTOMapper implements Function<Grade, GradeDTO> {

    @Override
    public GradeDTO apply(Grade grade) {
        return GradeDTO.builder()
                .id(grade.getId())
                .name(grade.getName())
                .teacherId(grade.getTeacher().getId())
                .gradeType(grade.getGradeType())
                .mark(grade.getMark())
                .dateOfGrading(grade.getDateOfGrading())
                .countryEnum(grade.getCountryRepresentation())
                .build();
    }
}
