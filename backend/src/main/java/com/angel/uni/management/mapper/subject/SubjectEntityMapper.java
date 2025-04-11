package com.angel.uni.management.mapper.subject;

import com.angel.uni.management.dto.SubjectDTO;
import com.angel.uni.management.entity.UniversitySubject;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class SubjectEntityMapper implements Function<SubjectDTO, UniversitySubject> {

    @Override
    public UniversitySubject apply(SubjectDTO subjectDTO) {
        return UniversitySubject.builder()
                .id(subjectDTO.id())
                .name(subjectDTO.name())
                .hoursPerWeek(subjectDTO.hoursPerWeek())
                .description(subjectDTO.description())
                .teacher(null) // Set later in service
                .build();
    }
}
