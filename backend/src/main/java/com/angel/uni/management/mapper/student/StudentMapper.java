package com.angel.uni.management.mapper.student;

import com.angel.uni.management.dto.student.StudentRequestDTO;
import com.angel.uni.management.dto.student.StudentResponseDTO;
import com.angel.uni.management.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class StudentMapper {

    private final StudentResponseDTOMapper studentResponseDTOMapper;
    private final StudentResponseEntityMapper studentResponseEntityMapper;
    private final StudentRequestDTOMapper studentRequestDTOMapper;
    private final StudentRequestEntityMapper studentRequestEntityMapper;

    @Autowired
    public StudentMapper(StudentResponseDTOMapper studentResponseDTOMapper, StudentResponseEntityMapper studentResponseEntityMapper, StudentRequestDTOMapper studentRequestDTOMapper, StudentRequestEntityMapper studentRequestEntityMapper) {
        this.studentResponseDTOMapper = studentResponseDTOMapper;
        this.studentResponseEntityMapper = studentResponseEntityMapper;
        this.studentRequestDTOMapper = studentRequestDTOMapper;
        this.studentRequestEntityMapper = studentRequestEntityMapper;
    }

    public final StudentResponseDTO toResponseDTO(Student student) {
        return studentResponseDTOMapper.apply(student);
    }

    public final StudentRequestDTO toRequestDTO(Student student) {
        return studentRequestDTOMapper.apply(student);
    }

    public final Student responseToEntity(StudentResponseDTO studentResponseDTO) {
        return studentResponseEntityMapper.apply(studentResponseDTO);
    }

    public final Student requestToEntity(StudentRequestDTO studentRequestDTO) {
        return studentRequestEntityMapper.apply(studentRequestDTO);
    }
}
