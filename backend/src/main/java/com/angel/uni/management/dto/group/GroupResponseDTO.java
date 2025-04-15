package com.angel.uni.management.dto.group;

import com.angel.uni.management.dto.student.StudentResponseDTO;
import lombok.Builder;

import java.util.List;

@Builder
public record GroupResponseDTO(Long id, String groupName, List<StudentResponseDTO> studentsAssignedToGroup) {
}

