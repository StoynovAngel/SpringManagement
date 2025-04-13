package com.angel.uni.management.service;

import com.angel.uni.management.dto.GroupDTO;
import org.apache.coyote.BadRequestException;

import java.util.List;

public interface UniversityGroupService {
    GroupDTO createUniversityGroup(GroupDTO groupDTO) throws BadRequestException;
    GroupDTO getUniversityGroupById(Long id);
    List<GroupDTO> getAllUniversityGroups();
    GroupDTO updateUniversityGroup(Long id, GroupDTO groupDTO) throws BadRequestException;
}
