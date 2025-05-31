package com.angel.uni.management.dto.student;

import com.angel.uni.management.dto.GradeDTO;
import lombok.Builder;

import java.util.List;

@Builder
public record StudentResponseDTO(Long id, String username, List<GradeDTO> grades, double averageGradeOverall) {
}
