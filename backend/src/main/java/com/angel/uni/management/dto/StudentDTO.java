package com.angel.uni.management.dto;

import lombok.Builder;

import java.util.List;

@Builder
public record StudentDTO(Long id, String username, List<GradeDTO> grades, Double averageGradeOverall) {
}
