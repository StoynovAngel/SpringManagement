package com.angel.uni.management.service.impl;

import com.angel.uni.management.dto.GradeDTO;
import com.angel.uni.management.entity.Grade;
import com.angel.uni.management.entity.Teacher;
import com.angel.uni.management.exceptions.ResourceNotFoundException;
import com.angel.uni.management.mapper.grade.GradeMapper;
import com.angel.uni.management.repositories.GradeRepository;
import com.angel.uni.management.repositories.TeacherRepository;
import com.angel.uni.management.service.GradeService;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GradeServiceImpl implements GradeService {

    private final GradeRepository gradeRepository;
    private final GradeMapper gradeMapper;
    private final TeacherRepository teacherRepository;

    @Autowired
    public GradeServiceImpl(GradeRepository gradeRepository, GradeMapper gradeMapper, TeacherRepository teacherRepository) {
        this.gradeRepository = gradeRepository;
        this.teacherRepository = teacherRepository;
        this.gradeMapper = gradeMapper;
    }

    @Override
    public GradeDTO createGrade(GradeDTO gradeDTO) throws BadRequestException {

        if (gradeDTO == null) {
            throw new BadRequestException("Cannot create grade: GradeDTO is null.");
        }

        Teacher teacher = teacherRepository.findById(gradeDTO.teacherId()).orElseThrow(() -> new ResourceNotFoundException("Teacher not found with this id: " + gradeDTO.teacherId()));

        Grade grade1 = gradeMapper.toEntity(gradeDTO);
        grade1.setTeacher(teacher);
        Grade savedGrade = gradeRepository.save(grade1);
        return gradeMapper.toDTO(savedGrade);
    }

    @Override
    public GradeDTO getGradeById(Long id) {
        Grade grade = gradeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Grade not found with this id:" + id));
        return gradeMapper.toDTO(grade);
    }

    @Override
    public List<GradeDTO> getAllGrades() {
        List<Grade> grades = gradeRepository.findAll();
        if (grades.isEmpty()) {
            throw new ResourceNotFoundException("Grades' list cannot be empty.");
        }
        return grades.stream().map(gradeMapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public GradeDTO updateGrade(Long id, GradeDTO gradeDTO) throws BadRequestException {
        if (gradeDTO == null) {
            throw new BadRequestException("Cannot update grade: GradeDTO is null.");
        }

        Grade gradeToBeEdited = gradeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Grade with this id not found: " + id));

        Teacher teacher = teacherRepository.findById(gradeDTO.teacherId())
                .orElseThrow(() -> new ResourceNotFoundException("Teacher not found with id " + gradeDTO.teacherId()));
        gradeToBeEdited.setTeacher(teacher);

        gradeToBeEdited.setName(gradeDTO.name());
        gradeToBeEdited.setGradeType(gradeDTO.gradeType());
        gradeToBeEdited.setMark(gradeDTO.mark());
        gradeToBeEdited.setDateOfGrading(gradeDTO.dateOfGrading());
        gradeToBeEdited.setCountryRepresentation(gradeDTO.countryRepresentation());

        Grade savedGrade = gradeRepository.save(gradeToBeEdited);
        return gradeMapper.toDTO(savedGrade);
    }
}
