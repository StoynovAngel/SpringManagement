package com.angel.uni.management.service;

import com.angel.uni.management.dto.GradeDTO;
import com.angel.uni.management.entity.Grade;
import com.angel.uni.management.entity.Teacher;
import com.angel.uni.management.enums.CountryEnum;
import com.angel.uni.management.enums.GradeType;
import com.angel.uni.management.exceptions.ResourceNotFoundException;
import com.angel.uni.management.mapper.grade.GradeDTOMapper;
import com.angel.uni.management.mapper.grade.GradeEntityMapper;
import com.angel.uni.management.mapper.teacher.TeacherEntityMapper;
import com.angel.uni.management.repositories.GradeRepository;
import com.angel.uni.management.repositories.TeacherRepository;
import com.angel.uni.management.service.impl.GradeServiceImpl;
import org.apache.coyote.BadRequestException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class GradeServiceTest {

    @Mock
    private GradeRepository gradeRepository;

    @InjectMocks
    private GradeServiceImpl gradeService;

    @Mock
    private GradeDTOMapper gradeDTOMapper;

    @Mock
    private TeacherRepository teacherRepository;

    @Mock
    private GradeEntityMapper gradeEntityMapper;

    @Mock
    private TeacherEntityMapper teacherEntityMapper;

    private GradeDTO gradeDTO;
    private Grade grade;
    private Teacher teacher;

    @BeforeEach
    void setUp() {
        teacher = Teacher.builder().id(1L).name("teacher").build();
        gradeDTO = GradeDTO.builder()
                .id(1L)
                .name("grade")
                .teacherId(1L)
                .gradeType(GradeType.FINAL)
                .mark(5.0)
                .dateOfGrading(LocalDateTime.now())
                .countryEnum(CountryEnum.BG)
                .build();

        grade = Grade.builder()
                .id(1L)
                .name("grade")
                .teacher(teacher)
                .gradeType(GradeType.FINAL)
                .mark(5.0)
                .dateOfGrading(LocalDateTime.now())
                .countryRepresentation(CountryEnum.BG)
                .build();

        teacherEntityMapper = new TeacherEntityMapper();
    }

    @Test
    void testCreateGrade() throws BadRequestException {
        Mockito.when(teacherRepository.findById(1L)).thenReturn(Optional.of(teacher));
        Mockito.when(gradeEntityMapper.apply(gradeDTO)).thenReturn(grade);
        Mockito.when(gradeRepository.save(grade)).thenReturn(grade);
        Mockito.when(gradeDTOMapper.apply(grade)).thenReturn(gradeDTO);

        GradeDTO result = gradeService.createGrade(gradeDTO);
        assertNotNull(result);
        assertEquals("grade", result.name());
    }

    @Test
    void testCreateGrade_BadRequest() {
        assertThrows(BadRequestException.class, () -> {
            gradeService.createGrade(null);
        });
    }

    @Test
    void testGetGradeById() {
        Mockito.when(gradeRepository.findById(1L)).thenReturn(Optional.of(grade));
        Mockito.when(gradeDTOMapper.apply(grade)).thenReturn(gradeDTO);
        GradeDTO result = gradeService.getGradeById(1L);
        assertEquals("grade", result.name());
    }

    @Test
    void testGetGradeById_ResourceNotFound() {
        assertThrows(ResourceNotFoundException.class, () -> {
            gradeService.getGradeById(1L);
        });
    }

    @Test
    void testGetAllGrades() {
        List<Grade> gradesList = new ArrayList<>();
        gradesList.add(grade);
        Mockito.when(gradeRepository.findAll()).thenReturn(gradesList);
        Mockito.when(gradeDTOMapper.apply(grade)).thenReturn(gradeDTO);

        List<GradeDTO> results = gradeService.getAllGrades();
        assertNotNull(results);
        Assertions.assertThat(results.size()).isGreaterThan(0);
    }

    @Test
    void testGetAllGrades_ResourceNotFound() {
        assertThrows(ResourceNotFoundException.class, () -> {
            gradeService.getAllGrades();
        });
    }

    @Test
    void testUpdateGrade() throws BadRequestException {
        GradeDTO updatedGradeDTO = GradeDTO.builder()
                .name("updated_grade")
                .teacherId(1L)
                .gradeType(GradeType.ORAL)
                .mark(4.25)
                .dateOfGrading(LocalDateTime.now())
                .countryEnum(CountryEnum.DE)
                .build();

        Mockito.when(gradeRepository.findById(1L)).thenReturn(Optional.of(grade));
        Mockito.when(teacherRepository.findById(updatedGradeDTO.teacherId())).thenReturn(Optional.of(teacher));
        Mockito.when(gradeRepository.save(Mockito.any(Grade.class))).thenReturn(grade);
        Mockito.when(gradeDTOMapper.apply(Mockito.any(Grade.class))).thenReturn(updatedGradeDTO);

        GradeDTO result = gradeService.updateGrade(1L, updatedGradeDTO);

        assertNotNull(result);
        assertEquals("updated_grade", result.name());
    }

    @Test
    void testUpdateGrade_BadRequest() {
        assertThrows(BadRequestException.class, () -> {
           gradeService.updateGrade(1L, null);
        });
    }
}