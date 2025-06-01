package com.angel.uni.management.service;

import com.angel.uni.management.dto.SubjectDTO;
import org.apache.coyote.BadRequestException;

import java.util.List;

public interface UniversitySubjectService {
    SubjectDTO createSubject(SubjectDTO subjectDTO) throws BadRequestException;
    SubjectDTO getSubjectById(Long id);
    List<SubjectDTO> getAllSubjects();
    SubjectDTO updateSubject(SubjectDTO subjectDTO) throws BadRequestException;
}