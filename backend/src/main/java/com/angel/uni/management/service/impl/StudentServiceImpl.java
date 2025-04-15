package com.angel.uni.management.service.impl;

import com.angel.uni.management.dto.student.StudentRequestDTO;
import com.angel.uni.management.dto.student.StudentResponseDTO;
import com.angel.uni.management.entity.Student;
import com.angel.uni.management.exceptions.ResourceNotFoundException;
import com.angel.uni.management.mapper.grade.GradeEntityMapper;
import com.angel.uni.management.mapper.student.StudentMapper;
import com.angel.uni.management.repositories.StudentRepository;
import com.angel.uni.management.service.StudentService;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentServiceImpl implements StudentService {

    private final GradeEntityMapper gradeEntityMapper;
    private final StudentRepository studentRepository;
    private final StudentMapper studentMapper;

    @Autowired
    public StudentServiceImpl(GradeEntityMapper gradeEntityMapper, StudentRepository studentRepository, StudentMapper studentMapper) {
        this.gradeEntityMapper = gradeEntityMapper;
        this.studentRepository = studentRepository;
        this.studentMapper = studentMapper;
    }

    @Override
    public StudentRequestDTO createStudent(StudentRequestDTO studentRequestDTO) throws BadRequestException {
        if (studentRequestDTO == null) {
            throw new BadRequestException("Cannot create student: Provided StudentRequestDTO is null");
        }
        Student student = studentMapper.requestToEntity(studentRequestDTO);
        Student savedStudent = studentRepository.save(student);
        return studentMapper.toRequestDTO(savedStudent);
    }

    @Override
    public StudentResponseDTO getStudentById(Long id) {
        Student student = studentRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Student not found with this id: " + id));
        return studentMapper.toResponseDTO(student);
    }

    @Override
    public List<StudentResponseDTO> getAllStudents() {
        List<Student> students = studentRepository.findAll();
        if (students.isEmpty()) {
            throw new ResourceNotFoundException("Students' list cannot be empty.");
        }
        return students.stream().map(studentMapper::toResponseDTO).collect(Collectors.toList());
    }

    @Override
    public StudentResponseDTO updateStudent(Long id, StudentResponseDTO updateStudentResponseDTO) throws BadRequestException {
        if (updateStudentResponseDTO == null) {
            throw new BadRequestException("Cannot update student: Provided StudentResponseDTO is null");
        }

        Student student = studentRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Student not found with this id: " + id));
        Student newStudent = updateStudentFields(student, updateStudentResponseDTO);
        return studentMapper.toResponseDTO(newStudent);
    }

    private Student updateStudentFields(Student studentEntity, StudentResponseDTO updateStudentResponseDTO) {

        if (updateStudentResponseDTO.grades() == null) {
            studentEntity.setGrades(new ArrayList<>());
        } else {
            studentEntity.setGrades(updateStudentResponseDTO.grades().stream()
                    .map(gradeEntityMapper)
                    .collect(Collectors.toList()));
        }

        studentEntity.setUsername(updateStudentResponseDTO.username());
        studentEntity.setAverageGradeOverall(updateStudentResponseDTO.averageGradeOverall());
        return studentRepository.save(studentEntity);
    }
}
