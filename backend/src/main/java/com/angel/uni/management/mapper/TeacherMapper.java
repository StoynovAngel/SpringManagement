package com.angel.uni.management.mapper;

import com.angel.uni.management.dto.TeacherDTO;
import com.angel.uni.management.entity.Teacher;
import com.angel.uni.management.interfaces.BaseMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TeacherMapper extends BaseMapper<TeacherDTO, Teacher> {
}
