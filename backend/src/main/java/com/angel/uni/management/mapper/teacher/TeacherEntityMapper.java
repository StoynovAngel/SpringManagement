package com.angel.uni.management.mapper.teacher;

import com.angel.uni.management.dto.TeacherDTO;
import com.angel.uni.management.entity.Teacher;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class TeacherEntityMapper implements Function<TeacherDTO, Teacher> {

    @Override
    public Teacher apply(TeacherDTO teacherDTO) {
        return Teacher.builder()
                .id(teacherDTO.id())
                .name(teacherDTO.name())
                .build();
    }
}
