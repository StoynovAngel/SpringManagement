package com.angel.uni.management.mapper.group;

import com.angel.uni.management.dto.group.GroupRequestDTO;
import com.angel.uni.management.entity.UniversityGroup;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class UniversityGroupRequestEntityMapper implements Function<GroupRequestDTO, UniversityGroup> {

    @Override
    public UniversityGroup apply(GroupRequestDTO groupRequestDTO) {
        return UniversityGroup.builder()
                .groupName(groupRequestDTO.groupName())
                .studentsAssignedToGroup(null)
                .build();
    }
}
