package com.angel.uni.management.service;

import com.angel.uni.management.dto.GroupDTO;
import com.angel.uni.management.entity.UniversityGroup;
import com.angel.uni.management.exceptions.ResourceNotFoundException;
import com.angel.uni.management.mapper.group.UniversityGroupDTOMapper;
import com.angel.uni.management.mapper.group.UniversityGroupEntityMapper;
import com.angel.uni.management.repositories.UniversityGroupRepository;
import com.angel.uni.management.service.impl.UniversityGroupServiceImpl;
import org.apache.coyote.BadRequestException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class UniversityGroupServiceTest {

    @Mock
    private UniversityGroupRepository universityGroupRepository;

    @InjectMocks
    private UniversityGroupServiceImpl universityGroupService;

    @Mock
    private UniversityGroupDTOMapper universityGroupDTOMapper;

    @Mock
    private UniversityGroupEntityMapper universityGroupEntityMapper;

    private UniversityGroup universityGroup;
    private GroupDTO groupDTO;

    @BeforeEach
    void setUp() {
        universityGroup = UniversityGroup.builder()
                .id(1L)
                .groupName("group")
                .studentsAssignedToGroup(new ArrayList<>())
                .build();

        groupDTO = GroupDTO.builder()
                .id(1L)
                .groupName("group")
                .studentsAssignedToGroup(new ArrayList<>())
                .build();
    }

    @Test
    void testCreateUniversityGroup() throws BadRequestException {
        Mockito.when(universityGroupEntityMapper.apply(groupDTO)).thenReturn(universityGroup);
        Mockito.when(universityGroupRepository.save(universityGroup)).thenReturn(universityGroup);
        Mockito.when(universityGroupDTOMapper.apply(universityGroup)).thenReturn(groupDTO);

        GroupDTO result = universityGroupService.createUniversityGroup(groupDTO);
        assertNotNull(result);
        Assertions.assertThat(result.id()).isGreaterThan(0);
    }

    @Test
    void testCreateUniversityGroup_BadRequest() {
        assertThrows(BadRequestException.class, () -> {
            universityGroupService.createUniversityGroup(null);
        });
    }

    @Test
    void testGetUniversityGroupById() {
        Mockito.when(universityGroupRepository.findById(1L)).thenReturn(Optional.of(universityGroup));
        Mockito.when(universityGroupDTOMapper.apply(universityGroup)).thenReturn(groupDTO);
        GroupDTO result = universityGroupService.getUniversityGroupById(1L);
        assertDoesNotThrow(() -> universityGroupRepository.findById(1L));
        assertNotNull(result);
        assertEquals("group", result.groupName());
    }

    @Test
    void testGetAllUniversityGroups() {
        List<UniversityGroup> listOfGroup = new ArrayList<>();
        listOfGroup.add(universityGroup);
        Mockito.when(universityGroupRepository.findAll()).thenReturn(listOfGroup);
        Mockito.when(universityGroupDTOMapper.apply(universityGroup)).thenReturn(groupDTO);
        List<GroupDTO> results = universityGroupService.getAllUniversityGroups();
        assertNotNull(results);
        assertDoesNotThrow(() -> results);
        Assertions.assertThat(results.size()).isGreaterThan(0);
    }

    @Test
    void testGetAllUniversityGroups_ResourceNotFound() {
        assertThrows(ResourceNotFoundException.class, () -> {
            universityGroupService.getAllUniversityGroups();
        });
    }

    @Test
    void testUpdateUniversityGroup() throws BadRequestException {
        GroupDTO updatedGroupDTO = GroupDTO.builder()
                .groupName("updated_group")
                .studentsAssignedToGroup(new ArrayList<>())
                .build();

        Mockito.when(universityGroupRepository.findById(1L)).thenReturn(Optional.of(universityGroup));
        Mockito.when(universityGroupRepository.save(universityGroup)).thenReturn(universityGroup);
        Mockito.when(universityGroupDTOMapper.apply(universityGroup)).thenReturn(updatedGroupDTO);

        GroupDTO result = universityGroupService.updateUniversityGroup(1L, updatedGroupDTO);
        assertNotNull(result);
        assertEquals("updated_group", result.groupName());
    }

    @Test
    void testUpdateUniversityGroup_BadRequest() {
        assertThrows(BadRequestException.class, () -> {
            universityGroupService.updateUniversityGroup(1L, null);
        });
    }
}