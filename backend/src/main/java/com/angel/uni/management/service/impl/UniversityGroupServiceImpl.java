package com.angel.uni.management.service.impl;

import com.angel.uni.management.dto.GroupDTO;
import com.angel.uni.management.entity.UniversityGroup;
import com.angel.uni.management.exceptions.ResourceNotFoundException;
import com.angel.uni.management.mapper.group.UniversityGroupDTOMapper;
import com.angel.uni.management.mapper.group.UniversityGroupEntityMapper;
import com.angel.uni.management.repositories.UniversityGroupRepository;
import com.angel.uni.management.service.UniversityGroupService;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UniversityGroupServiceImpl implements UniversityGroupService {

    private final UniversityGroupRepository universityGroupRepository;
    private final UniversityGroupDTOMapper universityGroupDTOMapper;
    private final UniversityGroupEntityMapper universityGroupEntityMapper;

    @Autowired
    public UniversityGroupServiceImpl(UniversityGroupRepository universityGroupRepository, UniversityGroupDTOMapper universityGroupDTOMapper, UniversityGroupEntityMapper universityGroupEntityMapper) {
        this.universityGroupRepository = universityGroupRepository;
        this.universityGroupDTOMapper = universityGroupDTOMapper;
        this.universityGroupEntityMapper = universityGroupEntityMapper;
    }

    @Override
    public GroupDTO createUniversityGroup(GroupDTO groupDTO) throws BadRequestException {
        if (groupDTO == null) {
            throw new BadRequestException("Cannot create group: GroupDTO is null.");
        }

        UniversityGroup group = universityGroupEntityMapper.apply(groupDTO);
        UniversityGroup savedGroup = universityGroupRepository.save(group);
        return universityGroupDTOMapper.apply(savedGroup);
    }

    @Override
    public GroupDTO getUniversityGroupById(Long id) {
        UniversityGroup getGroup = universityGroupRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("University group not found with this id: " + id));
        return universityGroupDTOMapper.apply(getGroup);
    }

    @Override
    public List<GroupDTO> getAllUniversityGroups() {
        List<UniversityGroup> results = universityGroupRepository.findAll();

        if (results.isEmpty()) {
            throw new ResourceNotFoundException("No universities groups not found.");
        }

        return results.stream().map(universityGroupDTOMapper).collect(Collectors.toList());
    }

    @Override
    public GroupDTO updateUniversityGroup(Long id, GroupDTO groupDTO) throws BadRequestException {

        if (groupDTO == null) {
            throw new BadRequestException("Cannot update group: GroupDTO is null.");
        }

        UniversityGroup groupToBeEdited = universityGroupRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("University group not found with this id: " + id));
        groupToBeEdited.setGroupName(groupDTO.groupName());
        groupToBeEdited.setStudentsAssignedToGroup(groupToBeEdited.getStudentsAssignedToGroup());
        universityGroupRepository.save(groupToBeEdited);

        return universityGroupDTOMapper.apply(groupToBeEdited);
    }
}
