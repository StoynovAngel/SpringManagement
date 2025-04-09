package com.angel.uni.management.mapper;

import com.angel.uni.management.dto.SubjectDTO;
import com.angel.uni.management.entity.UniversitySubject;
import com.angel.uni.management.interfaces.BaseMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SubjectMapper extends BaseMapper<SubjectDTO, UniversitySubject> {
}
