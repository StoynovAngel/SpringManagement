package com.angel.uni.management.mapper.group;

import com.angel.uni.management.dto.GroupDTO;
import com.angel.uni.management.entity.UniversityGroup;
import com.angel.uni.management.mapper.student.StudentMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = StudentMapper.class)
public interface GroupMapper {

    GroupDTO toDTO(UniversityGroup group);
    UniversityGroup toEntity(GroupDTO groupDTO);
}
