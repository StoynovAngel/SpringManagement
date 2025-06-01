package com.angel.uni.management.service.impl;

import com.angel.uni.management.dto.GradeDTO;
import com.angel.uni.management.entity.Grade;
import com.angel.uni.management.exceptions.ResourceNotFoundException;
import com.angel.uni.management.mapper.grade.GradeMapper;
import com.angel.uni.management.repositories.GradeRepository;
import com.angel.uni.management.service.GradeService;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GradeServiceImpl implements GradeService {

    private final GradeRepository gradeRepository;
    private final GradeMapper gradeMapper;

    @Autowired
    public GradeServiceImpl(GradeRepository gradeRepository, GradeMapper gradeMapper) {
        this.gradeRepository = gradeRepository;
        this.gradeMapper = gradeMapper;
    }

    @Override
    public GradeDTO createGrade(GradeDTO gradeDTO) throws BadRequestException {
        if (gradeDTO == null) throw new BadRequestException("Cannot create grade: GradeDTO is null.");
        Grade grade = gradeMapper.toEntity(gradeDTO);
        Grade savedGrade = gradeRepository.save(grade);
        return gradeMapper.toDTO(savedGrade);
    }

    @Override
    public GradeDTO getGradeById(Long id) {
        Grade grade = gradeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Could not find grade with this id: " + id));
        return gradeMapper.toDTO(grade);
    }

    @Override
    public List<GradeDTO> getAllGrades() {
        List<Grade> grades = gradeRepository.findAll();
        return grades.stream().map(gradeMapper::toDTO).toList();
    }

    @Override
    public GradeDTO updateGrade(Long id, GradeDTO gradeDTO) throws BadRequestException {
        if (gradeDTO == null) throw new BadRequestException("Cannot update grade: GradeDTO is null");
        Grade gradeToBeUpdated = gradeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Cannot find a grade with this id: "+ id));
        gradeMapper.updateEntityFromDto(gradeDTO, gradeToBeUpdated);
        Grade updated = gradeRepository.save(gradeToBeUpdated);
        return gradeMapper.toDTO(updated);
    }
}
