package com.angel.uni.management.dto.subject;

import lombok.Builder;

import java.util.List;

@Builder
public record SubjectRequestDTO(String name, int hoursPerWeek, String description, Long teacherId, List<Long> studentIds) {
}
