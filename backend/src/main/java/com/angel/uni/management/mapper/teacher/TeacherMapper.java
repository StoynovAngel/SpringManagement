package com.angel.uni.management.mapper.teacher;

import com.angel.uni.management.dto.TeacherDTO;
import com.angel.uni.management.entity.Teacher;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TeacherMapper {

    TeacherDTO toDTO(Teacher teacher);
    Teacher toEntity(TeacherDTO teacherDTO);
    Teacher toEntityById(Long id);
}
