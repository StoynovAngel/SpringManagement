package com.angel.uni.management.service;

import com.angel.uni.management.dto.SubjectDTO;
import org.apache.coyote.BadRequestException;

import java.util.List;

public interface USubjectService {

    SubjectDTO createSubject(SubjectDTO subjectDTO) throws BadRequestException;
    SubjectDTO getSubjectByName(String name) throws BadRequestException;
    List<SubjectDTO> getAllSubjects();
    SubjectDTO updateSubject(Long id, SubjectDTO subjectDTO) throws BadRequestException;
}
