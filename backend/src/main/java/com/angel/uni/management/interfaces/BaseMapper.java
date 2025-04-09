package com.angel.uni.management.interfaces;

import com.angel.uni.management.exceptions.DataMappingException;

public interface BaseMapper<DTO, Entity> {
    DTO mapToDTO(Entity entity) throws DataMappingException;
    Entity mapToEntity(DTO dto) throws DataMappingException;
}
