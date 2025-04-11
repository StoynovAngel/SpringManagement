package com.angel.uni.management.service;

import com.angel.uni.management.dto.StudentDTO;
import com.angel.uni.management.entity.Student;
import com.angel.uni.management.exceptions.ResourceNotFoundException;
import com.angel.uni.management.mapper.grade.GradeEntityMapper;
import com.angel.uni.management.mapper.student.StudentDTOMapper;
import com.angel.uni.management.mapper.student.StudentEntityMapper;
import com.angel.uni.management.repositories.StudentRepository;
import com.angel.uni.management.service.impl.StudentServiceImpl;
import org.apache.coyote.BadRequestException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class StudentServiceTest {

    @Mock
    private StudentDTOMapper studentDTOMapper;

    @Mock
    private StudentEntityMapper studentEntityMapper;

    @Mock
    private StudentRepository studentRepository;

    @InjectMocks
    private StudentServiceImpl studentService;

    @Mock
    private GradeEntityMapper gradeEntityMapper;

    private Student student;
    private StudentDTO studentDTO;

    @BeforeEach
    void setUp() {
        student = new Student();
        student.setUsername("ivan123");
        student.setAverageGradeOverall(5.50);

        studentDTO = new StudentDTO(1L, "ivan123", List.of(), 5.50);
        gradeEntityMapper = new GradeEntityMapper();
    }

    @Test
    void testCreateStudent() throws BadRequestException {
        when(studentEntityMapper.apply(studentDTO)).thenReturn(student);
        when(studentRepository.save(student)).thenReturn(student);
        when(studentDTOMapper.apply(student)).thenReturn(studentDTO);

        StudentDTO saved = studentService.createStudent(studentDTO);

        assertNotNull(saved);
        assertEquals("ivan123", saved.username());
        assertEquals(5.50, saved.averageGradeOverall());
    }

    @Test
    void testCreateStudent_BadRequestException() {
        assertThrows(BadRequestException.class, () -> {
            studentService.createStudent(null);
        });
    }

    @Test
    void testGetStudentById() {
        when(studentRepository.findById(1L)).thenReturn(Optional.of(student));
        when(studentDTOMapper.apply(student)).thenReturn(studentDTO);
        StudentDTO receivedStudent = studentService.getStudentById(1L);
        assertNotNull(receivedStudent);
    }


    @Test
    void testGetAllStudents() {
        Student student = Student.builder().id(1L).username("angel").build();
        StudentDTO studentDTO = StudentDTO.builder().id(1L).username("angel").grades(List.of()).averageGradeOverall(0.0).build();
        List<Student> studentList = List.of(student);

        when(studentRepository.findAll()).thenReturn(studentList);
        when(studentDTOMapper.apply(student)).thenReturn(studentDTO);

        List<StudentDTO> result = studentService.getAllStudents();

        assertNotNull(result);
        assertFalse(result.isEmpty());
    }

    @Test
    void testGetAllStudents_ResourceNotFound() {
        assertThrows(ResourceNotFoundException.class, () -> {
            studentService.getAllStudents();
        });
    }

    @Test
    void testUpdateStudent() throws BadRequestException {
        Long studentId = 1L;
        Student updatedStudent = Student.builder().username("updated_username").averageGradeOverall(6.00).build();
        StudentDTO updatedDTO = StudentDTO.builder()
                .id(studentId).username("updated_username").grades(List.of()).averageGradeOverall(6.00).build();

        when(studentRepository.findById(studentId)).thenReturn(Optional.of(student));
        when(studentRepository.save(Mockito.any(Student.class))).thenReturn(updatedStudent);
        when(studentDTOMapper.apply(updatedStudent)).thenReturn(updatedDTO);

        StudentDTO result = studentService.updateStudent(studentId, updatedDTO);

        assertNotNull(result);
        assertEquals("updated_username", result.username());
        assertEquals(6.00, result.averageGradeOverall());
    }

    @Test
    void testUpdateStudent_BadRequest() {
        Long studentId = 1L;
        assertThrows(BadRequestException.class, () -> {
            studentService.updateStudent(studentId, null);
        });
    }
}