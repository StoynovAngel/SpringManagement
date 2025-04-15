package com.angel.uni.management.dto.subject;

import com.angel.uni.management.dto.student.StudentResponseDTO;
import lombok.Builder;

import java.util.List;

@Builder
public record SubjectResponseDTO(Long id, String name, int hoursPerWeek, String description, Long teacherId, List<StudentResponseDTO> students) {
}