package com.angel.uni.management.mapper.grade;

import com.angel.uni.management.dto.GradeDTO;
import com.angel.uni.management.entity.Grade;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class GradeEntityMapper implements Function<GradeDTO, Grade> {

    @Override
    public Grade apply(GradeDTO gradeDTO) {
        return Grade.builder()
                .id(gradeDTO.id())
                .name(gradeDTO.name())
                .teacher(null) // Set in service layer
                .gradeType(gradeDTO.gradeType())
                .mark(gradeDTO.mark())
                .dateOfGrading(gradeDTO.dateOfGrading())
                .countryRepresentation(gradeDTO.countryEnum())
                .build();
    }
}
