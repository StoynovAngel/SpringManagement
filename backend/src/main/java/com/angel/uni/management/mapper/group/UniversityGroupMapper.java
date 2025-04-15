package com.angel.uni.management.mapper.group;

import com.angel.uni.management.dto.group.GroupRequestDTO;
import com.angel.uni.management.dto.group.GroupResponseDTO;
import com.angel.uni.management.entity.UniversityGroup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UniversityGroupMapper {

    private final UniversityGroupResponseDTOMapper universityGroupResponseDTOMapper;
    private final UniversityGroupResponseEntityMapper universityGroupResponseEntityMapper;
    private final UniversityGroupRequestDTOMapper universityGroupRequestDTOMapper;
    private final UniversityGroupRequestEntityMapper universityGroupRequestEntityMapper;

    @Autowired
    public UniversityGroupMapper(UniversityGroupResponseDTOMapper universityGroupResponseDTOMapper, UniversityGroupResponseEntityMapper universityGroupResponseEntityMapper, UniversityGroupRequestDTOMapper universityGroupRequestDTOMapper, UniversityGroupRequestEntityMapper universityGroupRequestEntityMapper) {
        this.universityGroupResponseDTOMapper = universityGroupResponseDTOMapper;
        this.universityGroupResponseEntityMapper = universityGroupResponseEntityMapper;
        this.universityGroupRequestDTOMapper = universityGroupRequestDTOMapper;
        this.universityGroupRequestEntityMapper = universityGroupRequestEntityMapper;
    }

    public final GroupRequestDTO toRequestDTO(UniversityGroup universityGroup) {
        return universityGroupRequestDTOMapper.apply(universityGroup);
    }

    public final GroupResponseDTO toResponseDTO(UniversityGroup universityGroup) {
        return universityGroupResponseDTOMapper.apply(universityGroup);
    }

    public final UniversityGroup requestToEntity(GroupRequestDTO groupRequestDTO) {
        return universityGroupRequestEntityMapper.apply(groupRequestDTO);
    }

    public final UniversityGroup responseToEntity(GroupResponseDTO groupResponseDTO) {
        return universityGroupResponseEntityMapper.apply(groupResponseDTO);
    }
}
