package com.angel.uni.management.mapper.subject;

import com.angel.uni.management.dto.subject.SubjectResponseDTO;
import com.angel.uni.management.entity.UniversitySubject;
import com.angel.uni.management.mapper.student.StudentResponseDTOMapper;
import org.springframework.stereotype.Service;

import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class SubjectResponseDTOMapper implements Function<UniversitySubject, SubjectResponseDTO> {

    private final StudentResponseDTOMapper studentResponseDTOMapper;

    public SubjectResponseDTOMapper(StudentResponseDTOMapper studentResponseDTOMapper) {
        this.studentResponseDTOMapper = studentResponseDTOMapper;
    }

    @Override
    public SubjectResponseDTO apply(UniversitySubject universitySubject) {
        return SubjectResponseDTO.builder()
                .id(universitySubject.getId())
                .name(universitySubject.getName())
                .hoursPerWeek(universitySubject.getHoursPerWeek())
                .description(universitySubject.getDescription())
                .teacherId(universitySubject.getTeacher().getId())
                .students(universitySubject.getStudentsAssignedToSubject().stream().map(studentResponseDTOMapper).collect(Collectors.toList()))
                .build();
    }
}
