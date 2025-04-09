package com.angel.uni.management.mapper;

import com.angel.uni.management.dto.GradeDTO;
import com.angel.uni.management.entity.grade.Grade;
import com.angel.uni.management.interfaces.BaseMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface GradeMapper extends BaseMapper<GradeDTO, Grade> {
}
