package com.angel.uni.management.interfaces;

public interface BaseMapper<DTO, Entity> {
    DTO mapToDTO(Entity entity);
    Entity mapToEntity(DTO dto);
}
