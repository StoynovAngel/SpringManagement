package com.angel.uni.management.service;

import com.angel.uni.management.dto.group.GroupRequestDTO;
import com.angel.uni.management.dto.group.GroupResponseDTO;
import com.angel.uni.management.entity.UniversityGroup;
import com.angel.uni.management.exceptions.ResourceNotFoundException;
import com.angel.uni.management.mapper.group.UniversityGroupMapper;
import com.angel.uni.management.mapper.group.UniversityGroupResponseDTOMapper;
import com.angel.uni.management.mapper.group.UniversityGroupResponseEntityMapper;
import com.angel.uni.management.mapper.student.StudentMapper;
import com.angel.uni.management.mapper.student.StudentResponseEntityMapper;
import com.angel.uni.management.repositories.UniversityGroupRepository;
import com.angel.uni.management.service.impl.UniversityGroupServiceImpl;
import org.apache.coyote.BadRequestException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Answers;
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

    @Mock(answer = Answers.RETURNS_DEEP_STUBS)
    private UniversityGroupMapper universityGroupMapper;

    @Mock
    private StudentMapper studentMapper;

    @Mock
    private StudentResponseEntityMapper studentResponseEntityMapper;

    private UniversityGroup universityGroup;
    private GroupResponseDTO groupResponseDTO;
    private GroupRequestDTO groupRequestDTO;

    @BeforeEach
    void setUp() {
        universityGroup = UniversityGroup.builder()
                .id(1L)
                .groupName("group")
                .studentsAssignedToGroup(new ArrayList<>())
                .build();

        groupResponseDTO = GroupResponseDTO.builder()
                .id(1L)
                .groupName("group")
                .studentsAssignedToGroup(new ArrayList<>())
                .build();

        groupRequestDTO = GroupRequestDTO.builder()
                .groupName("group")
                .studentIds(new ArrayList<>())
                .build();
    }

    @Test
    void testCreateUniversityGroup() throws BadRequestException {
        Mockito.when(universityGroupMapper.requestToEntity(groupRequestDTO)).thenReturn(universityGroup);
        Mockito.when(universityGroupRepository.save(universityGroup)).thenReturn(universityGroup);
        Mockito.when(universityGroupMapper.toResponseDTO(universityGroup)).thenReturn(groupResponseDTO);

        GroupRequestDTO result = universityGroupService.createUniversityGroup(groupRequestDTO);
        assertNotNull(result);
        assertEquals("group", groupRequestDTO.groupName());
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
        Mockito.when(universityGroupMapper.toResponseDTO(universityGroup)).thenReturn(groupResponseDTO);
        GroupResponseDTO result = universityGroupService.getUniversityGroupById(1L);
        assertDoesNotThrow(() -> universityGroupRepository.findById(1L));
        assertNotNull(result);
        assertEquals("group", result.groupName());
    }

    @Test
    void testGetAllUniversityGroups() {
        List<UniversityGroup> listOfGroup = new ArrayList<>();
        listOfGroup.add(universityGroup);
        Mockito.when(universityGroupRepository.findAll()).thenReturn(listOfGroup);
        Mockito.when(universityGroupMapper.toResponseDTO(universityGroup)).thenReturn(groupResponseDTO);
        List<GroupResponseDTO> results = universityGroupService.getAllUniversityGroups();
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
        GroupResponseDTO updatedGroupResponseDTO = GroupResponseDTO.builder()
                .groupName("updated_group")
                .studentsAssignedToGroup(new ArrayList<>())
                .build();

        UniversityGroup universityGroup = Mockito.mock(UniversityGroup.class);
        Mockito.when(universityGroupRepository.findById(1L)).thenReturn(Optional.of(universityGroup));
        Mockito.when(universityGroupRepository.save(universityGroup)).thenReturn(universityGroup);
        Mockito.when(universityGroupMapper.toResponseDTO(universityGroup)).thenReturn(updatedGroupResponseDTO);

        GroupResponseDTO result = universityGroupService.updateUniversityGroup(1L, updatedGroupResponseDTO);

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