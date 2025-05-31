package com.angel.uni.management.mapper.subject;

import com.angel.uni.management.dto.SubjectDTO;
import com.angel.uni.management.entity.UniversitySubject;
import com.angel.uni.management.mapper.student.StudentMapper;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring", uses = StudentMapper.class)
public interface SubjectMapper {

    SubjectDTO toDTO(UniversitySubject universitySubject);
    UniversitySubject toEntity(SubjectDTO subjectDTO);
    void updateEntityFromDTO(SubjectDTO subjectDTO, @MappingTarget UniversitySubject subject);
}
