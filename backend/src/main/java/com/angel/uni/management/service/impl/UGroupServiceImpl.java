package com.angel.uni.management.service.impl;

import com.angel.uni.management.dto.GroupDTO;
import com.angel.uni.management.entity.UniversityGroup;
import com.angel.uni.management.exceptions.ResourceNotFoundException;
import com.angel.uni.management.mapper.group.GroupMapper;
import com.angel.uni.management.repositories.UGroupRepository;
import com.angel.uni.management.service.UGroupService;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UGroupServiceImpl implements UGroupService {

    private final UGroupRepository universityGroupRepository;
    private final GroupMapper groupMapper;

    @Autowired
    public UGroupServiceImpl(UGroupRepository universityGroupRepository, GroupMapper groupMapper) {
        this.universityGroupRepository = universityGroupRepository;
        this.groupMapper = groupMapper;
    }

    @Override
    public GroupDTO createUniversityGroup(GroupDTO groupDTO) throws BadRequestException {
        if (groupDTO == null) {
            throw new BadRequestException("Cannot create group: GroupDTO is null.");
        }

        UniversityGroup group = groupMapper.toEntity(groupDTO);
        UniversityGroup savedGroup = universityGroupRepository.save(group);
        return groupMapper.toDTO(savedGroup);
    }

    @Override
    public GroupDTO getUniversityGroupById(Long id) {
        UniversityGroup getGroup = universityGroupRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("University group not found with this id: " + id));
        return groupMapper.toDTO(getGroup);
    }

    @Override
    public List<GroupDTO> getAllUniversityGroups() {
        List<UniversityGroup> results = universityGroupRepository.findAll();

        if (results.isEmpty()) {
            throw new ResourceNotFoundException("No universities groups not found.");
        }

        return results.stream().map(groupMapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public GroupDTO updateUniversityGroup(Long id, GroupDTO groupDTO) throws BadRequestException {
        if (groupDTO == null) {
            throw new BadRequestException("Cannot update group: GroupDTO is null.");
        }
        UniversityGroup groupToBeEdited = universityGroupRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("University group not found with this id: " + id));
        groupMapper.updateEntityFromDto(groupDTO, groupToBeEdited);
        UniversityGroup updated = universityGroupRepository.save(groupToBeEdited);
        return groupMapper.toDTO(updated);
    }
}
