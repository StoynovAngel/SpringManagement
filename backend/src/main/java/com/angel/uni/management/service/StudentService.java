package com.angel.uni.management.service;

import com.angel.uni.management.dto.StudentDTO;
import org.apache.coyote.BadRequestException;

import java.util.List;

public interface StudentService {
    StudentDTO createStudent(StudentDTO studentDTO) throws BadRequestException;
    StudentDTO getStudentById(Long id);
    List<StudentDTO> getAllStudents();
    StudentDTO updateStudent(Long id, StudentDTO studentDTO) throws BadRequestException;
}
