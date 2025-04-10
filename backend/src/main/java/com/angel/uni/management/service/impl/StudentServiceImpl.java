package com.angel.uni.management.service.impl;

import com.angel.uni.management.dto.StudentDTO;
import com.angel.uni.management.entity.Student;
import com.angel.uni.management.exceptions.ResourceNotFoundException;
import com.angel.uni.management.mapper.GradeMapper;
import com.angel.uni.management.mapper.StudentMapper;
import com.angel.uni.management.repositories.StudentRepository;
import com.angel.uni.management.service.StudentService;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentMapper studentMapper;
    private final GradeMapper gradeMapper;
    private final StudentRepository studentRepository;

    @Autowired
    public StudentServiceImpl(StudentMapper studentMapper, GradeMapper gradeMapper, StudentRepository studentRepository) {
        this.studentMapper = studentMapper;
        this.gradeMapper = gradeMapper;
        this.studentRepository = studentRepository;
    }

    @Override
    public StudentDTO createStudent(StudentDTO studentDTO) throws BadRequestException {
        if (studentDTO == null) {
            throw new BadRequestException("StudentDTO must not be null");
        }
        Student student = studentMapper.mapToEntity(studentDTO);
        Student savedStudent = studentRepository.save(student);
        return studentMapper.mapToDTO(savedStudent);
    }

    @Override
    public StudentDTO getStudentById(Long id) {
        Student student = studentRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Student not found with this id: " + id));
        return studentMapper.mapToDTO(student);
    }

    @Override
    public List<StudentDTO> getAllStudents() {
        List<Student> students = studentRepository.findAll();
        if (students.isEmpty()) {
            throw new ResourceNotFoundException("Students' list cannot be empty.");
        }
        return students.stream().map(studentMapper::mapToDTO).collect(Collectors.toList());
    }

    @Override
    public StudentDTO updateStudent(Long id, StudentDTO updateStudentDTO) throws BadRequestException {
        if (updateStudentDTO == null) {
            throw new BadRequestException("StudentDTO must not be null");
        }

        Student student = studentRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Student not found with this id: " + id));
        Student newStudent = updateStudentFields(student, updateStudentDTO);
        return studentMapper.mapToDTO(newStudent);
    }

    private Student updateStudentFields(Student studentEntity, StudentDTO updateStudentDTO) {
        studentEntity.setGrades(updateStudentDTO.grades().stream()
                .map(gradeMapper::mapToEntity)
                .collect(Collectors.toList())
        );
        studentEntity.setUsername(updateStudentDTO.username());
        studentEntity.setAverageGradeOverall(updateStudentDTO.averageGradeOverall());
        return studentRepository.save(studentEntity);
    }
}
