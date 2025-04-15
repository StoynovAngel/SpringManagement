package com.angel.uni.management.service;

import com.angel.uni.management.dto.student.StudentRequestDTO;
import com.angel.uni.management.dto.student.StudentResponseDTO;
import org.apache.coyote.BadRequestException;

import java.util.List;

public interface StudentService {
    StudentRequestDTO createStudent(StudentRequestDTO studentRequestDTO) throws BadRequestException;
    StudentResponseDTO getStudentById(Long id);
    List<StudentResponseDTO> getAllStudents();
    StudentResponseDTO updateStudent(Long id, StudentResponseDTO studentResponseDTO) throws BadRequestException;
}
