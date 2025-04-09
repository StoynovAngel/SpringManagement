package com.angel.uni.management.mapper;

import com.angel.uni.management.dto.GroupDTO;
import com.angel.uni.management.entity.UniversityGroup;
import com.angel.uni.management.interfaces.BaseMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UniversityGroupMapper extends BaseMapper<GroupDTO, UniversityGroup> {
}
