package com.angel.uni.management.mapper.group;

import com.angel.uni.management.dto.group.GroupRequestDTO;
import com.angel.uni.management.entity.Student;
import com.angel.uni.management.entity.UniversityGroup;
import org.springframework.stereotype.Component;

import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class UniversityGroupRequestDTOMapper implements Function<UniversityGroup, GroupRequestDTO> {

    @Override
    public GroupRequestDTO apply(UniversityGroup universityGroup) {
        return GroupRequestDTO.builder()
                .groupName(universityGroup.getGroupName())
                .studentIds(universityGroup.getStudentsAssignedToGroup().stream()
                        .map(Student::getId).collect(Collectors.toList()))
                .build();
    }
}
