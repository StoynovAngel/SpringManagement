package com.angel.uni.management.mapper.subject;

import com.angel.uni.management.dto.subject.SubjectRequestDTO;
import com.angel.uni.management.entity.Student;
import com.angel.uni.management.entity.UniversitySubject;
import org.springframework.stereotype.Service;

import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class SubjectRequestDTOMapper implements Function<UniversitySubject, SubjectRequestDTO> {

    @Override
    public SubjectRequestDTO apply(UniversitySubject universitySubject) {
        return SubjectRequestDTO.builder()
                .name(universitySubject.getName())
                .hoursPerWeek(universitySubject.getHoursPerWeek())
                .description(universitySubject.getDescription())
                .teacherId(universitySubject.getTeacher().getId())
                .studentIds(universitySubject.getStudentsAssignedToSubject().stream().map(Student::getId).collect(Collectors.toList()))
                .build();
    }
}

