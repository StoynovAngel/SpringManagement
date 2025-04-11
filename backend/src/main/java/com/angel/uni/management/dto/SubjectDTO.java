package com.angel.uni.management.dto;

import lombok.Builder;

import java.util.List;

@Builder
public record SubjectDTO(Long id, String name, int hoursPerWeek, String description, Long teacherId, List<StudentDTO> students) {
}