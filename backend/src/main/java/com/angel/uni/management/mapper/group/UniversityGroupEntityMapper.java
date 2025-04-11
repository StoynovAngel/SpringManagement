package com.angel.uni.management.mapper.group;

import com.angel.uni.management.dto.GroupDTO;
import com.angel.uni.management.entity.UniversityGroup;
import com.angel.uni.management.mapper.student.StudentEntityMapper;
import org.springframework.stereotype.Service;

import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class UniversityGroupEntityMapper implements Function<GroupDTO, UniversityGroup> {

    private final StudentEntityMapper studentEntityMapper;

    public UniversityGroupEntityMapper(StudentEntityMapper studentEntityMapper) {
        this.studentEntityMapper = studentEntityMapper;
    }

    @Override
    public UniversityGroup apply(GroupDTO groupDTO) {
        return UniversityGroup.builder()
                .id(groupDTO.id())
                .groupName(groupDTO.groupName())
                .studentsAssignedToGroup(groupDTO.studentsAssignedToGroup().stream()
                        .map(studentEntityMapper)
                        .collect(Collectors.toList()))
                .build();
    }
}
