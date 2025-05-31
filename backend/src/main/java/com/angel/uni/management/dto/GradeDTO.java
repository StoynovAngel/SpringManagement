package com.angel.uni.management.dto;

import com.angel.uni.management.enums.CountryEnum;
import com.angel.uni.management.enums.GradeType;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record GradeDTO(
        Long id,
        String name,
        Long teacherId,
        GradeType gradeType,
        double mark,
        LocalDateTime dateOfGrading,
        CountryEnum countryRepresentation
) { }

