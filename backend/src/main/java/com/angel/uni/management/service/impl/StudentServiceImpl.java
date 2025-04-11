package com.angel.uni.management.service.impl;

import com.angel.uni.management.dto.StudentDTO;
import com.angel.uni.management.entity.Student;
import com.angel.uni.management.exceptions.ResourceNotFoundException;
import com.angel.uni.management.mapper.grade.GradeEntityMapper;
import com.angel.uni.management.mapper.student.StudentDTOMapper;
import com.angel.uni.management.mapper.student.StudentEntityMapper;
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

    private final StudentDTOMapper studentDTOMapper;
    private final StudentEntityMapper studentEntityMapper;
    private final GradeEntityMapper gradeEntityMapper;
    private final StudentRepository studentRepository;

    @Autowired
    public StudentServiceImpl(StudentDTOMapper studentDTOMapper, StudentEntityMapper studentEntityMapper, GradeEntityMapper gradeEntityMapper, StudentRepository studentRepository) {
        this.studentDTOMapper = studentDTOMapper;
        this.studentEntityMapper = studentEntityMapper;
        this.gradeEntityMapper = gradeEntityMapper;
        this.studentRepository = studentRepository;
    }

    @Override
    public StudentDTO createStudent(StudentDTO studentDTO) throws BadRequestException {
        if (studentDTO == null) {
            throw new BadRequestException("Cannot create student: Provided StudentDTO is null");
        }
        Student student = studentEntityMapper.apply(studentDTO);
        Student savedStudent = studentRepository.save(student);
        return studentDTOMapper.apply(savedStudent);
    }

    @Override
    public StudentDTO getStudentById(Long id) {
        Student student = studentRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Student not found with this id: " + id));
        return studentDTOMapper.apply(student);
    }

    @Override
    public List<StudentDTO> getAllStudents() {
        List<Student> students = studentRepository.findAll();
        if (students.isEmpty()) {
            throw new ResourceNotFoundException("Students' list cannot be empty.");
        }
        return students.stream().map(studentDTOMapper).collect(Collectors.toList());
    }

    @Override
    public StudentDTO updateStudent(Long id, StudentDTO updateStudentDTO) throws BadRequestException {
        if (updateStudentDTO == null) {
            throw new BadRequestException("Cannot update student: Provided StudentDTO is null");
        }

        Student student = studentRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Student not found with this id: " + id));
        Student newStudent = updateStudentFields(student, updateStudentDTO);
        return studentDTOMapper.apply(newStudent);
    }

    private Student updateStudentFields(Student studentEntity, StudentDTO updateStudentDTO) {

        if (updateStudentDTO.grades() == null) {
            studentEntity.setGrades(new ArrayList<>());
        } else {
            studentEntity.setGrades(updateStudentDTO.grades().stream()
                    .map(gradeEntityMapper)
                    .collect(Collectors.toList()));
        }

        studentEntity.setUsername(updateStudentDTO.username());
        studentEntity.setAverageGradeOverall(updateStudentDTO.averageGradeOverall());
        return studentRepository.save(studentEntity);
    }
}
