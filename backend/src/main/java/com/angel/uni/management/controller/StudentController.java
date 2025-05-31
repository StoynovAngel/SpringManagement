package com.angel.uni.management.controller;

import com.angel.uni.management.dto.student.StudentRequestDTO;
import com.angel.uni.management.dto.student.StudentResponseDTO;
import com.angel.uni.management.service.StudentService;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/student")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public StudentRequestDTO createStudent(@RequestBody StudentRequestDTO studentRequestDTO) throws BadRequestException {
        return studentService.createStudent(studentRequestDTO);
    }

    @GetMapping
    @ResponseStatus(value = HttpStatus.OK)
    public List<StudentResponseDTO> getAllStudents() {
        return studentService.getAllStudents();
    }

    @GetMapping("/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public StudentResponseDTO getStudentById(@PathVariable("id") Long id) {
        return studentService.getStudentById(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public StudentResponseDTO updateStudentById(@PathVariable("id") Long id, @RequestBody StudentResponseDTO studentResponseDTO) throws BadRequestException {
        return studentService.updateStudent(id, studentResponseDTO);
    }
}
