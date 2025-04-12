package com.angel.uni.management.service;

import com.angel.uni.management.dto.GradeDTO;
import org.apache.coyote.BadRequestException;

import java.util.List;

public interface GradeService {
    GradeDTO createGrade(GradeDTO gradeDTO) throws BadRequestException;
    GradeDTO getGradeById(Long id);
    List<GradeDTO> getAllGrades();
    GradeDTO updateGrade(Long id, GradeDTO gradeDTO) throws BadRequestException;
}
