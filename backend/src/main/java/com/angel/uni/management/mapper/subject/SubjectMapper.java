package com.angel.uni.management.mapper.subject;

import com.angel.uni.management.dto.subject.SubjectRequestDTO;
import com.angel.uni.management.dto.subject.SubjectResponseDTO;
import com.angel.uni.management.entity.UniversitySubject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SubjectMapper {
    private final SubjectResponseDTOMapper subjectResponseDTOMapper;
    private final SubjectRequestDTOMapper subjectRequestDTOMapper;
    private final SubjectRequestEntityMapper subjectRequestEntityMapper;

    @Autowired
    public SubjectMapper(SubjectResponseDTOMapper subjectResponseDTOMapper, SubjectRequestDTOMapper subjectRequestDTOMapper, SubjectRequestEntityMapper subjectRequestEntityMapper) {
        this.subjectResponseDTOMapper = subjectResponseDTOMapper;
        this.subjectRequestDTOMapper = subjectRequestDTOMapper;
        this.subjectRequestEntityMapper = subjectRequestEntityMapper;
    }

    public final SubjectRequestDTO requestToDTO(UniversitySubject universitySubject) {
        return subjectRequestDTOMapper.apply(universitySubject);
    }

    public final UniversitySubject requestToEntity(SubjectRequestDTO subjectRequestDTO) {
        return subjectRequestEntityMapper.apply(subjectRequestDTO);
    }

    public final SubjectResponseDTO responseToDTO(UniversitySubject universitySubject) {
        return subjectResponseDTOMapper.apply(universitySubject);
    }
}
