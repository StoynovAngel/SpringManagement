package com.angel.uni.management.mapper.student;

import com.angel.uni.management.dto.StudentDTO;
import com.angel.uni.management.entity.Student;
import com.angel.uni.management.mapper.grade.GradeMapper;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring", uses = GradeMapper.class)
public interface StudentMapper {

    StudentDTO toDTO(Student student);
    Student toEntity(StudentDTO studentDTO);
    void updateEntityFromDto(StudentDTO studentDTO, @MappingTarget Student student);
}
