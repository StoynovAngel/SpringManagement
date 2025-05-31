package com.angel.uni.management.service;

import com.angel.uni.management.dto.TeacherDTO;
import com.angel.uni.management.entity.Teacher;
import com.angel.uni.management.exceptions.ResourceNotFoundException;
import com.angel.uni.management.mapper.teacher.TeacherMapper;
import com.angel.uni.management.repositories.TeacherRepository;
import com.angel.uni.management.service.impl.TeacherServiceImpl;
import org.apache.coyote.BadRequestException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class TeacherServiceTest {

    @Mock
    private TeacherMapper teacherMapper;

    @InjectMocks
    private TeacherServiceImpl teacherService;

    @Mock
    private TeacherRepository teacherRepository;

    private Teacher teacher;
    private TeacherDTO teacherDTO;

    @BeforeEach
    void setUp() {
        teacher = Teacher.builder().id(1L).name("teacher").build();
        teacherDTO = TeacherDTO.builder().id(1L).name("teacher").build();
    }

    @Test
    void testCreateTeacher() throws BadRequestException {
        Mockito.when(teacherMapper.toEntity(teacherDTO)).thenReturn(teacher);
        Mockito.when(teacherRepository.save(teacher)).thenReturn(teacher);
        Mockito.when(teacherMapper.toDTO(teacher)).thenReturn(teacherDTO);

        TeacherDTO saved = teacherService.createTeacher(teacherDTO);
        assertNotNull(saved);
    }

    @Test
    void testCreateTeacher_BadRequest() {
        assertThrows(BadRequestException.class, () -> {
            teacherService.createTeacher(null);
        });
    }

    @Test
    void testGetTeacherById() {
        Mockito.when(teacherRepository.findById(1L)).thenReturn(Optional.of(teacher));
        Mockito.when(teacherMapper.toDTO(teacher)).thenReturn(teacherDTO);
        TeacherDTO receivedTeacher = teacherService.getTeacherById(1L);
        assertNotNull(receivedTeacher);
    }

    @Test
    void testGetAllTeachers() {
        List<Teacher> teachers = List.of(teacher);
        Mockito.when(teacherRepository.findAll()).thenReturn(teachers);
        Mockito.when(teacherMapper.toDTO(teacher)).thenReturn(teacherDTO);

        List<TeacherDTO> results = teacherService.getAllTeachers();
        assertNotNull(results);
        Assertions.assertThat(results.size()).isGreaterThan(0);
    }

    @Test
    void testGetAllTeachers_ResourceNotFound() {
        assertThrows(ResourceNotFoundException.class, () -> {
            teacherService.getAllTeachers();
        });
    }

    @Test
    void testUpdateTeacher() throws BadRequestException {
        Long teacherId = 1L;
        Teacher updateTeacher = Teacher.builder().name("updated_username").build();
        TeacherDTO updateTeacherDTO = TeacherDTO.builder().name("updated_username").build();

        Mockito.when(teacherRepository.findById(teacherId)).thenReturn(Optional.of(teacher));
        Mockito.when(teacherRepository.save(Mockito.any(Teacher.class))).thenReturn(updateTeacher);
        Mockito.when(teacherMapper.toDTO(updateTeacher)).thenReturn(updateTeacherDTO);

        TeacherDTO result = teacherService.updateTeacher(teacherId, updateTeacherDTO);
        assertNotNull(result);
        assertEquals("updated_username", result.name());
    }

    @Test
    void testUpdateTeacher_ResourceNotFound() {
        Long teacherId = 1L;
        assertThrows(ResourceNotFoundException.class, () -> {
            teacherService.updateTeacher(teacherId, null);
        });
    }
}