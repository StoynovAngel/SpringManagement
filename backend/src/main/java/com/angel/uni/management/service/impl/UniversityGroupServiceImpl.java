package com.angel.uni.management.service.impl;

import com.angel.uni.management.dto.group.GroupRequestDTO;
import com.angel.uni.management.dto.group.GroupResponseDTO;
import com.angel.uni.management.entity.UniversityGroup;
import com.angel.uni.management.exceptions.ResourceNotFoundException;
import com.angel.uni.management.mapper.group.UniversityGroupMapper;
import com.angel.uni.management.mapper.student.StudentMapper;
import com.angel.uni.management.repositories.UniversityGroupRepository;
import com.angel.uni.management.service.UniversityGroupService;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UniversityGroupServiceImpl implements UniversityGroupService {

    private final UniversityGroupRepository universityGroupRepository;
    private final UniversityGroupMapper groupManager;
    private final StudentMapper studentMapper;

    @Autowired
    public UniversityGroupServiceImpl(UniversityGroupRepository universityGroupRepository,
                                      UniversityGroupMapper groupManager,
                                      StudentMapper studentMapper) {
        this.universityGroupRepository = universityGroupRepository;
        this.groupManager = groupManager;
        this.studentMapper = studentMapper;
    }

    @Override
    public GroupRequestDTO createUniversityGroup(GroupRequestDTO requestDTO) throws BadRequestException {
        if (requestDTO == null) {
            throw new BadRequestException("Cannot create group: GroupResponseDTO is null.");
        }

        UniversityGroup group = groupManager.requestToEntity(requestDTO);
        UniversityGroup savedGroup = universityGroupRepository.save(group);
        return groupManager.toRequestDTO(savedGroup);
    }

    @Override
    public GroupResponseDTO getUniversityGroupById(Long id) {
        UniversityGroup getGroup = universityGroupRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("University group not found with this id: " + id));
        return groupManager.toResponseDTO(getGroup);
    }

    @Override
    public List<GroupResponseDTO> getAllUniversityGroups() {
        List<UniversityGroup> results = universityGroupRepository.findAll();

        if (results.isEmpty()) {
            throw new ResourceNotFoundException("No universities groups not found.");
        }

        return results.stream().map(groupManager::toResponseDTO).collect(Collectors.toList());
    }

    @Override
    public GroupResponseDTO updateUniversityGroup(Long id, GroupResponseDTO groupResponseDTO) throws BadRequestException {

        if (groupResponseDTO == null) {
            throw new BadRequestException("Cannot update group: GroupResponseDTO is null.");
        }

        UniversityGroup groupToBeEdited = universityGroupRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("University group not found with this id: " + id));

        groupToBeEdited.setGroupName(groupResponseDTO.groupName());
        groupToBeEdited.setStudentsAssignedToGroup(groupResponseDTO.studentsAssignedToGroup().stream().map(studentMapper::responseToEntity).collect(Collectors.toList()));
        universityGroupRepository.save(groupToBeEdited);

        return groupManager.toResponseDTO(groupToBeEdited);
    }
}
