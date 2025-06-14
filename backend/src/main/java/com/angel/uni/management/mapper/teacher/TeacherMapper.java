package com.angel.uni.management.mapper.teacher;

import com.angel.uni.management.dto.TeacherDTO;
import com.angel.uni.management.entity.Teacher;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface TeacherMapper {

    TeacherDTO toDTO(Teacher teacher);
    Teacher toEntity(TeacherDTO teacherDTO);
    Teacher toEntityById(Long id);
    void updateEntityFromDto(TeacherDTO teacherDTO, @MappingTarget Teacher teacher);
}
