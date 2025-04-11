package com.angel.uni.management.mapper.group;

import com.angel.uni.management.dto.GroupDTO;
import com.angel.uni.management.entity.UniversityGroup;
import com.angel.uni.management.mapper.student.StudentDTOMapper;
import org.springframework.stereotype.Service;

import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class UniversityGroupDTOMapper implements Function<UniversityGroup, GroupDTO> {

    private final StudentDTOMapper studentDTOMapper;

    public UniversityGroupDTOMapper(StudentDTOMapper studentDTOMapper) {
        this.studentDTOMapper = studentDTOMapper;
    }

    @Override
    public GroupDTO apply(UniversityGroup universityGroup) {
        return GroupDTO.builder()
                .id(universityGroup.getId())
                .groupName(universityGroup.getGroupName())
                .studentsAssignedToGroup(universityGroup.getStudentsAssignedToGroup().stream()
                        .map(studentDTOMapper)
                        .collect(Collectors.toList()))
                .build();
    }
}
