package com.angel.uni.management.controller;

import com.angel.uni.management.dto.StudentDTO;
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
    public StudentDTO createStudent(@RequestBody StudentDTO studentDTO) throws BadRequestException {
        return studentService.createStudent(studentDTO);
    }

    @GetMapping
    @ResponseStatus(value = HttpStatus.OK)
    public List<StudentDTO> getAllStudents() {
        return studentService.getAllStudents();
    }

    @GetMapping("/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public StudentDTO getStudentById(@PathVariable("id") Long id) {
        return studentService.getStudentById(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public StudentDTO updateStudentById(@PathVariable("id") Long id, @RequestBody StudentDTO studentDTO) throws BadRequestException {
        return studentService.updateStudent(id, studentDTO);
    }
}
