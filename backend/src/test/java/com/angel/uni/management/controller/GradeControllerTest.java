package com.angel.uni.management.controller;

import com.angel.uni.management.dto.GradeDTO;
import com.angel.uni.management.enums.CountryEnum;
import com.angel.uni.management.enums.GradeType;
import com.angel.uni.management.service.GradeService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
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

import java.time.LocalDateTime;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@WebMvcTest(controllers = GradeController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
class GradeControllerTest {

    private final MockMvc mockMvc;
    private final ObjectMapper objectMapper;
    private GradeDTO gradeDTO;

    @MockBean
    private GradeService gradeService;

    @Autowired
    GradeControllerTest(MockMvc mockMvc, ObjectMapper objectMapper) {
        this.mockMvc = mockMvc;
        this.objectMapper = objectMapper;
    }

    @BeforeEach
    void setUp() {
        gradeDTO = GradeDTO.builder()
                .id(1L)
                .name("grade")
                .teacherId(1L)
                .gradeType(GradeType.FINAL)
                .mark(4.25)
                .dateOfGrading(LocalDateTime.now())
                .countryRepresentation(CountryEnum.DE)
                .build();
    }

    @Test
    void testCreateGrade() throws Exception {
        BDDMockito.given(gradeService.createGrade(ArgumentMatchers.any()))
                .willAnswer(invocation -> invocation.getArgument(0));

        ResultActions resultActions = mockMvc.perform(post("/api/grade")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(gradeDTO)));

        resultActions.andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name", CoreMatchers.is(gradeDTO.name())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.mark", CoreMatchers.is(gradeDTO.mark())));
    }

    @Test
    void testGetGradeById() throws Exception {
        Long id = 1L;
        Mockito.when(gradeService.getGradeById(id)).thenReturn(gradeDTO);
        ResultActions response = mockMvc.perform(get("/api/grade/" + id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(gradeDTO))
        );
        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name", CoreMatchers.is(gradeDTO.name())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.mark", CoreMatchers.is(gradeDTO.mark())));
    }

    @Test
    void getAllGrades() throws Exception {
        List<GradeDTO> gradesList = List.of(gradeDTO);
        Mockito.when(gradeService.getAllGrades()).thenReturn(gradesList);
        ResultActions response = mockMvc.perform(get("/api/grade")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(gradeDTO))
        );
        response.andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void updateGradeById() throws Exception {
        Long id = 1L;
        GradeDTO updatedDTO = GradeDTO.builder()
                .name("update_grade")
                .teacherId(1L)
                .gradeType(GradeType.ORAL)
                .mark(4.50)
                .dateOfGrading(LocalDateTime.now())
                .countryRepresentation(CountryEnum.BG)
                .build();

        Mockito.when(gradeService.updateGrade(id, updatedDTO)).thenReturn(updatedDTO);

        ResultActions response = mockMvc.perform(put("/api/grade/" + id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updatedDTO)));

        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name", CoreMatchers.is(updatedDTO.name())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.mark", CoreMatchers.is(updatedDTO.mark())));
    }
}