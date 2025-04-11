package com.angel.uni.management.service;

import com.angel.uni.management.dto.TeacherDTO;
import org.apache.coyote.BadRequestException;

import java.util.List;

public interface TeacherService {
    TeacherDTO createTeacher(TeacherDTO teacherDTO) throws BadRequestException;
    TeacherDTO getTeacherById(Long id);
    List<TeacherDTO> getAllTeachers();
    TeacherDTO updateTeacher(Long id, TeacherDTO teacherDTO) throws BadRequestException;
}
