package com.angel.uni.management.service;

import com.angel.uni.management.dto.group.GroupRequestDTO;
import com.angel.uni.management.dto.group.GroupResponseDTO;
import org.apache.coyote.BadRequestException;

import java.util.List;

public interface UniversityGroupService {
    GroupRequestDTO createUniversityGroup(GroupRequestDTO groupRequestDTO) throws BadRequestException;
    GroupResponseDTO getUniversityGroupById(Long id);
    List<GroupResponseDTO> getAllUniversityGroups();
    GroupResponseDTO updateUniversityGroup(Long id, GroupResponseDTO groupResponseDTO) throws BadRequestException;
}
