package com.angel.uni.management.service.impl;

import com.angel.uni.management.dto.TeacherDTO;
import com.angel.uni.management.entity.Teacher;
import com.angel.uni.management.exceptions.ResourceNotFoundException;
import com.angel.uni.management.mapper.teacher.TeacherMapper;
import com.angel.uni.management.repositories.TeacherRepository;
import com.angel.uni.management.service.TeacherService;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TeacherServiceImpl implements TeacherService {

    private final TeacherRepository repository;
    private final TeacherMapper teacherMapper;

    @Autowired
    public TeacherServiceImpl(TeacherRepository repository, TeacherMapper teacherMapper) {
        this.repository = repository;
        this.teacherMapper = teacherMapper;
    }

    @Override
    public TeacherDTO createTeacher(TeacherDTO teacherDTO) throws BadRequestException {
        if (teacherDTO == null) {
            throw new BadRequestException("Cannot create teacher: Provided TeacherDTO is null");
        }

        Teacher teacher = teacherMapper.toEntity(teacherDTO);
        Teacher saved = repository.save(teacher);
        return teacherMapper.toDTO(saved);
    }

    @Override
    public TeacherDTO getTeacherById(Long id) {
        Teacher teacher = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Teacher not found with this id:" + id));
        return teacherMapper.toDTO(teacher);
    }

    @Override
    public List<TeacherDTO> getAllTeachers() {
        List<Teacher> teachers = repository.findAll();

        if (teachers.isEmpty()) {
            throw new ResourceNotFoundException("Could not get all teachers. Teachers' list is empty.");
        }

        return teachers.stream().map(teacherMapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public TeacherDTO updateTeacher(Long id, TeacherDTO teacherDTO) throws BadRequestException {

        if (teacherDTO == null) {
            throw new ResourceNotFoundException("Cannot update teacher: teacherDTO is null");
        }

        Teacher teacher = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Teacher not found with this id: " + id));
        Teacher updatedTeacher = updateTeacherFields(teacherDTO, teacher);
        return teacherMapper.toDTO(updatedTeacher);
    }

    private Teacher updateTeacherFields(TeacherDTO teacherDTO, Teacher teacher) {
        teacher.setName(teacherDTO.name());
        return repository.save(teacher);
    }
}
