package com.angel.uni.management.controller;

import com.angel.uni.management.dto.group.GroupResponseDTO;
import com.angel.uni.management.service.UniversityGroupService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.BDDMockito.given;

@WebMvcTest(controllers = UniversityGroupController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
class UniversityGroupControllerTest {

    private final MockMvc mockMvc;
    private final ObjectMapper objectMapper;
    private GroupResponseDTO groupResponseDTO;

    @MockBean
    private UniversityGroupService universityGroupService;

    @Autowired
    UniversityGroupControllerTest(MockMvc mockMvc, ObjectMapper objectMapper) {
        this.mockMvc = mockMvc;
        this.objectMapper = objectMapper;
    }

    @BeforeEach
    void setUp() {
        groupResponseDTO = GroupResponseDTO.builder()
                .id(1L)
                .groupName("group")
                .studentsAssignedToGroup(new ArrayList<>())
                .build();
    }

    @Test
    void createGroup() throws Exception {
        given(universityGroupService.createUniversityGroup(ArgumentMatchers.any()))
                .willAnswer(invocation -> invocation.getArgument(0));
        ResultActions response = mockMvc.perform(MockMvcRequestBuilders.post("/api/group")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(groupResponseDTO))
        );
        response.andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.groupName", CoreMatchers.is(groupResponseDTO.groupName())));
    }

    @Test
    void getGroupById() throws Exception {
        Long id = 1L;
        Mockito.when(universityGroupService.getUniversityGroupById(1L)).thenReturn(groupResponseDTO);
        ResultActions response = mockMvc.perform(MockMvcRequestBuilders.get("/api/group/" + id)
                .contentType(MediaType.APPLICATION_JSON)
        );
        response.andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void getAllGroups() throws Exception {
        List<GroupResponseDTO> groups = List.of(groupResponseDTO);
        Mockito.when(universityGroupService.getAllUniversityGroups()).thenReturn(groups);
        ResultActions response = mockMvc.perform(MockMvcRequestBuilders.get("/api/group")
                .contentType(MediaType.APPLICATION_JSON)
        );
        response.andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void updateGroupById() throws Exception {
        Long id = 1L;
        GroupResponseDTO responseDTO = GroupResponseDTO.builder()
                .groupName("updated_group")
                .studentsAssignedToGroup(new ArrayList<>())
                .build();
        Mockito.when(universityGroupService.updateUniversityGroup(1L, groupResponseDTO)).thenReturn(responseDTO);
        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.put("/api/group/" + id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(groupResponseDTO))
        );
        resultActions.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.groupName", CoreMatchers.is(responseDTO.groupName())));
    }
}