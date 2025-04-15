package com.angel.uni.management.mapper.subject;

import com.angel.uni.management.dto.subject.SubjectRequestDTO;
import com.angel.uni.management.entity.UniversitySubject;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class SubjectRequestEntityMapper implements Function<SubjectRequestDTO, UniversitySubject> {

    @Override
    public UniversitySubject apply(SubjectRequestDTO subjectRequestDTO) {
        return UniversitySubject.builder()
                .name(subjectRequestDTO.name())
                .hoursPerWeek(subjectRequestDTO.hoursPerWeek())
                .description(subjectRequestDTO.description())
                .teacher(null) // set in service layer
                .studentsAssignedToSubject(null) // set in service layer
                .build();
    }
}
