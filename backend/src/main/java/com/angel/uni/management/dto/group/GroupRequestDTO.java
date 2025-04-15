package com.angel.uni.management.dto.group;

import lombok.Builder;

import java.util.List;

@Builder
public record GroupRequestDTO(String groupName, List<Long> studentIds) {
}
