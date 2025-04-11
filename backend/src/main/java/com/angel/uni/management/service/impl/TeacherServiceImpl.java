package com.angel.uni.management.service.impl;

import com.angel.uni.management.dto.TeacherDTO;
import com.angel.uni.management.entity.Teacher;
import com.angel.uni.management.exceptions.ResourceNotFoundException;
import com.angel.uni.management.mapper.teacher.TeacherDTOMapper;
import com.angel.uni.management.mapper.teacher.TeacherEntityMapper;
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
    private final TeacherEntityMapper teacherEntityMapper;
    private final TeacherDTOMapper teacherDTOMapper;

    @Autowired
    public TeacherServiceImpl(TeacherRepository repository, TeacherEntityMapper teacherEntityMapper, TeacherDTOMapper teacherDTOMapper) {
        this.repository = repository;
        this.teacherEntityMapper = teacherEntityMapper;
        this.teacherDTOMapper = teacherDTOMapper;
    }

    @Override
    public TeacherDTO createTeacher(TeacherDTO teacherDTO) throws BadRequestException {
        if (teacherDTO == null) {
            throw new BadRequestException("Cannot create teacher: Provided TeacherDTO is null");
        }

        Teacher teacher = teacherEntityMapper.apply(teacherDTO);
        Teacher saved = repository.save(teacher);
        return teacherDTOMapper.apply(saved);
    }

    @Override
    public TeacherDTO getTeacherById(Long id) {
        Teacher teacher = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Teacher not found with this id:" + id));
        return teacherDTOMapper.apply(teacher);
    }

    @Override
    public List<TeacherDTO> getAllTeachers() {
        List<Teacher> teachers = repository.findAll();

        if (teachers.isEmpty()) {
            throw new ResourceNotFoundException("Could not get all teachers. Teachers' list is empty.");
        }

        return teachers.stream().map(teacherDTOMapper).collect(Collectors.toList());
    }

    @Override
    public TeacherDTO updateTeacher(Long id, TeacherDTO teacherDTO) throws BadRequestException {

        if (teacherDTO == null) {
            throw new ResourceNotFoundException("Cannot update teacher: teacherDTO is null");
        }

        Teacher teacher = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Teacher not found with this id: " + id));
        Teacher updatedTeacher = updateTeacherFields(teacherDTO, teacher);
        return teacherDTOMapper.apply(updatedTeacher);
    }

    private Teacher updateTeacherFields(TeacherDTO teacherDTO, Teacher teacher) {
        teacher.setName(teacherDTO.name());
        return repository.save(teacher);
    }
}
