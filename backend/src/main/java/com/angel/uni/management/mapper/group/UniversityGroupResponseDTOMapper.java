package com.angel.uni.management.mapper.group;

import com.angel.uni.management.dto.group.GroupResponseDTO;
import com.angel.uni.management.entity.UniversityGroup;
import com.angel.uni.management.mapper.student.StudentResponseDTOMapper;
import org.springframework.stereotype.Service;

import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class UniversityGroupResponseDTOMapper implements Function<UniversityGroup, GroupResponseDTO> {

    private final StudentResponseDTOMapper studentResponseDTOMapper;

    public UniversityGroupResponseDTOMapper(StudentResponseDTOMapper studentResponseDTOMapper) {
        this.studentResponseDTOMapper = studentResponseDTOMapper;
    }

    @Override
    public GroupResponseDTO apply(UniversityGroup universityGroup) {
        return GroupResponseDTO.builder()
                .id(universityGroup.getId())
                .groupName(universityGroup.getGroupName())
                .studentsAssignedToGroup(universityGroup.getStudentsAssignedToGroup().stream()
                        .map(studentResponseDTOMapper)
                        .collect(Collectors.toList()))
                .build();
    }
}
