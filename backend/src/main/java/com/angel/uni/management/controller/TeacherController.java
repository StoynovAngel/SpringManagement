package com.angel.uni.management.controller;

import com.angel.uni.management.dto.TeacherDTO;
import com.angel.uni.management.service.TeacherService;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/teacher")
public class TeacherController {

    private final TeacherService teacherService;

    @Autowired
    public TeacherController(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public TeacherDTO createTeacher(@RequestBody TeacherDTO teacherDTO) throws BadRequestException {
        return teacherService.createTeacher(teacherDTO);
    }

    @GetMapping("/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public TeacherDTO getTeacherById(@PathVariable("id") Long id) {
        return teacherService.getTeacherById(id);
    }

    @GetMapping
    @ResponseStatus(value = HttpStatus.OK)
    public List<TeacherDTO> getAllTeachers() {
        return teacherService.getAllTeachers();
    }

    @PutMapping("/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public TeacherDTO updateTeacher(@PathVariable("id") Long id, @RequestBody TeacherDTO teacherDTO) throws BadRequestException {
        return teacherService.updateTeacher(id, teacherDTO);
    }
}
