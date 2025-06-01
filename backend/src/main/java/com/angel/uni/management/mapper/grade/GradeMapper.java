package com.angel.uni.management.mapper.grade;

import com.angel.uni.management.dto.GradeDTO;
import com.angel.uni.management.entity.Grade;
import com.angel.uni.management.mapper.student.StudentMapper;
import com.angel.uni.management.mapper.teacher.TeacherMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring", uses = {TeacherMapper.class, StudentMapper.class})
public interface GradeMapper {

    @Mapping(target = "teacherId", source = "teacher.id")
    GradeDTO toDTO(Grade grade);

    @Mapping(target = "teacher", source = "teacherId")
    Grade toEntity(GradeDTO gradeDTO);

    void updateEntityFromDto(GradeDTO gradeDTO, @MappingTarget Grade grade);
}