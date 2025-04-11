package com.angel.uni.management.mapper.subject;

import com.angel.uni.management.dto.SubjectDTO;
import com.angel.uni.management.entity.UniversitySubject;
import com.angel.uni.management.mapper.student.StudentDTOMapper;
import org.springframework.stereotype.Service;

import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class SubjectDTOMapper implements Function<UniversitySubject, SubjectDTO> {

    private final StudentDTOMapper studentDTOMapper;

    public SubjectDTOMapper(StudentDTOMapper studentDTOMapper) {
        this.studentDTOMapper = studentDTOMapper;
    }

    @Override
    public SubjectDTO apply(UniversitySubject universitySubject) {
        return SubjectDTO.builder()
                .name(universitySubject.getName())
                .hoursPerWeek(universitySubject.getHoursPerWeek())
                .description(universitySubject.getDescription())
                .teacherId(universitySubject.getTeacher().getId())
                .students(universitySubject.getStudentsAssignedToSubject().stream().map(studentDTOMapper).collect(Collectors.toList()))
                .build();
    }
}
