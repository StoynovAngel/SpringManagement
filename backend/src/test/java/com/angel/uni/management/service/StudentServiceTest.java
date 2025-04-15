package com.angel.uni.management.service;

import com.angel.uni.management.dto.student.StudentRequestDTO;
import com.angel.uni.management.dto.student.StudentResponseDTO;
import com.angel.uni.management.entity.Student;
import com.angel.uni.management.exceptions.ResourceNotFoundException;
import com.angel.uni.management.mapper.grade.GradeEntityMapper;
import com.angel.uni.management.mapper.student.StudentMapper;
import com.angel.uni.management.repositories.StudentRepository;
import com.angel.uni.management.service.impl.StudentServiceImpl;
import org.apache.coyote.BadRequestException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Answers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class StudentServiceTest {

    @Mock(answer = Answers.RETURNS_DEEP_STUBS)
    private StudentMapper studentMapper;

    @Mock
    private StudentRepository studentRepository;

    @InjectMocks
    private StudentServiceImpl studentService;

    @Mock
    private GradeEntityMapper gradeEntityMapper;

    private Student student;
    private StudentResponseDTO studentResponseDTO;
    private StudentRequestDTO studentRequestDTO;


    @BeforeEach
    void setUp() {
        student = new Student();
        student.setUsername("ivan123");
        student.setAverageGradeOverall(5.50);

        studentResponseDTO = new StudentResponseDTO(1L, "ivan123", List.of(), 5.50);
        studentRequestDTO = new StudentRequestDTO("ivan123", List.of());
        gradeEntityMapper = new GradeEntityMapper();
    }

    @Test
    void testCreateStudent() throws BadRequestException {
        when(studentMapper.requestToEntity(studentRequestDTO)).thenReturn(student);
        when(studentRepository.save(student)).thenReturn(student);
        when(studentMapper.toRequestDTO(student)).thenReturn(studentRequestDTO);

        StudentRequestDTO saved = studentService.createStudent(studentRequestDTO);

        assertNotNull(saved);
        assertEquals("ivan123", saved.username());
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
        when(studentMapper.toResponseDTO(student)).thenReturn(studentResponseDTO);
        StudentResponseDTO receivedStudent = studentService.getStudentById(1L);
        assertNotNull(receivedStudent);
    }


    @Test
    void testGetAllStudents() {
        Student student = Student.builder().id(1L).username("angel").build();
        StudentResponseDTO studentResponseDTO = StudentResponseDTO.builder().id(1L).username("angel").grades(List.of()).averageGradeOverall(0.0).build();
        List<Student> studentList = List.of(student);

        when(studentRepository.findAll()).thenReturn(studentList);
        when(studentMapper.toResponseDTO(student)).thenReturn(studentResponseDTO);

        List<StudentResponseDTO> result = studentService.getAllStudents();

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
        StudentResponseDTO updatedDTO = StudentResponseDTO.builder()
                .id(studentId).username("updated_username").grades(List.of()).averageGradeOverall(6.00).build();

        when(studentRepository.findById(studentId)).thenReturn(Optional.of(student));
        when(studentRepository.save(Mockito.any(Student.class))).thenReturn(updatedStudent);
        when(studentMapper.toResponseDTO(updatedStudent)).thenReturn(updatedDTO);

        StudentResponseDTO result = studentService.updateStudent(studentId, updatedDTO);

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