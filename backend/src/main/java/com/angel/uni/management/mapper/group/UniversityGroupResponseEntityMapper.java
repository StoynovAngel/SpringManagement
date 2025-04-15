package com.angel.uni.management.mapper.group;

import com.angel.uni.management.dto.group.GroupResponseDTO;
import com.angel.uni.management.entity.UniversityGroup;
import com.angel.uni.management.mapper.student.StudentResponseEntityMapper;
import org.springframework.stereotype.Service;

import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class UniversityGroupResponseEntityMapper implements Function<GroupResponseDTO, UniversityGroup> {

    private final StudentResponseEntityMapper studentResponseEntityMapper;

    public UniversityGroupResponseEntityMapper(StudentResponseEntityMapper studentResponseEntityMapper) {
        this.studentResponseEntityMapper = studentResponseEntityMapper;
    }

    @Override
    public UniversityGroup apply(GroupResponseDTO groupResponseDTO) {
        return UniversityGroup.builder()
                .id(groupResponseDTO.id())
                .groupName(groupResponseDTO.groupName())
                .studentsAssignedToGroup(groupResponseDTO.studentsAssignedToGroup().stream()
                        .map(studentResponseEntityMapper)
                        .collect(Collectors.toList()))
                .build();
    }
}
