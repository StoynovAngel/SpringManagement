package com.angel.uni.management.controller;

import com.angel.uni.management.dto.student.StudentResponseDTO;
import com.angel.uni.management.service.StudentService;
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
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@WebMvcTest(controllers = StudentController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
class StudentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private StudentService studentService;

    @Autowired
    private ObjectMapper objectMapper;

    private StudentResponseDTO studentResponseDTO;

    @BeforeEach
    void setUp() {
        studentResponseDTO = new StudentResponseDTO(1L, "username", List.of(), 5.50);
    }


    @Test
    void testCreateStudent() throws Exception {
        given(studentService.createStudent(ArgumentMatchers.any())).willAnswer(invocation -> invocation.getArgument(0));
        ResultActions response = mockMvc.perform(post("/api/student")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(studentResponseDTO)));
        response.andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.username", CoreMatchers.is(studentResponseDTO.username())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.averageGradeOverall", CoreMatchers.is(studentResponseDTO.averageGradeOverall())));
    }

    @Test
    void testGetAllStudents() throws Exception {
        StudentResponseDTO responseDTO = StudentResponseDTO.builder().username("username").averageGradeOverall(0.0).grades(List.of()).build();
        List<StudentResponseDTO> responses = List.of(responseDTO);
        Mockito.when(studentService.getAllStudents()).thenReturn(responses);
        ResultActions response = mockMvc.perform(get("/api/student")
                .contentType(MediaType.APPLICATION_JSON)
        );

        response.andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void testGetStudentById() throws Exception {
        Long id = 1L;
        StudentResponseDTO responseDTO = StudentResponseDTO.builder().username("username").averageGradeOverall(0.0).grades(List.of()).build();
        Mockito.when(studentService.getStudentById(id)).thenReturn(responseDTO);
        ResultActions response = mockMvc.perform(get("/api/student/" + id)
                .contentType(MediaType.APPLICATION_JSON)
        );

        response.andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void testUpdateStudentById() throws Exception {
        Long id = 1L;
        StudentResponseDTO responseDTO = StudentResponseDTO.builder().username("username").averageGradeOverall(5.5).grades(List.of()).build();
        Mockito.when(studentService.updateStudent(id, studentResponseDTO)).thenReturn(responseDTO);
        ResultActions response = mockMvc.perform(put("/api/student/" + id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(studentResponseDTO)));
        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.username", CoreMatchers.is(studentResponseDTO.username())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.averageGradeOverall", CoreMatchers.is(studentResponseDTO.averageGradeOverall())));
    }
}