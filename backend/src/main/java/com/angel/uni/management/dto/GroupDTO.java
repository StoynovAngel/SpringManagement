package com.angel.uni.management.dto;

import lombok.Builder;

import java.util.List;

@Builder
public record GroupDTO(Long id, String groupName, List<StudentDTO> studentsAssignedToGroup) {
}

