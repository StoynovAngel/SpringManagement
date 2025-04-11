package com.angel.uni.management.controller;

import com.angel.uni.management.dto.StudentDTO;
import com.angel.uni.management.dto.TeacherDTO;
import com.angel.uni.management.service.TeacherService;
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

import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;

@WebMvcTest(controllers = TeacherController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
class TeacherControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TeacherService teacherService;

    @Autowired
    private ObjectMapper objectMapper;

    private TeacherDTO teacherDTO;

    @BeforeEach
    void setup() {
        teacherDTO = TeacherDTO.builder().id(1L).name("name").build();
    }

    @Test
    void createTeacher() throws Exception {
        given(teacherService.createTeacher(ArgumentMatchers.any())).willAnswer(invocation -> invocation.getArgument(0));
        ResultActions response = mockMvc.perform(MockMvcRequestBuilders.post("/api/teacher")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(teacherDTO)));
        response.andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name", CoreMatchers.is(teacherDTO.name())));
    }

    @Test
    void getTeacherById() throws Exception {
        Long id = 1L;
        TeacherDTO responseDTO = TeacherDTO.builder().name("name").build();
        Mockito.when(teacherService.getTeacherById(id)).thenReturn(responseDTO);
        ResultActions response = mockMvc.perform(MockMvcRequestBuilders.get("/api/teacher/" + id)
                .contentType(MediaType.APPLICATION_JSON)
        );
        response.andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void getAllTeachers() throws Exception {
        TeacherDTO responseDTO = TeacherDTO.builder().name("name").build();
        List<TeacherDTO> responses = List.of(responseDTO);
        Mockito.when(teacherService.getAllTeachers()).thenReturn(responses);
        ResultActions response = mockMvc.perform(MockMvcRequestBuilders.get("/api/teacher")
                .contentType(MediaType.APPLICATION_JSON)
        );
        response.andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void updateTeacher() throws Exception {
        Long id = 1L;
        TeacherDTO responseDTO = TeacherDTO.builder().name("name").build();
        Mockito.when(teacherService.updateTeacher(id, teacherDTO)).thenReturn(responseDTO);
        ResultActions response = mockMvc.perform(MockMvcRequestBuilders.put("/api/teacher/" + id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(teacherDTO)));
        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name", CoreMatchers.is(teacherDTO.name())));
    }
}