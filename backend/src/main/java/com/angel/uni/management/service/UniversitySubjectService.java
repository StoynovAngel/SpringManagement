package com.angel.uni.management.service;

import com.angel.uni.management.dto.subject.SubjectRequestDTO;
import com.angel.uni.management.dto.subject.SubjectResponseDTO;
import org.apache.coyote.BadRequestException;

import java.util.List;

public interface UniversitySubjectService {
    SubjectRequestDTO createSubject(SubjectRequestDTO subjectDTO) throws BadRequestException;
    SubjectResponseDTO getSubjectById(Long id);
    List<SubjectResponseDTO> getAllSubjects();
    SubjectResponseDTO updateSubject(SubjectResponseDTO subjectDTO) throws BadRequestException;
}