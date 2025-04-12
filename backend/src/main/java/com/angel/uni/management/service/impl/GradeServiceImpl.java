package com.angel.uni.management.service.impl;

import com.angel.uni.management.dto.GradeDTO;
import com.angel.uni.management.entity.Grade;
import com.angel.uni.management.entity.Teacher;
import com.angel.uni.management.exceptions.ResourceNotFoundException;
import com.angel.uni.management.mapper.grade.GradeDTOMapper;
import com.angel.uni.management.mapper.grade.GradeEntityMapper;
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
    private final GradeEntityMapper gradeEntityMapper;
    private final GradeDTOMapper gradeDTOMapper;
    private final TeacherRepository teacherRepository;

    @Autowired
    public GradeServiceImpl(GradeRepository gradeRepository, GradeEntityMapper gradeEntityMapper, GradeDTOMapper gradeDTOMapper, TeacherRepository teacherRepository) {
        this.gradeRepository = gradeRepository;
        this.gradeEntityMapper = gradeEntityMapper;
        this.gradeDTOMapper = gradeDTOMapper;
        this.teacherRepository = teacherRepository;
    }

    @Override
    public GradeDTO createGrade(GradeDTO gradeDTO) throws BadRequestException {

        if (gradeDTO == null) {
            throw new BadRequestException("Cannot create grade: GradeDTO is null.");
        }

        Teacher teacher = teacherRepository.findById(gradeDTO.teacherId()).orElseThrow(() -> new ResourceNotFoundException("Teacher not found with this id: " + gradeDTO.teacherId()));
        Grade grade = Grade.builder()
                .id(gradeDTO.id())
                .name(gradeDTO.name())
                .teacher(teacher)
                .gradeType(gradeDTO.gradeType())
                .mark(gradeDTO.mark())
                .dateOfGrading(gradeDTO.dateOfGrading())
                .countryRepresentation(gradeDTO.countryEnum())
                .build();

        Grade savedGrade = gradeRepository.save(grade);
        return gradeDTOMapper.apply(savedGrade);
    }

    @Override
    public GradeDTO getGradeById(Long id) {
        Grade grade = gradeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Grade not found with this id:" + id));
        return gradeDTOMapper.apply(grade);
    }

    @Override
    public List<GradeDTO> getAllGrades() {
        List<Grade> grades = gradeRepository.findAll();
        if (grades.isEmpty()) {
            throw new ResourceNotFoundException("Grades' list cannot be empty.");
        }
        return grades.stream().map(gradeDTOMapper).collect(Collectors.toList());
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
        gradeToBeEdited.setCountryRepresentation(gradeDTO.countryEnum());

        Grade savedGrade = gradeRepository.save(gradeToBeEdited);
        return gradeDTOMapper.apply(savedGrade);
    }
}
