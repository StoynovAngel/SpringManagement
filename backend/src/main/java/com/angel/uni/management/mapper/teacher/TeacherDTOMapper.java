package com.angel.uni.management.mapper.teacher;

import com.angel.uni.management.dto.TeacherDTO;
import com.angel.uni.management.entity.Teacher;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class TeacherDTOMapper implements Function<Teacher, TeacherDTO> {

    @Override
    public TeacherDTO apply(Teacher teacher) {
        return TeacherDTO.builder()
                .id(teacher.getId())
                .name(teacher.getName())
                .build();
    }
}
